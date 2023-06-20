package com.example.temspotify.controller;

import com.example.temspotify.dao.DataSource;
import com.example.temspotify.dao.MusicaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="RecuperaMusicasServlet", value = "/get-music")
public class RecuperaMusicasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/error.jsp";
        try{
            if(request.getSession().getAttribute("Usuario") == null) {
                request.setAttribute("erroSTR", "Usuario nao conectado!");
            } else {
                String idPlaylist = request.getParameter("idPlaylist");
                request.setAttribute("idPlaylist", idPlaylist);

                DataSource dataSource = new DataSource();
                MusicaDAO dao = new MusicaDAO(dataSource);
                List<Object> musicas = dao.read(null);
                if(musicas != null){
                    request.setAttribute("Musicas", musicas);
                    paginaDestino = "/my-musics.jsp";
                } else {
                    request.setAttribute("erroSTR", "Erro ao recuperar musicas do banco de dados");
                }
                dataSource.getConnection().close();
            }
        } catch (Exception e){
            String msg = "Erro ao montar pagina de musicas";
            System.out.println(msg.concat(": ").concat(e.getMessage()));
            e.printStackTrace();
            request.setAttribute("erroSTR", msg);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request,response);
    }
}
