package edu.studentbuzz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.model.Comment;
import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Post;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class delete_comment
 */
@WebServlet("/delete_comment.do")
public class delete_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //PrintWriter out = response.getWriter();
        String commentid=(request.getParameter("commentid")!=null)?request.getParameter("commentid"):"";

        if(session.getAttribute("faculty")!=null)
        {
            if(commentid.equals("")) {response.sendRedirect("faculty/posts/index.jsp"); return;}
            Comment cmm = new Comment(Long.parseLong(commentid));
            cmm.findById();
            if(cmm.getCommentID()==null) {response.sendRedirect("faculty/posts/index.jsp");return;}
            Post post=new Post(Long.parseLong(cmm.getPostID()));
            post.findById();
            if(cmm.getWriteID().equals(((Faculty)session.getAttribute("faculty")).getEmailID()) || post.getWriterID().equals(((Faculty)session.getAttribute("faculty")).getEmailID()))
            {
                cmm.remove();
                response.sendRedirect("faculty/posts/view.jsp?msg=21&postid="+cmm.getPostID());
            }
            else 
            {
                response.sendRedirect("student/posts/view.jsp?error=20&postid="+cmm.getPostID());
            }
        }
        else if(session.getAttribute("student")!=null)
        {
            if(commentid.equals("")) {response.sendRedirect("student/posts/index.jsp"); return;}
            Comment cmm = new Comment(Long.parseLong(commentid));
            cmm.findById();
            if(cmm.getCommentID()==null) {response.sendRedirect("student/posts/index.jsp");return;}
            Post post=new Post(Long.parseLong(cmm.getPostID()));
            post.findById();
            if(cmm.getWriteID().equals(((Student)session.getAttribute("student")).getEmailID()) || post.getWriterID().equals(((Student)session.getAttribute("student")).getEmailID()))
            {
                cmm.remove();
                response.sendRedirect("student/posts/view.jsp?msg=21&postid="+cmm.getPostID());
            }
            else 
            {
                response.sendRedirect("student/posts/view.jsp?error=20&postid="+cmm.getPostID());
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
