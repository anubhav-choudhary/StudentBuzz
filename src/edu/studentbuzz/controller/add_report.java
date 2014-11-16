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
import edu.studentbuzz.model.Report;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class add_report
 */
@WebServlet("/add_report.do")
public class add_report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String textCon = (request.getParameter("textcontent")!=null)?request.getParameter("textcontent"):"";
        String postid =(request.getParameter("postid")!=null)?request.getParameter("postid"):"";
        boolean isStudent = (session.getAttribute("student")!=null);
        
            
        if(!(textCon.equals("") || postid.equals("")))
        {
            Post post = new Post(Long.parseLong(postid));
            post.findById();
            if(post.getPostID()==null)
            {
                if(isStudent)response.sendRedirect("student/posts/index.jsp?error=18");
                else response.sendRedirect("faculty/posts/index.jsp?error=18");
                return;
            }
            if(session.getAttribute("student")!=null)
            {
                Report rep = new Report(new IDgenerator("report").getId());
                rep.setPostid(Integer.parseInt(postid));
                rep.setTextcontent(textCon);
                rep.setReporterid(((Student)session.getAttribute("student")).getEmailID());
                rep.add();
                response.sendRedirect("student/posts/index.jsp?msg=22");
            }
            else if(session.getAttribute("faculty")!=null)
            {
                Report rep = new Report(new IDgenerator("report").getId());
                rep.setPostid(Integer.parseInt(postid));
                rep.setTextcontent(textCon);
                rep.setReporterid(((Faculty)session.getAttribute("faculty")).getEmailID());
                rep.add();
                response.sendRedirect("faculty/posts/index.jsp?msg=22");
            }
            else
            {
                response.sendRedirect("login.jsp?error=6");
            }
        }
        else
        {
            if(isStudent) response.sendRedirect("student/posts/add_report.jsp?postid="+postid+"&error=2");
                else response.sendRedirect("faculty/posts/add_report.jsp?postid="+postid+"&error=2");
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
