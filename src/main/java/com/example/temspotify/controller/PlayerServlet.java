package com.example.temspotify.controller;

import com.example.temspotify.model.PlayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="PlayerServlet", value="/player")
public class PlayerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/error.jsp";

        if(request.getSession().getAttribute("Usuario") == null){
            request.setAttribute("erroSTR","Usuario não conectado!");
        } else {
            PlayList playList = (PlayList) request.getSession().getAttribute("PlayList");
            if (playList == null){
                request.setAttribute("erroSTR","Playlist não encontrada!");
            } else {
                paginaDestino = "/player.jsp";
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request,response);
    }
}
