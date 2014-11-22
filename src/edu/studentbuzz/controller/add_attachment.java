package edu.studentbuzz.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.helper.FileUploader;
import edu.studentbuzz.helper.IDgenerator;
import edu.studentbuzz.model.Attachment;
import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Post;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class add_attachment
 */
@WebServlet("/add_attachment.do")
@MultipartConfig(fileSizeThreshold=1024*1024*10,maxFileSize=1024*1024*50,maxRequestSize=1024*1024*100)
public class add_attachment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String postid = (request.getParameter("postid")!=null)?request.getParameter("postid"):"";
        boolean isStudent = (session.getAttribute("student")!=null);
        boolean isFaculty = (session.getAttribute("faculty")!=null);
        //##################UPLOADER CONFIGURATION########################
        FileUploader fu = new FileUploader();
        ArrayList<String> fl = new ArrayList<String>();
        fl.add("attachment");
        ArrayList<String> at = new ArrayList<String>();
        at.add("image/png");
        at.add("image/jpeg");
        at.add("image/pjpeg");
        at.add("image/bmp");
        at.add("image/x-windows-bmp");
        at.add("image/gif");
        at.add("application/pdf");
        at.add("application/x-compressed");
        at.add("application/x-zip-compressed");
        at.add("application/zip");
        at.add("multipart/x-zip");
        at.add("application/msword");
        at.add("text/plain");
        at.add("application/mspowerpoint");
        at.add("application/powerpoint");
        at.add("application/vnd.ms-powerpoint");
        at.add("application/x-mspowerpoint");
        fu.setAllowed_filetype(at);
        fu.setField_names(fl);
        fu.setRequest(request);
        fu.setSizelimit(5242880);
        fu.setUpload_dir("attachments");
        //################################################################
        if(isStudent || isFaculty)
        {
            if(!postid.equals(""))
            {   
                Post post = new Post(Long.parseLong(postid));
                post.findById();
                if(isStudent)
                {
                    Student stud = (Student)session.getAttribute("student");
                    if(post.getWriterID().equals(stud.getEmailID()))
                    {   
                        fu.upload();
                        if(!fu.getAssigined_name().isEmpty())
                        {
                            Attachment att = new Attachment();
                            att.setAttachmentID(new IDgenerator("attachment").getId());
                            att.setFileType(fu.getFile_type().get(0));
                            att.setFilename(fu.getOriginal_name().get(0));
                            att.setPostID(new BigInteger(postid));
                            att.setSize(fu.getSize().get(0).intValue());
                            att.setUrl(fu.getAssigined_name().get(0));
                            att.add();
                            response.sendRedirect("student/posts/manage_attachment.jsp?msg=17&postid="+postid);
                        }
                        else
                        {
                            response.sendRedirect("student/posts/manage_attachment.jsp?error=15&postid="+postid);//uploading failed.
                        }
                    }
                    else
                    {
                        response.sendRedirect("student/posts/index.jsp?error=20");
                    }
                }
                else
                {
                    Faculty fac = (Faculty)session.getAttribute("faculty");
                    if(post.getWriterID().equals(fac.getEmailID()))
                    {
                        fu.upload();
                        if(!fu.getAssigined_name().isEmpty())
                        {
                            Attachment att = new Attachment();
                            att.setAttachmentID(new IDgenerator("attachment").getId());
                            att.setFileType(fu.getFile_type().get(0));
                            att.setFilename(fu.getOriginal_name().get(0));
                            att.setPostID(new BigInteger(postid));
                            att.setSize(fu.getSize().get(0).intValue());
                            att.setUrl(fu.getAssigined_name().get(0));
                            att.add();
                            response.sendRedirect("faculty/posts/manage_attachment.jsp?msg=17&postid="+postid);
                        }
                        else
                        {
                            response.sendRedirect("faculty/posts/manage_attachment.jsp?error=15&postid="+postid);
                        }
                    }
                    else
                    {
                        response.sendRedirect("faculty/posts/index.jsp?error=20");
                    }
                
                }
            }
            else
            {
                if(isStudent) response.sendRedirect("student/posts/manage_attachment.jsp?error=2&postid="+postid);
                else response.sendRedirect("faculty/posts/manage_attachment.jsp?error=2&postid="+postid);
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
