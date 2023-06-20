let musics = [];
let repeat = false;
let currentSong = 0;
const URL = "http://localhost:8080/temspotify/";

let player;

function getOnEnded() {
    return () => {
        function isLastMusic(musicIndex) {
            return musicIndex === musics.length - 1;
        }

        if (!isLastMusic(currentSong)) {
            playMusicIndex(currentSong + 1);
            return;
        }

        if (repeat) {
            playMusicIndex(0);
            return;
        }

        alert("Fim das musicas!");
    };
}

function fillMusicList() {
    var divMusicas = document.getElementById("player-content");
    var filhos = divMusicas.childNodes;

    filhos.forEach(filho => {
        if (filho.nodeName === "DIV") {
            console.log("Adding music to the list: " + filho.title);
            musics.push(filho.title);
        }
    });
}

function setUpPlayer() {
    console.log("Setting up player");
    player = document.getElementById("music-player");
    fillMusicList();
    player.src = URL + musics[0];
    player.onended = getOnEnded();
}

function playMusicIndex(i) {
    currentSong = i;
    let musicPath = URL + musics[currentSong];
    player.src = musicPath;
    console.log("Playing: " + musicPath);
    player.play();
}

function play(musica) {
    let index = musics.indexOf(musica.title);

    if (index === -1)
        return;

    console.log("Searching music " + musica.title + " found index: " + index);
    console.log("Will play: " + musica.title);
    playMusicIndex(index);
}

function changeRepeat() {
    repeat = !repeat;

    let src = repeat ? "images/repeat-pressed.png" : "images/repeat.png";
    document.getElementById("imgRepeat").src = src;
}