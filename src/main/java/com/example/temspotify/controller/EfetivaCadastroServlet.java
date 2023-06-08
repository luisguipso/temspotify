package com.example.temspotify.controller;

import com.example.temspotify.dao.DataSource;
import com.example.temspotify.dao.UsuarioDAO;
import com.example.temspotify.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EfetivaCadastroServlet", value = "/efetiva-cadastro")
public class EfetivaCadastroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pagina = "/my-account.jsp";
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");

        Usuario usuario = Usuario.builder()
                .nome(nome)
                .senha(senha)
                .email(email)
                .build();

        DataSource dataSource = new DataSource();
        UsuarioDAO dao = new UsuarioDAO(dataSource);
        dao.create(usuario);
        System.out.println(usuario);

        try {
            dataSource.getConnection().close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão" + e.getMessage());
            e.printStackTrace();
            request.setAttribute("erroMSG", "Erro ao criar nova conta de usuário: ");
            pagina = "/erro.jsp";
        }

        request.getSession().setAttribute("Usuario", usuario);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
    }
}
