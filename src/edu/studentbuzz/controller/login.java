package edu.studentbuzz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.model.Account;
import edu.studentbuzz.model.Admin;
import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class login
 */
@WebServlet("/login.do")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Data Collection
        HttpSession session=request.getSession();
        String email = (request.getParameter("email")!=null)?request.getParameter("email"):"";
        String password = (request.getParameter("password")!=null)?request.getParameter("password"):"";
        
        //Data Validation
        if(email.equals("") || password.equals(""))
        {
               response.sendRedirect("login.jsp?error=2");
        }
        else{
        //Data operation
            Account acc = new Account();
            acc.validate(email, password);
            if(acc.getEmailID()!=null)
            {
                if(acc.getStatus()==1)
                {
                Admin ad = new Admin(acc.getEmailID());
                ad.findById();
                if(ad.getEmailID()==null)
                {
                    Faculty ft = new Faculty(acc.getEmailID());
                    ft.findById();
                    if(ft.getEmailID()==null)
                    {
                        Student st = new Student(acc.getEmailID());
                        st.findById();
                        session.setAttribute("student", st);
                        response.sendRedirect("student/index.jsp");
                    }
                    else
                    {
                        session.setAttribute("faculty", ft);
                        response.sendRedirect("faculty/index.jsp");
                    }
                }
                else
                {
                        session.setAttribute("admin", ad);
                        response.sendRedirect("admin/index.jsp");
                }
                }
                else
                {
                    response.sendRedirect("login.jsp?error=5");
                }
            }
            else
            {
                        response.sendRedirect("login.jsp?error=1");
            }
        
        }
        
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
