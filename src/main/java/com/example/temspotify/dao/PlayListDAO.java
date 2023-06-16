package com.example.temspotify.dao;

import com.example.temspotify.model.Musica;
import com.example.temspotify.model.PlayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayListDAO implements DAO{
    private final DataSource dataSource;

    public PlayListDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Object o) {
        try{
            PlayList pl = (PlayList) o;
            String sql = "INSERT INTO playlist values (null, ?, ?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, pl.getTitulo());
            stm.setInt(2, pl.getUsuario().getId());
            System.out.println("Executando comando: " + sql);
            int res = stm.executeUpdate();
            if (res == 0){
                throw new RuntimeException("Não foi possivel incluir playlist!");
            }
            ResultSet rs = stm.getGeneratedKeys();
            if(rs.next()){
                pl.setId(rs.getInt(1));
            }
        } catch (SQLException e){
            System.out.println("Erro ao cadastrar PlayList: ".concat(e.getMessage()));
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> read(Object o) {
        try {
            String sql = "SELECT * FROM playlist WHERE usuario_id = ?";
            Integer userId = (Integer)o;
            PreparedStatement stm = dataSource.getConnection().prepareStatement(sql);
            stm.setInt(1, userId);
            System.out.println("Executando comando: ".concat(sql));
            ResultSet rs = stm.executeQuery();
            ArrayList<Object> playlists = new ArrayList<>();
            while (rs.next()){
                PlayList pl = PlayList.builder()
                        .id(rs.getInt("id"))
                        .titulo(rs.getString("titulo"))
                        .build();
                playlists.add(pl);
            }
            rs.close();
            stm.close();
            return playlists;
        } catch (SQLException e){
            System.out.println("Erro ao recuperar PlayLists: ".concat(e.getMessage()));
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public PlayList readPlayListDetailsById(int id){
        try {
            String sql = "select * from playlist p"
                    + " left join musicas_playlists mp on p.id = mp.playlist_id "
                    + " left join musica m on m.id = mp.musica_id "
                    + " where p.id = ? ";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            PlayList playList = null;
            while (rs.next()) {
                if (playList == null)
                    playList = PlayList.builder()
                        .id(rs.getInt("p.id"))
                        .titulo(rs.getString("p.titulo"))
                        .musicas(new ArrayList<Musica>())
                        .build();
                if(rs.getString("m.titulo") != null) {
                    Musica musica = Musica.builder()
                            .id(rs.getInt("m.id"))
                            .titulo(rs.getString("m.titulo"))
                            .album(rs.getString("m.album"))
                            .estilo(rs.getInt("m.estilo"))
                            .artista(rs.getString("m.artista"))
                            .linkMP3(rs.getString("m.linkMP3"))
                            .build();
                    playList.getMusicas().add(musica);
                }
            }
            return playList;
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar detalhes da Playlist: ".concat(e.getMessage()));
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }

    public boolean createMusicaPlaylist(int idPlaylist, int idMusica) {
        try {
            String sql = "INSERT INTO musicas_playlists VALUES (?, ?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(sql);
            stm.setInt(1,idMusica);
            stm.setInt(2,idPlaylist);
            System.out.println("Executando comando: ".concat(sql));
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: ".concat(e.getMessage()));
            e.printStackTrace();
        }
        return false;
    }
}
