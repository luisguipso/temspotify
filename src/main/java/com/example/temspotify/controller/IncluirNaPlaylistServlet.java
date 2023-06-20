package com.example.temspotify.controller;

import com.example.temspotify.dao.DataSource;
import com.example.temspotify.dao.PlayListDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "IncluirNaPlaylistServlet", value = "/add-on-playlist")
public class IncluirNaPlaylistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaResultado = "/result.jsp";
        int idPlaylist = Integer.parseInt(request.getParameter("idPlaylist"));
        int idMusica = Integer.parseInt(request.getParameter("idMusica"));

        DataSource dataSource = new DataSource();
        PlayListDAO dao = new PlayListDAO(dataSource);
        if (dao.createMusicaPlaylist(idPlaylist, idMusica)) {
            request.setAttribute("resultSTR", "OK");
        } else {
            request.setAttribute("resultSTR", "Erro");
        }
        try {
            dataSource.getConnection().close();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar musica na playlist:".concat(e.getMessage()));
            request.setAttribute("resultSTR", "Erro ao inserir Musica na Playlist!");
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaResultado);
        dispatcher.forward(request, response);
    }
}
