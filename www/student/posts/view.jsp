<%@page import="edu.studentbuzz.model.Comment"%>
<%@page import="java.util.Iterator"%>
<%@page import="edu.studentbuzz.model.Attachment"%>
<%@page import="edu.studentbuzz.model.Faculty"%>
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
            String name="";
            String pic;
            boolean report;
            if(post.getPostID()!=null)
            {
               Student stud = new Student(post.getWriterID());
               stud.findById();
               if(stud.getEmailID()==null)
               {
                   Faculty fac = new Faculty(post.getWriterID());
                   fac.findById();
                   name = fac.getName();
                   pic=fac.getPicture();
                   report = ((Student)session.getAttribute("student")).getEmailID().equals(fac.getEmailID());
               }
               else
               {
               name = stud.getName();
               pic=stud.getPicture();
               report = ((Student)session.getAttribute("student")).getEmailID().equals(stud.getEmailID());
               }
               
               boolean attachment =false;
               Attachment att = new Attachment();
               if(!att.find("select * from attachment where Post_ID='"+post.getPostID()+"'").isEmpty())
               {
                   attachment = true;
               }
               
               Iterator itr = att.find("select * from attachment where Post_ID='"+post.getPostID()+"'").iterator();
               
               Comment cmm = new Comment();
               Iterator itr2 = cmm.find("select * from comment where post_id='"+post.getPostID()+"' order by timestamp").iterator();
               boolean del_comm1=(post.getWriterID().equals(((Student)session.getAttribute("student")).getEmailID()));
        
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
    return confirm('Are you sure you want to delete this comment?');
    
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
        <%
        if(request.getParameter("msg")!=null) {out.println(new MsgDecoder(Integer.parseInt(request.getParameter("msg"))).getHTML());}
               else if(request.getParameter("error")!=null) {out.println(new ErrorDecoder(Integer.parseInt(request.getParameter("error"))).getHTML());}%>
      <h1><a href="../account/view.jsp?email=<%=post.getWriterID()%>"><img class="avatar" src="../../profile_pictures/<%=pic%>" width="32" height="32" alt="" align="middle"/>  <%=name%> </a> at <%=post.getTimestamp()%></h1>
      <p><%=post.getTextContent()%></p>      
      <p>Category : <a href="../posts/show_subject.jsp?subid=<%=post.getSubcatID()%>"><%=post.getDeptName()%>  : <%=post.getSubCatName()%></a> <%if(!report){%>| <a href="add_report.jsp?postid=<%=post.getPostID()%>">Report Abuse</a><%}%></p>
      <%if(attachment){%>
      <h1>Attachments</h1>
      <ul>
          <%
            while(itr.hasNext())
            {
                att = (Attachment) itr.next();
                out.println("<li> "+att.getFilename()+ " ("+att.getSize()+" Bytes) <a href='../../attachments/"+att.getUrl()+"'>Download</a>");
            }
            %>
      </ul><%}%>
    <div id="comments">
        <h2>Comments</h2>
        <ul class="commentlist">
            <%  short i=0;
            Faculty fac = new Faculty();
            Student studn = new Student();
            String picture,cmm_name; 
            boolean del_comm2=false;
                while(itr2.hasNext())
                {
                    cmm = (Comment) itr2.next();
                   out.println((i==0)?"<li class='comment_odd'>":"<li class='comment_even'>");
                   if(i==0) i=1; else i=0;
                   fac.setEmailID(cmm.getWriteID());
                   fac.findById();
                   if(fac.getEmailID()==null) 
                   {
                       studn.setEmailID(cmm.getWriteID());
                       studn.findById();
                       picture=studn.getPicture();
                       cmm_name=studn.getName();
                   }
                   else
                   {
                       picture=fac.getPicture();
                       cmm_name=fac.getName();
                   }
                   del_comm2=(cmm.getWriteID().equals(((Student)session.getAttribute("student")).getEmailID()));
                  out.println("<div class='author'><img class='avatar' src='../../profile_pictures/"+picture+"' width='32' height='32' alt='' /><span class='name'><a href='../account/view.jsp?email="+cmm.getWriteID()+"' name='"+cmm.getCommentID()+"'>"+cmm_name+"</a></span> <span class='wrote'>wrote:</span></div>");
                  out.println("<div class='submitdate'><a href='#'>"+cmm.getTimestamp().toString()+"</a></div>");
                  out.println("<p>"+cmm.getTextContent()+"</p>");
                  if(del_comm1 || del_comm2)out.println("<p><a href='../../delete_comment.do?commentid="+cmm.getCommentID()+"' onclick='return deleteconfirm()'>Delete</a></p>");
                  out.print("<li>");
                  
                }
            %>


        </ul>
      </div>
      <h2>Write A Comment</h2>
      <div id="respond">
        <form action="../../add_comment.do" method="post">
          <p>
          <label for="name"><small>Your comment here</small></label>
            <textarea name="comment" id="comment" cols="100%" rows="10"></textarea>
            <input name="postid" type="hidden" value="<%=post.getPostID()%>" />
          </p>
          <p>
            <input name="submit" type="submit" id="submit" value="Comment" />
            &nbsp;
            <input name="reset" type="reset" id="reset" tabindex="5" value="Clear" />
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