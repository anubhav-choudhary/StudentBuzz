package edu.studentbuzz.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.studentbuzz.helper.DeleteFile;
import edu.studentbuzz.helper.FileUploader;
import edu.studentbuzz.model.Admin;
import edu.studentbuzz.model.Faculty;
import edu.studentbuzz.model.Student;

/**
 * Servlet implementation class add_picture
 */
@WebServlet("/add_picture.do")
@MultipartConfig(fileSizeThreshold=1024*1024*10,maxFileSize=1024*1024*50,maxRequestSize=1024*1024*100)
public class add_picture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (request.getParameter("email")!=null)?request.getParameter("email"):"";
        //##################UPLOADER CONFIGURATION########################
        FileUploader fu = new FileUploader();
        ArrayList<String> fl = new ArrayList<String>();
        fl.add("picture");
        ArrayList<String> at = new ArrayList<String>();
        at.add("image/png");
        at.add("image/jpeg");
        at.add("image/pjpeg");
        at.add("image/bmp");
        at.add("image/x-windows-bmp");
        at.add("image/gif");
        fu.setAllowed_filetype(at);
        fu.setField_names(fl);
        fu.setRequest(request);
        fu.setSizelimit(5242880);
        fu.setUpload_dir("profile_pictures");
        //################################################################
        
            if(session.getAttribute("student")!=null)
            {
                Student stud = (Student)session.getAttribute("student");
                if(!stud.getPicture().equals("default.png"))
                {
                    new DeleteFile(stud.getPicture(), 1, request).delete();
                }
                fu.upload();
                if(fu.getAssigined_name().isEmpty())
                {
                    response.sendRedirect("student/account/change_picture.jsp?error=15");
                }
                else
                {
                    stud.setPicture(fu.getAssigined_name().get(0));
                    stud.update();
                    response.sendRedirect("student/account/change_picture.jsp?msg=13");
                }
                
            }
            else if(session.getAttribute("faculty")!=null )
            {
                Faculty fac = (Faculty)session.getAttribute("faculty");
                if(!fac.getPicture().equals("default.png"))
                {
                    new DeleteFile(fac.getPicture(), 1, request).delete();
                }
                fu.upload();
                if(fu.getAssigined_name().isEmpty())
                {
                    response.sendRedirect("faculty/account/change_picture.jsp?error=15");
                }
                else
                {
                    fac.setPicture(fu.getAssigined_name().get(0));
                    fac.update();
                    response.sendRedirect("faculty/account/change_picture.jsp?msg=13");
                }
            }
            else if(session.getAttribute("admin")!=null)
            {
                if(email.equals(""))
                {
                    Admin adm = (Admin) session.getAttribute("admin");
                    if(!adm.getPicture().equals("default.png"))
                    {
                        new DeleteFile(adm.getPicture(), 1, request).delete();
                    }
                    fu.upload();
                    if(fu.getAssigined_name().isEmpty())
                    {
                        response.sendRedirect("admin/account/change_picture.jsp?error=15");
                    }  
                    else
                    {
                        adm.setPicture(fu.getAssigined_name().get(0));
                        adm.update();
                        response.sendRedirect("admin/account/change_picture.jsp?msg=13");
                    }
                }
                else
                {
                    Student stud =new Student(email);
                    stud.findById();
                    if(stud.getEmailID()!=null)
                    {
                        //uplaod for student
                        if(!stud.getPicture().equals("default.png"))
                        {
                             new DeleteFile(stud.getPicture(), 1, request).delete();
                        }
                        fu.upload();
                        if(fu.getAssigined_name().isEmpty())
                        {
                            response.sendRedirect("admin/student/change_picture.jsp?error=15&email="+stud.getEmailID());
                        }  
                        else
                        {
                            stud.setPicture(fu.getAssigined_name().get(0));
                            stud.update();
                            response.sendRedirect("admin/student/change_picture.jsp?msg=13&email="+stud.getEmailID());
                        }
                    }
                    else
                    {
                        Faculty fac = new Faculty(email);
                        fac.findById();
                        if(fac.getEmailID()!=null)
                        {
                            //upload for faculty
                            if(!fac.getPicture().equals("default.png"))
                            {
                                 new DeleteFile(fac.getPicture(), 1, request).delete();
                            }
                            fu.upload();
                            if(fu.getAssigined_name().isEmpty())
                            {
                                response.sendRedirect("admin/faculty/change_picture.jsp?error=15&email="+fac.getEmailID());
                            }  
                            else
                            {
                                fac.setPicture(fu.getAssigined_name().get(0));
                                fac.update();
                                response.sendRedirect("admin/faculty/change_picture.jsp?msg=13&email="+fac.getEmailID());
                            }
                        }
                        else
                        {
                            response.sendRedirect("admin/index.jsp?error=17");
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
