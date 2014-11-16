package edu.studentbuzz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.helper.IDgenerator;
import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Post;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class add_post
 */
@WebServlet("/add_post.do")
public class add_post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //data collection
        HttpSession session = request.getSession();
        String textCon = (request.getParameter("textcontent")!=null)?request.getParameter("textcontent"):"";
        String subId = (request.getParameter("subid")!=null)?request.getParameter("subid"):"";
        boolean isStudent = (session.getAttribute("student")!=null);
        
        if(!(textCon.equals("") || subId.equals("")))
        {
            if(session.getAttribute("student")!=null)
            {
                Student stud = (Student) session.getAttribute("student");
                Post post = new Post();
                post.setPostID(new IDgenerator("post").getId());
                post.setWriterID(stud.getEmailID());
                post.setTextContent(textCon);
                post.setSubcatID(subId);
                post.add();
                response.sendRedirect("student/posts/index.jsp?msg=16");
            }
            else if(session.getAttribute("faculty")!=null)
            {
                Faculty fac = (Faculty) session.getAttribute("faculty");
                Post post = new Post();
                post.setPostID(new IDgenerator("post").getId());
                post.setWriterID(fac.getEmailID());
                post.setTextContent(textCon);
                post.setSubcatID(subId);
                post.add();
                response.sendRedirect("faculty/posts/index.jsp?msg=16");
            }
            else
            {
                response.sendRedirect("login.jsp?error=6");
            }
        }
        else
        {
            if(isStudent) response.sendRedirect("student/posts/add.jsp?error=2");
                else response.sendRedirect("faculty/posts/add.jsp?error=2");
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
