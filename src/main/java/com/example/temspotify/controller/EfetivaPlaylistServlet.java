package com.example.temspotify.controller;

import com.example.temspotify.dao.DataSource;
import com.example.temspotify.dao.PlayListDAO;
import com.example.temspotify.model.PlayList;
import com.example.temspotify.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "EfetivaPlaylistServlet", value="/efetiva-playlist")
public class EfetivaPlaylistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "my-playlists.jsp";

        try{
            Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
            if(usuario != null) {
                PlayList novaPlaylist = PlayList.builder()
                        .titulo(request.getParameter("txtNomePlaylist"))
                        .usuario(usuario)
                        .build();

                DataSource dataSource = new DataSource();
                PlayListDAO dao = new PlayListDAO(dataSource);
                dao.create(novaPlaylist);
                dataSource.getConnection().close();

                if(usuario.getPlayLists() == null)
                    usuario.setPlayLists(new ArrayList<PlayList>());
                usuario.getPlayLists().add(novaPlaylist);
                request.getSession().setAttribute("Usuario", usuario);
            }
        } catch (Exception e){
            System.out.println("Erro ao cadastrar PlayList: ".concat(e.getMessage()));
            e.printStackTrace();
            paginaDestino = "/error.jsp";
            request.setAttribute("erroSTR", "Erro ao criar playlist: ".concat(e.getMessage()));
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
