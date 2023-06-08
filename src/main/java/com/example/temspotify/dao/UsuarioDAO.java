package com.example.temspotify.dao;

import com.example.temspotify.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO{
    private DataSource dataSource;

    public UsuarioDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void create(Object o){
        try {
            if (!(o instanceof Usuario))
                throw new RuntimeException("Invalid Object");
            Usuario usuario = (Usuario) o;

            String sql = "INSERT INTO usuario VALUES(null, ?, ?, ?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getEmail());
            stm.setString(3, usuario.getSenha());
            System.out.println("Executando query: " + sql);
            int res = stm.executeUpdate();
            if (res != 0){
                ResultSet rs = stm.getGeneratedKeys();
                if(rs.next()){
                    usuario.setId(rs.getInt(1));
                }
                rs.close();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário" + e.getMessage());
        }
    }

    public List<Object> read(Object o){
        try{
            if(!(o instanceof Usuario))
                throw new RuntimeException("Invalid Object");
            Usuario incompleto = (Usuario) o;
            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(sql);
            stm.setString(1, incompleto.getEmail());
            stm.setString(2, incompleto.getSenha());
            System.out.println("Executando query: " + sql);
            ResultSet rs = stm.executeQuery();
            ArrayList<Object> result = new ArrayList<>();
            if (rs.next()){
                System.out.println("Result: id do usuario: " + rs.getInt("id"));
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        null);
                result.add(usuario);
            }
            stm.close();
            rs.close();
            return result;
        } catch (SQLException ex){
            System.out.println("Erro ao recuperar: " + ex.getMessage());
            ex.printStackTrace();
        }
       return null;
    }

    public void update(Object o){

    }

    public void delete(Object o){

    }
}
