<!DOCTYPE html>
<jsp:useBean id="Usuario" type="com.example.temspotify.model.Usuario" scope="session"/>
<jsp:useBean id="Musicas" type="java.util.List" scope="request"/>
<jsp:useBean id="idPlaylist" type="java.lang.String" scope="request"/>
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
            <a href="./my-account">
                <img src="images/logo.png" alt="logo" class="rounded mx-auto d-block" width="15%" align="center"/>
            </a>
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
            <h4 class="text-center">Acervo De M&uacute;sicas</h4>
        </div>
    </div>
    <div class="row" id="conteudo">
        <div class="col-md-2">
            &nbsp;
        </div>
        <div class="col-md-2 botao">
            <span class="text-center"> <a class="nav-linke" href="./new-music"> Upload Musica </a> </span>
        </div>
        <div class="col-md-2 botao">
            <span class="text-center"> <a class="nav-linke" href="./playlists"> Minhas Playlists </a> </span>
        </div>
        <div class="col-md-2 botao">
            <span class="text-center"> <a class="nav-linke" href="./new-playlist"> Nova Playlist </a> </span>
        </div>
        <div class="col-md-2 botao">
            <span class="text-center"> <a class="nav-linke" href="./logout"> Logout </a> </span>
        </div>
        <div class="col-md-2">
            &nbsp;
        </div>
    </div>
    <%-- varias iterações, uma linha por playlist usará JSTL--%>
    <c:forEach var="musica" items="${Musicas}">
        <div class="row">
            <div class="col-md-2">&nbsp;</div>
            <div class="col-md-1">
                <button class="btn-primary" onclick="adicionar(${idPlaylist}, ${musica.id})">+</button>
            </div>
            <div class="col-md-7">
                <span>${musica.titulo} (${musica.artista})</span>
                <br>
                <span class="artista">Album: ${musica.album}</span>
                <br>
                <span class="artista">Estilo:
                  <c:if test="${musica.estilo == 1}">Rock</c:if>
                  <c:if test="${musica.estilo == 2}">Sertanejo/Moda de viola</c:if>
                  <c:if test="${musica.estilo == 3}">Pagode/Samba</c:if>
                  <c:if test="${musica.estilo == 4}">Eletronico</c:if>
                  <c:if test="${musica.estilo == 5}">Musicas da Jovem Pan</c:if>
                  <c:if test="${musica.estilo == 6}">Outros</c:if>
                </span>
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