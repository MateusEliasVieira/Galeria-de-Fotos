package controller;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import dao.DAOWallpaperRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.WallpaperBean;


@MultipartConfig
@WebServlet(urlPatterns = {"/save","/gallery"})
public class SaveWallpaperServletController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DAOWallpaperRepository daoWallpaperRepository = new DAOWallpaperRepository();
   
    public SaveWallpaperServletController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		try {
			
			if(action != null && !action.isEmpty() && action.equals("list")) {
				List<WallpaperBean> list = daoWallpaperRepository.listAll();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("gallery.jsp");
				request.setAttribute("list", list);
				requestDispatcher.forward(request, response);
			}
			
			else if(action != null && !action.isEmpty() && action.equals("download")) {
				
				String id = request.getParameter("id");
				
				if(id != null && !id.isEmpty()) {
					
					Long idWallpaper = Long.parseLong(id);
					WallpaperBean wallpaperBean = daoWallpaperRepository.findById(idWallpaper);
					
					if(wallpaperBean != null) {
						response.setHeader("Content-Disposition", "attachment;filename=arquivo." + wallpaperBean.getExtension());
						response.getOutputStream().write(new Base64().decodeBase64(wallpaperBean.getWallpaper().split(",")[1]));						
					}else {
						// Caso o wallpaper não exista
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
						request.setAttribute("msg", "Desculpe, este wallpaper não existe!");
						requestDispatcher.forward(request, response);
					}
					
				}else {
					// Caso o id seja alterado
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
				}
				
			}else {
				// Caso a action seja alterada
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			}
			
		
		} catch (Exception e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
			request.setAttribute("error", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	
			String author = request.getParameter("author");
			String description = request.getParameter("description");
			
			if((author != null && !author.isEmpty()) && (description != null && !description.isEmpty())) {
							
				
				Part part = request.getPart("file"); // Pegamos o arquivo (imagem)
				
				if(part.getSize() > 0) { // Verificamos se a imagem existe através do seu tamanho 
					
					byte file[] = IOUtils.toByteArray(part.getInputStream()); // Convertemos para um array de bytes
					Base64 base64 = new Base64(); // Instanciando objeto
					String stringBase64 = base64.encodeAsString(file); // Converte aquele array de bytes em string base64
					String stringBase64Save = "data:"+part.getContentType()+";base64,"+stringBase64; // Adicionamos alguns parametros necessários (data:image/formato;base64,stringBase64) para depois poder ser mostrado no img do html			
				
					// Instaciamos um objeto e colocamos os dados nele
					WallpaperBean wallpaperBean = new WallpaperBean();
					wallpaperBean.setAuthor(author);
					wallpaperBean.setDescription(description);
					wallpaperBean.setWallpaper(stringBase64Save);
					wallpaperBean.setExtension(part.getContentType().split("/")[1]);
					
					// Mandamos para salvar
					daoWallpaperRepository.insert(wallpaperBean);
					
				}else {
					// Não enviou a imagem
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Por favor, selecione a imagem!");
					requestDispatcher.forward(request, response);
				}
					
				
			}else {
				// Não preencheu os dados
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Por favor preencha todos os dados!");
				requestDispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
			request.setAttribute("error", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}
	
	

}
