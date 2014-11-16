package edu.studentbuzz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.helper.IDgenerator;
import edu.studentbuzz.model.Comment;
import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class add_comment
 */
@WebServlet("/add_comment.do")
public class add_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String textCon = (request.getParameter("comment")!=null)?request.getParameter("comment"):"";        
        String postid = (request.getParameter("postid")!=null)?request.getParameter("postid"):"";        
        
        boolean isStudent = (session.getAttribute("student")!=null);
        boolean isFaculty = (session.getAttribute("faculty")!=null);
        
        if(!(textCon.equals("") || postid.equals("")))
        {
            if(session.getAttribute("student")!=null)
            {
                Comment cmm = new Comment();
                cmm.setCommentID(new IDgenerator("comment").getId());
                cmm.setPostID(postid);
                cmm.setTextContent(textCon);
                cmm.setWriteID(((Student)session.getAttribute("student")).getEmailID());
                cmm.add();
                response.sendRedirect("student/posts/view.jsp?postid="+postid+"#"+cmm.getCommentID());
            }
            else if(session.getAttribute("faculty")!=null)
            {
                Comment cmm = new Comment();
                cmm.setCommentID(new IDgenerator("comment").getId());
                cmm.setPostID(postid);
                cmm.setTextContent(textCon);
                cmm.setWriteID(((Faculty)session.getAttribute("faculty")).getEmailID());
                cmm.add();
                response.sendRedirect("faculty/posts/view.jsp?postid="+postid+"#"+cmm.getCommentID());
            }
            else
            {
                response.sendRedirect("login.jsp?error=6");
            }
        }
        else
        {
            if(isFaculty)response.sendRedirect("faculty/posts/view.jsp?postid="+postid+"&error="+2);
            if(isStudent)response.sendRedirect("student/posts/view.jsp?postid="+postid+"&error="+2);
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
