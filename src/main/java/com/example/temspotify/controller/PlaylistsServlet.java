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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "PlaylistsServlet", value = "/playlists")
public class PlaylistsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/index.html";

        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
            if (usuario != null){
                paginaDestino = "/my-playlists.jsp";
                if (usuario.getPlayLists() == null){
                    DataSource dataSource = new DataSource();
                    PlayListDAO dao = new PlayListDAO(dataSource);
                    List<Object> playlistsFromDb = dao.read(usuario.getId());
                    List<PlayList> userPlayLists = new ArrayList<>();

                    if(playlistsFromDb != null)
                        userPlayLists = playlistsFromDb.stream()
                                .map(o -> {
                                    PlayList pl = (PlayList) o;
                                    pl.setUsuario(usuario);
                                    return pl;
                                })
                                .collect(Collectors.toList());

                    usuario.setPlayLists(userPlayLists);
                    request.getSession().setAttribute("Usuario", usuario);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao recuperar Playlists: ".concat(e.getMessage()));
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request,response);
    }
}
