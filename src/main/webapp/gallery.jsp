<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.WallpaperBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link href="assets/css/gallery.css" rel="stylesheet"/>
<title>Gallery</title>
</head>
<body id="body">
	<h1>Wellcome to Wallpaper Gallery</h1>
	
	<a id="btnIndex" class="btn btn-danger" href="<%= request.getContextPath() %>">Home</a>
	
	<% 
		List<WallpaperBean> list = (List) request.getAttribute("list");
	%>
	<div class="box">
		<% for(WallpaperBean wallpaperBean : list){	%>
			<div class="frame">
				<img src="<%= wallpaperBean.getWallpaper() %>" />
				<h5>Author: <%= wallpaperBean.getAuthor() %></h5>
				<button id="button-modal" class="btn btn-success" onclick="openModal('modal<%= wallpaperBean.getId()%>')">Modal</button>
				<div id="modal<%= wallpaperBean.getId()%>" class="box-modal">
			        <div class="form-modal">
				        <p class="btn btn-dark" onclick="closeModal('modal<%= wallpaperBean.getId()%>')">Close</p>
			            <img class="wallpapers-modal" src="<%= wallpaperBean.getWallpaper() %>" />
						<h5>Author: <%= wallpaperBean.getAuthor() %></h5>
						<p>Description: <%= wallpaperBean.getDescription() %></p>
						<a href="gallery?action=download&id=<%= wallpaperBean.getId() %>" class="btn btn-danger">Download</a>
			        </div>
    			</div>
			</div>	
	
		<%} %>
	</div>
	
	<script src="assets/js/modalGallery.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	
</body>
</html>