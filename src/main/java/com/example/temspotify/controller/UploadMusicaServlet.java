package com.example.temspotify.controller;

import com.example.temspotify.dao.DataSource;
import com.example.temspotify.dao.MusicaDAO;
import com.example.temspotify.model.Musica;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@WebServlet(name="UploadMusicaServlet", value="/upload-musica")
@MultipartConfig(
        maxFileSize = 20848820,
        maxRequestSize = 418018841
)
public class UploadMusicaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/error.jsp";

        if (request.getSession().getAttribute("Usuario") != null) {
            try {
                String artista = request.getParameter("txtArtista");
                String album = request.getParameter("txtAlbum");
                String titulo = request.getParameter("txtNomeMusica");
                int estilo = Integer.parseInt(request.getParameter("txtEstilo"));

                InputStream arquivoOriginal = request.getPart("fileMP3").getInputStream();
                String nomeArquivoOriginal = request.getPart("fileMP3").getSubmittedFileName();
                String nomeArquivo = getServletContext().getRealPath("/")
                        .concat("musicas/")
                        .concat(nomeArquivoOriginal);
                FileOutputStream arquivoMP3 = new FileOutputStream(nomeArquivo);
                byte b[] = new byte[1024];
                while (arquivoOriginal.available() > 0 ){
                    arquivoOriginal.read(b);
                    arquivoMP3.write(b);
                }
                arquivoOriginal.close();
                arquivoMP3.close();

                Musica musica = Musica.builder()
                        .artista(artista)
                        .album(album)
                        .estilo(estilo)
                        .titulo(titulo)
                        .linkMP3("musicas/".concat(nomeArquivoOriginal))
                        .build();
                DataSource dataSource = new DataSource();
                MusicaDAO dao = new MusicaDAO(dataSource);
                dao.create(musica);
                paginaDestino = "/my-account.jsp";
            } catch (Exception e) {
                request.setAttribute("erroSTR", "Erro ao fazer upload: ".concat(e.getMessage()));
                e.printStackTrace();
            }
        } else {
            request.setAttribute("erroSTR", "Erro Usuario não conectado!");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
