package edu.studentbuzz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Post;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class update_post
 */
@WebServlet("/update_post.do")
public class update_post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String postid = (request.getParameter("postid")!=null)?request.getParameter("postid"):"";
        String textCon = (request.getParameter("textcontent")!=null)?request.getParameter("textcontent"):"";
        String subId = (request.getParameter("subid")!=null)?request.getParameter("subid"):"";
        boolean isStudent = (session.getAttribute("student")!=null);
        
        if(!(postid.equals("") || textCon.equals("") || subId.equals("")))
        {
            Post post = new Post(Long.parseLong(postid));
            post.findById();
            if(post.getPostID()!=null)
            {
                if(session.getAttribute("student")!=null && post.getWriterID().equals(((Student)session.getAttribute("student")).getEmailID()))
                {
                    post.setTextContent(textCon);
                    post.setSubcatID(subId);
                    post.update();
                    response.sendRedirect("student/posts/index.jsp?msg=19");
                }
                else if(session.getAttribute("faculty")!=null && post.getWriterID().equals(((Faculty)session.getAttribute("faculty")).getEmailID()))
                {
                    post.setTextContent(textCon);
                    post.setSubcatID(subId);
                    post.update();
                    response.sendRedirect("faculty/posts/index.jsp?msg=19");
                }
                else
                {
                    response.sendRedirect("login.jsp?error=6");
                }
            }
            else
            {
                if(isStudent) response.sendRedirect("student/posts/index.jsp?error=18");
                else response.sendRedirect("Invalid Post");
            }
        }
        else
        {
            if(isStudent) response.sendRedirect("student/posts/update.jsp?error=2&postid="+postid);
            else response.sendRedirect("error 2");
                
        }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		processRequest(request, response);
	}

}
