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
import quizapp.bean.StaffLogin;
import quizapp.model.StaffMember;


@WebServlet(name = "StaffLogin", urlPatterns = {"/StaffLogin"})
public class StaffLoginController extends HttpServlet {

    
    @Override  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
                RequestDispatcher rd=request.getRequestDispatcher("stafflogin.jsp");  
                rd.forward(request, response); 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
          
        String sss=request.getParameter("staffID");
        int staffID = Integer.parseInt(sss);
        StaffMember staffMember = new StaffMember();
        
        boolean isStaff = staffMember.isValidStaff(staffID);
        HttpSession session = request.getSession();
          
        if(isStaff){
            StaffLogin staffLogin = new StaffLogin();
            
            staffLogin.setLoggedIn();
            staffLogin.setStaffID(staffID);

            session.setAttribute("StaffLogin", staffLogin);
            
            RequestDispatcher rd=request.getRequestDispatcher("login-success.jsp");  
            rd.forward(request, response);  
        }  
        else{
            RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");  
            rd.forward(request, response);  
        }
    }
    
    

}
