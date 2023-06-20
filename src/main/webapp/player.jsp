<!DOCTYPE html>
<jsp:useBean id="Usuario" type="com.example.temspotify.model.Usuario" scope="session"/>
<jsp:useBean id="PlayList" type="com.example.temspotify.model.PlayList" scope="session"/>
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
<body onLoad="setUpPlayer()">
<div class="container-fluid">
    <!------------------------------------------------- LOGO ------------------------------------------------->
    <div class="row">
        <div class="col-md-12">
            <a href="playlists">
                <img src="images/logo.png" alt="logo" class="rounded mx-auto d-block" width="15%" align="center"/>
            </a>

        </div>
    </div>
    <%--        Titulo PLaylist       --%>
    <div class="row">
        <div class="col-md-2">
            &nbsp;
        </div>
        <div class="col-md-8 text-center">
            <h4>${PlayList.titulo}</h4>
        </div>
        <div class="col-md-2">
            &nbsp;
        </div>
    </div>
    <%--        Musicas       --%>
    <div class="row">
        <div class="col-md-2">
            &nbsp;
        </div>
        <div id="player-content" class="col-md-8 text-center musica">
            <c:forEach var="music" items="${PlayList.musicas}">
                <div title="${music.linkMP3}" onclick="play(this);">
                        ${music.titulo} (${music.artista})
                </div>
            </c:forEach>
        </div>
        <div class="col-md-2">
            &nbsp;
        </div>
    </div>
    <br>
    <%--      Player Controll      --%>
    <div class="row">
        <div class="col-md-2">
            &nbsp;
        </div>
        <div class="col-md-8 text-center">
            <div class="row">
                <div class="col-md-2">
                    &nbsp;
                </div>
                <div class="col-md-3 text-right">
                    <img id="imgRepeat" src="images/repeat.png" alt="repeat" onclick="changeRepeat()"
                         height="60px"/>
                </div>
                <div class="col-md-5 text-left">
                    <audio id="music-player" controls controlsList="nodownload" src=""></audio>
                </div>
                <div class="col-md-2">
                    &nbsp;
                </div>
            </div>
        </div>
        <div class="col-md-2">
            &nbsp;
        </div>
    </div>
</div>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
<script src="js/player-scripts.js"></script>

</body>
</html>