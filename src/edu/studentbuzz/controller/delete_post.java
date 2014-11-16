package edu.studentbuzz.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.helper.DeleteFile;
import edu.studentbuzz.model.Attachment;
import edu.studentbuzz.model.Comment;
import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Post;
import edu.studentbuzz.model.Report;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class delete_post
 */
@WebServlet("/delete_post.do")
public class delete_post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String postid=(request.getParameter("postid")!=null)?request.getParameter("postid"):"";

        if(session.getAttribute("admin")!=null)
        {
            if(postid.equals("")) {response.sendRedirect("admin/report/index.jsp?error=2");return;}
            Post pst = new Post(Long.parseLong(postid));
            pst.findById();
            if(pst.getPostID()==null) {response.sendRedirect("admin/report/index.jsp?error=21"); return;}
            Comment cmm = new Comment();
            Attachment att = new Attachment();
            Report rep = new Report();
            DeleteFile df= new DeleteFile();
            Iterator<Attachment> itr1;
            Iterator<Comment> itr2;
            Iterator<Report> itr3;
            //Attachment delete process
            itr1=att.find("select * from attachment where Post_ID='"+postid+"'").iterator();
            while(itr1.hasNext())
            {
                att = (Attachment)itr1.next();
                df.config(att.getUrl(), 2, request);
                df.delete();
                att.remove();
            }
            itr2=cmm.find("select * from comment where post_id='"+postid+"'").iterator();
            while(itr2.hasNext())
            {
                cmm = (Comment)itr2.next();
                cmm.remove();
            }
            itr3=rep.find("select * from report where postid='"+postid+"'").iterator();
            while(itr3.hasNext())
            {
                rep = (Report)itr3.next();
                rep.remove();
            }
            pst.remove();
            response.sendRedirect("admin/report/index.jsp?msg=20");
        }
        else if(session.getAttribute("faculty")!=null)
        {
            if(postid.equals("")) {response.sendRedirect("faculty/posts/index.jsp");return;}
            Post pst = new Post(Long.parseLong(postid));
            pst.findById();
            if(pst.getPostID()==null) {response.sendRedirect("faculty/posts/index.jsp?error=21"); return;}
            if(!pst.getWriterID().equals(((Faculty)session.getAttribute("faculty")).getEmailID())) {response.sendRedirect("faculty/posts/index.jsp?error=20"); return;}
            Comment cmm = new Comment();
            Attachment att = new Attachment();
            Report rep = new Report();
            DeleteFile df= new DeleteFile();
            Iterator<Attachment> itr1;
            Iterator<Comment> itr2;
            Iterator<Report> itr3;
            //Attachment delete process
            itr1=att.find("select * from attachment where Post_ID='"+postid+"'").iterator();
            while(itr1.hasNext())
            {
                att = (Attachment)itr1.next();
                df.config(att.getUrl(), 2, request);
                df.delete();
                att.remove();
            }
            itr2=cmm.find("select * from comment where post_id='"+postid+"'").iterator();
            while(itr2.hasNext())
            {
                cmm = (Comment)itr2.next();
                cmm.remove();
            }
            itr3=rep.find("select * from report where postid='"+postid+"'").iterator();
            while(itr3.hasNext())
            {
                rep = (Report)itr3.next();
                rep.remove();
            }
            pst.remove();
            response.sendRedirect("faculty/posts/index.jsp?msg=20");
        }
        else if(session.getAttribute("student")!=null)
        {
            if(postid.equals("")) {response.sendRedirect("student/posts/index.jsp");return;}
            Post pst = new Post(Long.parseLong(postid));
            pst.findById();
            if(pst.getPostID()==null) {response.sendRedirect("student/posts/index.jsp?error=21"); return;}
            if(!pst.getWriterID().equals(((Student)session.getAttribute("student")).getEmailID())) {response.sendRedirect("student/posts/index.jsp?error=20"); return;}
            Comment cmm = new Comment();
            Attachment att = new Attachment();
            Report rep = new Report();
            DeleteFile df= new DeleteFile();
            Iterator<Attachment> itr1;
            Iterator<Comment> itr2;
            Iterator<Report> itr3;
            //Attachment delete process
            itr1=att.find("select * from attachment where Post_ID='"+postid+"'").iterator();
            while(itr1.hasNext())
            {
                att =itr1.next();
                df.config(att.getUrl(), 2, request);
                df.delete();
                att.remove();
            }
            itr2=cmm.find("select * from comment where post_id='"+postid+"'").iterator();
            while(itr2.hasNext())
            {
                cmm = (Comment)itr2.next();
                cmm.remove();
            }
            itr3=rep.find("select * from report where postid='"+postid+"'").iterator();
            while(itr3.hasNext())
            {
                rep = (Report)itr3.next();
                rep.remove();
            }
            pst.remove();
            response.sendRedirect("student/posts/index.jsp?msg=20");
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
