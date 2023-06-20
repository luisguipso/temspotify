package com.example.temspotify.dao;

import com.example.temspotify.model.Musica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO implements DAO {
    private final DataSource dataSource;

    public MusicaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Object o) {
        if (!(o instanceof Musica))
            throw new RuntimeException("Tipo de objeto incorreto!");

        try {
            Musica musica = (Musica) o;

            String sql = "INSERT INTO musica VALUES (null, ?, ?, ?, ?, ?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(sql);
            stm.setString(1, musica.getTitulo());
            stm.setString(2, musica.getArtista());
            stm.setString(3, musica.getAlbum());
            stm.setInt(4, musica.getEstilo());
            stm.setString(5, musica.getLinkMP3());

            System.out.println("Executando comando: ".concat(sql));
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar música: ".concat(e.getMessage()));
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> read(Object o) {
        String sql = "SELECT * FROM musica ORDER BY titulo";
        try (PreparedStatement stm = dataSource.getConnection().prepareStatement(sql)){
            System.out.println("Executando query: ".concat(sql));
            ResultSet rs = stm.executeQuery();
            List<Object> musicas = new ArrayList<>();
            while(rs.next()){
                Musica musica = Musica.builder()
                        .id(rs.getInt("id"))
                        .titulo(rs.getString("titulo"))
                        .artista(rs.getString("artista"))
                        .album(rs.getString("album"))
                        .estilo(rs.getInt("estilo"))
                        .linkMP3(rs.getString("linkMP3"))
                        .build();
                musicas.add(musica);
            }
            return musicas;
        } catch (SQLException e){
            System.out.println("Erro ao recuperar musica: ".concat(e.getMessage()));
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
}
