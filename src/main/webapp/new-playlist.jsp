<!DOCTYPE html>
<jsp:useBean id="Usuario" type="com.example.temspotify.model.Usuario" scope="session"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>.: TemSpotify by Luis :.</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>
<body>

<div class="container-fluid">
    <!------------------------------------------------- CREDITOS ------------------------------------------------->
    <div class="row">
        <div class="col-md-12">
            <p id="creditos" align="right"> Developed by Luis Gomes </p>
        </div>
    </div>
    <!------------------------------------------------- LOGO ------------------------------------------------->
    <div class="row">
        <div class="col-md-12" align="center">
            <a href="./my-account">
            <img src="images/logo.png" class="rounded mx-auto d-block"  width="15%" align="center" />
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
    <div class="row">
        <div class="col-md-12">
            <h4 class="text-center">
                Crie Sua Playlist <a class="nav-linke" href="./my-account">${Usuario.nome}</a>
            </h4>
        </div>
    </div>
    <!------------------------------------------------- FORM ------------------------------------------------->
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
                    <form role="form" action="efetiva-playlist" method="POST">
                        <div class="form-group">
                            <label for="playlistName">Nome da Playlist</label>
                            <input type="text" class="form-control" id="playlistName" name="txtNomePlaylist">
                        </div>
                        <button type="submit" class="btn btn-primary">Criar Playlist</button>
                    </form>
                </div>
                <div class="col-md-2">
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>