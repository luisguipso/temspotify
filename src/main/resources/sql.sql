create database temspotify;
create user 'temspotify@localhost' identified by 'T3m@ul@';
grant all privileges on temspotify.* to 'temspotify@localhost';

create table musica
(
    id      int not null auto_increment,
    artista varchar(100),
    album   varchar(100),
    estilo  int,
    linkMP3 text,
    constraint pk_musica primary key (id)
);

alter table musica add column titulo varchar(100) after id;

create table usuario (
    id int not null auto_increment,
    nome varchar(100),
    email varchar(100),
    senha varchar(20),

    constraint pk_usuario primary key (id)
);

create table playlist (
    id int not null auto_increment,
    titulo varchar(150),
    usuario_id int not null,
    constraint pk_playlist primary key (id),
    constraint fk_usuario foreign key (usuario_id) references usuario(id)
);

create table musicas_playlists (
    musica_id int not null,
    playlist_id int not null,
    constraint pk_musicas_playlists primary key (musica_id, playlist_id),
    constraint fk_musica foreign key (musica_id) references musica(id),
    constraint fk_playlist foreign key (playlist_id) references playlist(id)
);


insert into usuario values (null, "Luis Guilherme", "luisguilherme_pso@hotmail.com", "1234");
insert into usuario values (null, "Cyntia Novaes", "cyntia_dte@hotmail.com", "4321");
insert into usuario values (null, "User Teste", "userteste_mga@hotmail.com", "1234");