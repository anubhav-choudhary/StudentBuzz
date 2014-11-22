<%@page import="edu.studentbuzz.model.Faculty"%>
<%@page import="edu.studentbuzz.helper.UserValidator"%>
<%@page import="edu.studentbuzz.model.Student"%>
<%
    UserValidator uv = new UserValidator("student", session);
    if(uv.isValid())
   {
        String email = (request.getParameter("email")!=null)?request.getParameter("email"):"";
        if(!email.equals(""))
        {
            Student stud = new Student(email);
            stud.findById();
            if(stud.getEmailID()!=null)
            {
                String gend = (stud.getGender())?"Male":"Female";
        
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Student Buzz</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="../../styles/layout.css" type="text/css" />
<link rel="stylesheet" href="../../styles/3_column.css" type="text/css" />
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
        <li><a href="../posts/add.jsp">Add Post</a></li>
        <li><a href="../posts/index.jsp">My Posts</a></li>
        <li><a href="../posts/dept.jsp">All Posts</a></li>
        <li><a href="index.jsp">My Account</a></li>
        <li class="last"><a href="../../logout.do">Sign out</a></li>
      </ul>
    </div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row3">
    <div id="container" class="clear">
  <div id="left_column">
      
      <div class="holder">
        <h2 class="title"><%=stud.getName()%></h2>
        <div class="imgholder"><img src="../../profile_pictures/<%=stud.getPicture()%>" alt="" width="190"/></div>
      </div>
    </div>
    <!-- ############ -->
    <div id="content" style="width: 700px;">
      <h1 class="title">Profile</h1>
      <div id="respond">
        
          <p>
            <label for="name"><small>Name</small></label><br />
            <span style="font-size: larger; color: #5B5B5B;"><%=stud.getName()%></span>
          </p>
          <p>
            <label for="name"><small>Email</small></label><br />
            <span style="font-size: larger; color: #5B5B5B;"><%=stud.getEmailID()%></span>
          </p>
          <p>
            <label for="name"><small>Gender</small></label><br />
            <span style="font-size: larger; color: #5B5B5B;"><%=gend%></span>
          </p>
          <p>
            <label for="name"><small>Collage</small></label><br />
            <span style="font-size: larger; color: #5B5B5B;"><%=stud.getCollege()%></span>
          </p>
          <p>
            <label for="name"><small>Branch</small></label><br />
            <span style="font-size: larger; color: #5B5B5B;"><%=stud.getBranch()%></span>
          </p>
          <p>
            <label for="name"><small>Semester</small></label><br />
            <span style="font-size: larger; color: #5B5B5B;"><%=stud.getSemester()%></span>
          </p>
      </div>
    </div>
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
        <li><a href="../posts/add.jsp">&raquo; Add Post</a></li>
        <li><a href="../posts/index.jsp">&raquo; My Posts</a></li>
        <li><a href="../posts/dept.jsp">&raquo; All Posts</a></li>
        <li><a href="index.jsp">&raquo; My Account</a></li>
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
          <li class="last"><a href="#"><img src="../images/social/rss.gif" alt="" /></a></li>
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
                Faculty fac = new Faculty(email);
                fac.findById();
                if(fac.getEmailID()!=null) response.sendRedirect("fac_view.jsp?email="+email);
                           else response.sendRedirect("index.jsp?error=11");
           }
        }
        else
        {
         response.sendRedirect("index.jsp");
        }
    }
    else
    {
        response.sendRedirect("../../"+uv.genPath());
    }
%>