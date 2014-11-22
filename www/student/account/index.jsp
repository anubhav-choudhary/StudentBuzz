<%@page import="edu.studentbuzz.helper.ErrorDecoder"%>
<%@page import="edu.studentbuzz.helper.MsgDecoder"%>
<%@page import="edu.studentbuzz.model.Student"%>
<%@page import="edu.studentbuzz.helper.UserValidator"%>
<%
    UserValidator uv = new UserValidator("student", session);
    if(uv.isValid())
   {
        Student stud = (Student)session.getAttribute("student");
    String gender = "<select name='gender'>";
    if(stud.getGender()) gender+="<option value='1' selected='selected'>Male</option><option value='0'>Female</option>";
       else gender+="<option value='1' >Male</option><option value='0' selected='selected'>Female</option>";
    gender+="</select>";
        
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Student Buzz</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="../../styles/layout.css" type="text/css" />
<link rel="stylesheet" href="../../styles/3_column.css" type="text/css" />
<script type="text/javascript">
function deleteconfirm()
{
    return confirm('Are you sure you want to delete this account?');
    
}
function delcnf()
{
    return confirm('Are you sure you want to remove profile picture?');
    
}
</script>
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
        <li class="active"><a href="index.jsp">My Account</a></li>
        <li class="last"><a href="../../logout.do">Sign out</a></li>
      </ul>
    </div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row3">
  <div id="container" class="clear">
    <!-- ####################################################################################################### -->
    <div id="left_column">
      
      <div class="holder">
        <h2 class="title"><%=stud.getName()%></h2>
        <div class="imgholder"><img src="../../profile_pictures/<%=stud.getPicture()%>" alt="" width="190"/></div>
        <p align="center"><a href="change_picture.jsp">Change</a> | <a href="../../remove_picture.do" onclick="return delcnf();">Remove</a></p>        
      </div>
    </div>
    <!-- ############ -->
    <div id="content" style="width: 700px;">
      <h1 class="title">Profile</h1>
      <%
        if(request.getParameter("msg")!=null) {out.println(new MsgDecoder(Integer.parseInt(request.getParameter("msg"))).getHTML());}
         else if(request.getParameter("error")!=null) {out.println(new ErrorDecoder(Integer.parseInt(request.getParameter("error"))).getHTML());}%>
      <div id="respond">
        <form action="../../update_student.do" method="post">
          <p>
            <label for="name"><small>Name</small></label><br />
            <input type="text" name="name" id="name" value="<%=stud.getName()%>" size="22" />
          </p>
            <input type="hidden" name="email" id="email" value="<%=stud.getEmailID()%>" size="22" />

          <p>
            <label for="name"><small>Gender</small></label><br />
            <%=gender%>
          </p>
          <p>
            <label for="name"><small>Collage</small></label><br />
            <input type="text" name="collage" id="collage" value="<%=stud.getCollege()%>" size="22" />
          </p>
          <p>
            <label for="name"><small>Branch</small></label><br />
            <input type="text" name="branch" id="branch" value="<%=stud.getBranch()%>" size="22" />
          </p>
          <p>
            <label for="name"><small>Semester</small></label><br />
            <input type="text" name="semester" id="semester" value="<%=stud.getSemester()%>" size="22" />
          </p>
          
          <br />
      <h2 class="title">Password</h2>
      <p>
            <label for="oldpassword"><small>Old Password</small></label><br />
            <input type="password" name="oldpassword" id="oldpassword" value="" size="22" />
          </p>
             <p>
            <label for="newpassword"><small>New Password</small></label><br />
            <input type="password" name="newpassword" id="newpassword" value="" size="22" />
          </p>
             <p>
            <label for="name"><small>Confirm New Password</small></label><br />
            <input type="password" name="cnewpassword" id="cnewpassword" value="" size="22" />
          </p>
                <p><br />
                    <input name="submit" type="submit" id="submit" value="Update" /> 

          </p>
              </form>
      
      <h2 class="title">Delete Account</h2>
      <form action="../../delete_student.do" onsubmit="return deleteconfirm();">
      <input name="submit" type="submit" id="submit" value="Delete Account" /></form>
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
        response.sendRedirect("../../"+uv.genPath());
    }
%>