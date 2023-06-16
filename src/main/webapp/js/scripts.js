function adicionar(idPlaylist, idMusica){
    var xmlhttp = new XMLHttpRequest();
    let baseURL = "http://localhost:8080/temspotify";
    let servlet = "/add-on-playlist";
    xmlhttp.open("GET", baseURL + servlet + "?idPlaylist="+idPlaylist+"&idMusica="+idMusica);
    xmlhttp.onreadystatechange = function (){
      if(xmlhttp.status === 200 && xmlhttp.readyState === 4){
          alert(xmlhttp.responseText);
      }
    };
    xmlhttp.send();
}