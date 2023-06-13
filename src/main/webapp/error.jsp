<!DOCTYPE html>
<jsp:useBean id="erroSTR" type="java.lang.String" scope="request" />
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
    <!------------------------------------------------- Conteudo ------------------------------------------------->
    <div class="row">
        <div class="col-md-12">
            <h4 class="text-center" id="h4error">
                ERRO: ${erroSTR}
            </h4>
        </div>
    </div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>