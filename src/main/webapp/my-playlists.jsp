<!DOCTYPE html>
<jsp:useBean id="Usuario" type="com.example.temspotify.model.Usuario" scope="session"/>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>.: TemSpotify by Luis :.</title>

  <meta name="description" content="Source code generated using layoutit.com">
  <meta name="author" content="LayoutIt!">

  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">

</head>
<body>
<div class="container-fluid">
  <!------------------------------------------------- LOGO ------------------------------------------------->
  <div class="row">
    <div class="col-md-12">
      <img src="images/logo.png" class="rounded mx-auto d-block" width="15%" align="center"/>
    </div>
  </div>
  <!------------------------------------------------- TITULO ------------------------------------------------->
  <div class="row">
    <div class="col-md-12">
      <h3 class="text-center">
        Tem Spotify - Sua playlist na Web!
      </h3>
    </div>
  </div>
  <!------------------------------------------------- Conteudo ------------------------------------------------->
  <div class="row">
    <div class="col-md-12">
      <h4 class="text-center">
        Bem vindo!!! ${Usuario.nome}
      </h4>
    </div>
  </div>
  <div class="row" id="menu">
    <div class="col-md-3">
      &nbsp;
    </div>
    <div class="col-md-6">
      <ul class="nav">
        <li class="nav-item">
          <a class="nav-link" href="#">Minha Conta</a>
        </li>
        <li class="nav-item ml-md-auto">
          <a class="nav-link" href="./my-playlists">Playlists</a>
        </li>
        <li class="nav-item ml-md-auto">
          <a class="nav-link" href="#">Nova Playlist</a>
        </li>
        <li class="nav-item ml-md-auto">
          <a class="nav-link" href="#">Upload</a>
        </li>
      </ul>
    </div>
    <div class="col-md-3">
      &nbsp;
    </div>
  </div>
<%-- varias iterações, uma linha por playlist usará JSTL--%>
  <c:forEach var="playlist" items="${Usuario.playLists}">
    <div class="row">
      <div class="col-md-2">&nbsp;</div>
      <div class="col-md-8">
        <strong>${playlist.titulo}</strong>
        <ul>
          <c:forEach var="musica" items="${playlist.musicas}">
            <li>${musica.titulo} (${musica.artista})</li>
          </c:forEach>
        </ul>
      </div>
      <div class="col-md-2">&nbsp;</div>
    </div>
  </c:forEach>

</div>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>