package com.example.temspotify.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="MinhaContaServlet", value = "/my-account")
public class MinhaContaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/index.html";

        if (request.getSession().getAttribute("Usuario") != null) {
            paginaDestino = "/my-account.jsp";
        } else {
            request.setAttribute("erroSTR", "Erro Usuario não conectado!");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
