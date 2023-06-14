package com.example.temspotify.controller;

import com.example.temspotify.dao.DataSource;
import com.example.temspotify.dao.PlayListDAO;
import com.example.temspotify.model.PlayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name= "DetalhesDaPlaylistServlet", value="/playlist-details")
public class DetalhesDaPlaylistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/error.jsp";
        if(request.getSession().getAttribute("Usuario") != null) {
            try {
                DataSource dataSource = new DataSource();
                PlayListDAO dao = new PlayListDAO(dataSource);
                int idPlaylist = Integer.parseInt(request.getParameter("id"));
                PlayList playList = dao.readPlayListDetailsById(idPlaylist);
                if (playList != null) {
                    request.getSession().setAttribute("PlayList", playList);
                    paginaDestino = "/playlist-details.jsp";
                } else {
                    request.setAttribute("erroMSG", "Erro ao recuperar Playlist!");
                }
            } catch (Exception e) {
                request.setAttribute("erroMSG", "Erro ao recuperar Playlist:".concat(e.getMessage()));
                e.printStackTrace();
            }
        } else {
            request.setAttribute("erroMSG", "Usuario nao autenticado!");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
