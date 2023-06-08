package com.example.temspotify.controller;

import com.example.temspotify.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MyPlayListsServlet", value = "/my-playlists")
public class MyPlayListsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
        String paginaRetorno = "/index.html";

        if(usuario != null){
            paginaRetorno = "/my-playlists.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaRetorno);
        dispatcher.forward(request, response);
    }
}
