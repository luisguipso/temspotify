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
import java.util.List;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        String pagina = "/error.jsp";
        Usuario incompleto = new Usuario();
        incompleto.setEmail(email);
        incompleto.setSenha(senha);
        DataSource ds = null;

        try{
            ds = new DataSource();
            UsuarioDAO dao = new UsuarioDAO(ds);
            List<Object> res = dao.read(incompleto);
            if(res != null && res.size() > 0){
                pagina = "/my-account.jsp";
                request.getSession().setAttribute("Usuario", res.get(0));
            } else {
                request.setAttribute("erroSTR", "Usuario / Senha inválidos!");
            }
            ds.getConnection().close();
        } catch (Exception e) {
            request.setAttribute("erroSTR", e.getMessage());
            e.printStackTrace();
        }

        RequestDispatcher d = getServletContext().getRequestDispatcher(pagina);
        d.forward(request, response);
    }
}
