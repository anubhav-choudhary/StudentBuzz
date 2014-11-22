package edu.studentbuzz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.helper.DeleteFile;
import edu.studentbuzz.model.Admin;
import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class remove_picture
 */
@WebServlet("/remove_picture.do")
public class remove_picture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (request.getParameter("email")!=null)?request.getParameter("email"):"";
        
        if(session.getAttribute("student")!=null)
        {
                Student stud = (Student)session.getAttribute("student");
                if(stud.getPicture().equals("default.png"))
                {
                    response.sendRedirect("student/account/index.jsp?error=16");
                }
                else
                {
                    new DeleteFile(stud.getPicture(), 1, request).delete();
                    stud.setPicture("default.png");
                    stud.update();
                    response.sendRedirect("student/account/index.jsp?msg=15");
                }
        }
        else if(session.getAttribute("faculty")!=null)
        {
                Faculty fac = (Faculty)session.getAttribute("faculty");
                if(fac.getPicture().equals("default.png"))
                {
                    response.sendRedirect("faculty/account/index.jsp?error=16");
                }
                else
                {
                    new DeleteFile(fac.getPicture(), 1, request).delete();
                    fac.setPicture("default.png");
                    fac.update();
                    response.sendRedirect("faculty/account/index.jsp?msg=15");
                }
        }
        else if(session.getAttribute("admin")!=null)
        {
            if(email.equals(""))
            {
                Admin adm = (Admin)session.getAttribute("admin");
                if(adm.getPicture().equals("default.png"))
                {
                    response.sendRedirect("admin/account/index.jsp?error=16");
                }
                else
                {
                    new DeleteFile(adm.getPicture(), 1, request).delete();
                    adm.setPicture("default.png");
                    adm.update();
                    response.sendRedirect("admin/account/index.jsp?msg=15");
                }
            }
            else
            {
                Student stud = new Student(email);
                stud.findById();
                if(stud.getEmailID()==null)
                {
                    Faculty fac = new Faculty(email);
                    fac.findById();
                    if(fac.getEmailID()!=null)
                    {
                        if(fac.getPicture().equals("default.png"))
                        {
                            response.sendRedirect("admin/faculty/update.jsp?&error=16&email="+email);
                        }
                        else
                        {
                            new DeleteFile(fac.getPicture(), 1, request).delete();
                            fac.setPicture("default.png");
                            fac.update();
                            response.sendRedirect("admin/faculty/update.jsp?msg=15&email="+email);
                        }       
                    }
                    else
                    {
                        response.sendRedirect("admin/index.jsp?error=17");
                    }
                }
                else
                {
                    if(stud.getPicture().equals("default.png"))
                    {
                        response.sendRedirect("admin/student/update.jsp?error=16&email="+email);
                    }
                    else
                    {
                        new DeleteFile(stud.getPicture(), 1, request).delete();
                        stud.setPicture("default.png");
                        stud.update();
                        response.sendRedirect("admin/student/update.jsp?msg=15&email="+email);
                    }
                }
            }
        }
        else
        {
            response.sendRedirect("login.jsp?error=6");
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
