package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionFactory;
import model.WallpaperBean;

public class DAOWallpaperRepository {

	private Connection connection = null;
	
	public DAOWallpaperRepository() {
		connection = SingleConnectionFactory.getConnection();
	}
	
	public void insert(WallpaperBean wallpaperBean) throws Exception {
		String sql = "insert into wallpaper(author,description,wallpaper,extension) values(?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, wallpaperBean.getAuthor());
		preparedStatement.setString(2, wallpaperBean.getDescription());
		preparedStatement.setString(3, wallpaperBean.getWallpaper());
		preparedStatement.setString(4, wallpaperBean.getExtension());
		preparedStatement.execute();
		connection.commit();
	}
	
	public List<WallpaperBean> listAll() throws Exception{
		List<WallpaperBean> list = new ArrayList<WallpaperBean>();
		String sql = "select * from wallpaper";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			WallpaperBean wallpaperBean = new WallpaperBean();
			wallpaperBean.setId(resultSet.getLong("id"));
			wallpaperBean.setAuthor(resultSet.getString("author"));
			wallpaperBean.setDescription(resultSet.getString("description"));
			wallpaperBean.setWallpaper(resultSet.getString("wallpaper"));
			wallpaperBean.setExtension(resultSet.getString("extension"));
			list.add(wallpaperBean);
		}
		return list;
	}
	
	public WallpaperBean findById(Long id) throws Exception {
		String sql = "select * from wallpaper where id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		WallpaperBean wallpaperBean = new WallpaperBean();
		while(resultSet.next()) {
			wallpaperBean.setId(resultSet.getLong("id"));
			wallpaperBean.setAuthor(resultSet.getString("author"));
			wallpaperBean.setDescription(resultSet.getString("description"));
			wallpaperBean.setWallpaper(resultSet.getString("wallpaper"));
			wallpaperBean.setExtension(resultSet.getString("extension"));
		}
		return wallpaperBean;
	}
	
	
	
	
}
