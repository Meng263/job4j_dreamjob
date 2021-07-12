package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", Store.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        boolean isDelete = Boolean.parseBoolean(req.getParameter("delete"));
        int id = Integer.parseInt(req.getParameter("id"));
        Store store = Store.instOf();
        if (isDelete) {
            store.deleteCandidateById(id);
        } else {
            store.save(new Candidate(id, req.getParameter("name")));
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
