<%@page import="java.util.Iterator"%>
<%@page import="edu.studentbuzz.model.Attachment"%>
<%@page import="edu.studentbuzz.model.Post"%>
<%@page import="edu.studentbuzz.helper.UserValidator"%>
<%@page import="edu.studentbuzz.model.Student"%>
<%@page import="edu.studentbuzz.helper.ErrorDecoder"%>
<%@page import="edu.studentbuzz.helper.MsgDecoder"%>
<%
    UserValidator uv = new UserValidator("student", session);
    if(uv.isValid())
   {
        String postid = (request.getParameter("postid")!=null)?request.getParameter("postid"):"";
        if(!postid.equals(""))
        {
            Post post = new Post(Long.parseLong(postid));
            post.findById();
            if(post.getPostID()!=null && post.getWriterID().equals(((Student)session.getAttribute("student")).getEmailID()))
            {
                Attachment att = new Attachment();
                Iterator itr = att.find("select * from attachment where Post_ID='"+post.getPostID()+"'").iterator();
            
        
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
    return confirm('Are you sure you want to delete this attachment?');
    
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
     <h1>Attachments of Post</h1>
     <%
        if(request.getParameter("msg")!=null) {out.println(new MsgDecoder(Integer.parseInt(request.getParameter("msg"))).getHTML());}
               else if(request.getParameter("error")!=null) {out.println(new ErrorDecoder(Integer.parseInt(request.getParameter("error"))).getHTML());}%>
     <table>
         <thead><tr><th>Attachment</th><th>Size</th><th>Download</th><th>Delete</th></tr></thead>
         <tbody>
         <%
            while(itr.hasNext())
            {
                att = (Attachment) itr.next();
                out.print("<tr align='center'><td>"+att.getFilename()+"</td><td>"+att.getSize()+" Byte</td><td><a href='../../attachments/"+att.getUrl()+"'>Download</a></td><td><a href='../../delete_attachment.do?attid="+att.getAttachmentID()+"' onclick='return deleteconfirm();'>Delete</a></td></tr>");
            }

        %>
        </tbody>
     </table>
     <h1>Add Attachment</h1>
     <div id="respond">
         <form method='post' action='../../add_attachment.do?postid=<%=post.getPostID()%>' enctype='multipart/form-data' >
         <p>
            <label for="attachment"><small>Select your file</small></label><br />
            <input type="file" name="attachment" id="attachment" />
          </p>
         <p><br />
                    <input  name="submit" type="submit" id="submit" value="Upload" /> 
                    <a href="update.jsp?postid=<%=post.getPostID()%>"><input  name="submit" type="button" id="submit" value="Back" /> </a>

          </p>
     </form>
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
                response.sendRedirect("index.jsp?error=18");
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