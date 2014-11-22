<%@page import="java.util.ArrayList"%>
<%@page import="edu.studentbuzz.model.Faculty"%>
<%@page import="java.util.Iterator"%>
<%@page import="edu.studentbuzz.model.Post"%>
<%@page import="edu.studentbuzz.model.Department"%>
<%@page import="edu.studentbuzz.model.SubCategory"%>
<%@page import="edu.studentbuzz.helper.UserValidator"%>
<%@page import="edu.studentbuzz.model.Student"%>
<%
    UserValidator uv = new UserValidator("student", session);
    if(uv.isValid())
   {
        String subid=(request.getParameter("subid")!=null)?request.getParameter("subid"):"";
        String pageno = (request.getParameter("page")!=null)?request.getParameter("page"):"1";
        int pagesize=30;
        if(!subid.equals(""))
        {
            SubCategory scat = new SubCategory(subid);
            scat.findById();
            if(scat.getSubID()!=null)
            {
                Department dept=new Department(scat.getDeptid());
                dept.findById();
                
                Post post = new Post();
                ArrayList total = post.find("select * from post where subcat_ID='"+scat.getSubID()+"' Order by Timestamp desc");
                Iterator itr = post.find("select * from post where subcat_ID='"+scat.getSubID()+"' Order by Timestamp desc LIMIT "+((Integer.parseInt(pageno)-1)*pagesize)+" ,"+pagesize).iterator();
                Student stud = new Student();
                Faculty fac = new Faculty();
                int count = total.size();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Student Buzz</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="../../styles/layout.css" type="text/css" />
</head>
<body id="top">
<div class="wrapper row1">
  <div id="header" class="clear">
    <div class="fl_left">
      <h1><a href="index.html">Student Buzz</a></h1>
      <p>Where You and Experts meet!</p>
    </div>
    <div class="fl_right">
      <p style="color: black;">Hello, <% out.println(((Student)session.getAttribute("student")).getName());%> (<a href="../../logout.do">Sign Out</a>)</p>
      
    </div>
    <div id="topnav">
      <ul>
        <li><a href="../index.jsp">Home</a></li>
        <li><a href="add.jsp">Add Post</a></li>
        <li><a href="index.jsp">My Posts</a></li>
        <li><a href="dept.jsp">All Posts</a></li>
        <li><a href="../account/index.jsp">My Account</a></li>
        <li class="last"><a href="../../logout.do">Sign out</a></li>
      </ul>
    </div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row3">
  <div id="container" class="clear">
    <!-- ####################################################################################################### -->
    <div id="content" style="width: 100%;">
      <h1 style="margin-bottom: 30px;">All Posts Releated to <%=dept.getName()%> : <%=scat.getName()%></h1>
      <%
      String name,pic;
        while(itr.hasNext())
        {
            post = (Post)itr.next();
            stud.setEmailID(post.getWriterID());
            stud.findById();
            if(stud.getEmailID()==null)
            {
                fac.setEmailID(post.getWriterID());
                fac.findById();
                name=fac.getName();
                pic=fac.getPicture();
            }
            else
            {
                name=stud.getName();
                pic=stud.getPicture();
            }
            out.println("<h1><a href='../account/view.jsp?email="+post.getWriterID()+"'><img class='avatar' src='../../profile_pictures/"+pic+"' width='32' height='32' alt='' align='middle'/>"+name+"</a> at "+post.getTimestamp().toString()+"</h1>");
            out.println("<p>"+post.getTextContent().substring(0, (400<post.getTextContent().length())?400:post.getTextContent().length())+"...</p>");
            out.println("<p> <a href='view.jsp?postid="+post.getPostID()+"'>Comment</a> ");
            if(!post.getWriterID().equals(((Student)session.getAttribute("student")).getEmailID())) out.print("| <a href='add_report.jsp?postid="+post.getPostID()+"'>Report Abuse</a></p>");
            out.print("</p>");
        }


        %>
      <div class="pagination">
          <%
     int page_no= Integer.parseInt(pageno);
      String prev = (page_no>1)?"show_subject.jsp?subid="+subid+"&page="+String.valueOf((page_no-1)):"#";
      String next = (count>(page_no*pagesize))?"show_subject.jsp?subid="+subid+"&page="+String.valueOf(page_no+1):"#";
     %>
      <ul>
        <li class="prev"><a href="<%=prev%>">&laquo; Previous</a></li>
        <li><a href="show_subject.jsp?subid=<%=subid%>&page=1">1</a></li>
        <%
         String buttons="";
          for(int i=2;i<6;i++)
          {
              if(count>(i-1)*pagesize) buttons+=("<li><a href='show_subject.jsp?subid="+subid+"&page="+i+"'>"+i+"</a></li>");
                           else break;
          }

        %>
        <%=buttons%>
        <li class="next"><a href="<%=next%>">Next &raquo;</a></li>
      </ul>
    </div>
    </div>
   
    <!-- ####################################################################################################### -->
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row4">
  <div id="footer" class="clear">
    <!-- ####################################################################################################### -->
    <div class="footbox">
      <h2>Quick Links</h2>
      <ul>
        <li><a href="../index.jsp">&raquo; Home</a></li>
        <li><a href="add.jsp">&raquo; Add Post</a></li>
        <li><a href="index.jsp">&raquo; My Posts</a></li>
        <li><a href="dept.jsp">&raquo; All Posts</a></li>
        <li><a href="../account/index.jsp">&raquo; My Account</a></li>
      </ul>
    </div>
    <div class="fl_right">
      <div id="social">
        <h2>Connect With Us</h2>
        <ul>
          <li><a href="#"><img src="../../images/social/facebook.gif" alt="" /></a></li>
          <li><a href="#"><img src="../../images/social/twitter.gif" alt="" /></a></li>
          <li><a href="#"><img src="../../images/social/flickr.gif" alt="" /></a></li>
          <li><a href="#"><img src="../../images/social/youtube.gif" alt="" /></a></li>
          <li class="last"><a href="#"><img src="../../images/social/rss.gif" alt="" /></a></li>
        </ul>
      </div>
    </div>
    <!-- ####################################################################################################### -->
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper">
  <div id="copyright" class="clear">
    <p class="fl_left">All Rights Reserved - <a href="#">StudentBuzz</a></p>
    <p class="fl_right">Developed by <a href="../../contactus.jsp">Anubhav Chodhary | Shravani Mallick</a></p>
  </div>
</div>
</body>
</html>
<%
            }
            else
            {
                response.sendRedirect("dept.jsp?error=10");
            }
        }
        else
        {
            response.sendRedirect("dept.jsp");
        }
    }
    else
    {
        response.sendRedirect("../../"+uv.genPath());
    }
%>