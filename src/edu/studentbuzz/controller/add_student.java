package edu.studentbuzz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.helper.Md5Generator;
import edu.studentbuzz.model.Account;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class add_student
 */
@WebServlet("/add_student.do")
public class add_student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (request.getParameter("name")!=null)?request.getParameter("name"):"";
        String email = (request.getParameter("email")!=null)?request.getParameter("email"):"";
        String gender = (request.getParameter("gender")!=null)?request.getParameter("gender"):"";
        String pass = (request.getParameter("newpassword")!=null)?request.getParameter("newpassword"):"";
        String cpass = (request.getParameter("cnewpassword")!=null)?request.getParameter("cnewpassword"):"";
        String semester = (request.getParameter("semester")!=null)?request.getParameter("semester"):"";
        String collage = (request.getParameter("collage")!=null)?request.getParameter("collage"):"";
        String branch = (request.getParameter("branch")!=null)?request.getParameter("branch"):"";
        boolean isAdmin = (session.getAttribute("admin")==null)?false:true;
        if(!(name.equals("") || email.equals("") || gender.equals("") || pass.equals("") || semester.equals("") || collage.equals("")|| branch.equals("")))
        {
            if(pass.equals(cpass))
            {
                Account acc = new Account(email);
                acc.findById();
                if(acc.getEmailID()==null)
                {
                   acc.setEmailID(email);
                   acc.setPassword(new Md5Generator(pass).getResult());
                   acc.setStatus(Short.parseShort("1"));
                   acc.add();
                   
                   Student stud = new Student();
                   stud.setEmailID(email);
                   stud.setName(name);
                   stud.setBranch(branch);
                   stud.setCollege(collage);
                   stud.setSemester(Short.parseShort(semester));
                   stud.setPicture("default.png");
                   boolean gend = (gender.equals("1"))?true:false;
                   stud.setGender(gend);
                   stud.add();
                   if(isAdmin)
                   {
                       response.sendRedirect("admin/student/index.jsp?msg=4");
                   }
                   else
                   {
                       session.setAttribute("student", stud);
                       response.sendRedirect("student/index.jsp");
                   }
                }
                else
                {
                    if (isAdmin)response.sendRedirect("admin/student/add.jsp?error=4");
                    else response.sendRedirect("register.jsp?role=1&error=4");
                }
            }
            else
            {
                if (isAdmin) response.sendRedirect("admin/student/add.jsp?error=3");
                else response.sendRedirect("register.jsp?role=1&error=3");
            }
        }
        else
        {
            if (isAdmin) response.sendRedirect("admin/student/add.jsp?error=2");
            else  response.sendRedirect("register.jsp?role=1&error=2");
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
