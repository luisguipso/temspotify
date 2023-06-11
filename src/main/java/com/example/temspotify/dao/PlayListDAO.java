package com.example.temspotify.dao;

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

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }
}
