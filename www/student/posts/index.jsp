<%@page import="java.util.Iterator"%>
<%@page import="edu.studentbuzz.model.Post"%>
<%@page import="edu.studentbuzz.helper.UserValidator"%>
<%@page import="edu.studentbuzz.model.Student"%>
<%@page import="edu.studentbuzz.helper.ErrorDecoder"%>
<%@page import="edu.studentbuzz.helper.MsgDecoder"%>
<%
    UserValidator uv = new UserValidator("student", session);
    if(uv.isValid())
   {
        Student stud = (Student)session.getAttribute("student");
        Post post = new Post();
        Iterator itr = post.find("select * from post where Writer_ID='"+stud.getEmailID()+"' order by timestamp desc").iterator();
        
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Student Buzz</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="../../styles/layout.css" type="text/css" />
<script type="text/javascript">
function deleteconfirm()
{
    return confirm('Are you sure you want to delete this post?');
    
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
        <li><a href="add.jsp">Add Post</a></li>
        <li class="active"> <a href="index.jsp">My Posts</a></li>
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
    <h1>My Posts</h1>
    <%
        if(request.getParameter("msg")!=null) {out.println(new MsgDecoder(Integer.parseInt(request.getParameter("msg"))).getHTML());}
               else if(request.getParameter("error")!=null) {out.println(new ErrorDecoder(Integer.parseInt(request.getParameter("error"))).getHTML());}%>
    <table>
    <thead><tr><th>Post</th><th>Department</th><th>Sub Category</th><th>Last Updated</th><th>View</th><th>Edit</th><th>Delete</th></tr></thead>
    <tbody>    
    <%
    while(itr.hasNext())
    {
        post = (Post) itr.next();
        out.println("<tr align='center' style='border-bottom: 1px solid #CCCCCC;'><td style='width:350px'>"+post.getTextContent().substring(0, (100<post.getTextContent().length())?100:post.getTextContent().length()) +"...</td>");
        out.println("<td>"+post.getDeptName()+"</td>");
        out.println("<td>"+post.getSubCatName()+"</td>");
        out.println("<td>"+post.getTimestamp().toString()+"</td>");
        out.println("<td><a href='view.jsp?postid="+post.getPostID()+"'>View</a></td><td><a href='update.jsp?postid="+post.getPostID()+"'>Edit</a></td><td><a href='../../delete_post.do?postid="+post.getPostID()+"' onclick='return deleteconfirm()'>Delete</td></tr>");
    }

    %>
    </tbody>
    </table>
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
        response.sendRedirect("../../"+uv.genPath());
    }
%>