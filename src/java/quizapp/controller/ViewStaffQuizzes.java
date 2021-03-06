/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quizapp.bean.UserLogin;
import quizapp.model.DeleteStaffQuiz;
import quizapp.model.FetchStaffQuizzes;

/**
 *
 * @author craigwatt
 */
@WebServlet(name = "ViewStaffQuizzes", urlPatterns = {"/ViewStaffQuizzes"})
public class ViewStaffQuizzes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute("whoLog");
        gatherStaffQuizzes(userLogin.getID(), request, response);
    }

    private void gatherStaffQuizzes(int sID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FetchStaffQuizzes sO = new FetchStaffQuizzes();
        request.setAttribute("quizList", sO.getQuizzes(sID));
        RequestDispatcher rd = request.getRequestDispatcher("/viewStaffQuizzes.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sss = "";
        //String aaa = "";
        //request.getAttribute(aaa)
        sss = request.getParameter("Delete");
        //aaa = request.getParameter("Availabilty");
        int quizID = Integer.parseInt(sss);
        DeleteStaffQuiz deleteStaffQuiz = new DeleteStaffQuiz();
        boolean didItDelete;
        didItDelete = deleteStaffQuiz.delete(quizID);
        HttpSession session = request.getSession();
        if (didItDelete) {
            session.setAttribute("deleteQuizAttempt", quizID);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
