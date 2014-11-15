<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.studentbuzz.model.Department"%>
<%@page import="edu.studentbuzz.helper.ErrorDecoder"%>
<%@page import="edu.studentbuzz.helper.MsgDecoder"%>
<%
    String stud="",fac="",studfrm="style='display: none;'",facfrm="style='display: none;'";
    if(request.getParameter("role")!=null && request.getParameter("role").equals("1")) {stud="checked='checked'";fac="";studfrm="";}
       else if(request.getParameter("role")!=null && request.getParameter("role").equals("2")) {stud="";fac="checked='checked'";facfrm="";}
    
    Department dept = new Department();
    ArrayList<Department> al = dept.find("select * from department order by name");
    Iterator<Department> itr = al.iterator();
    String deptlist = "<select name='deptid' onchange='load_subcat()' id='deptid'>";
    while(itr.hasNext())
    {
        dept = itr.next();
        deptlist+=("<option value='"+dept.getDeptID() +"'>"+dept.getName()+"</option>");
    }
    deptlist+="</select>";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Student Buzz</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
<!-- Homepage Specific Elements -->
<script type="text/javascript" src="scripts/jquery-1.4.1.min.js"></script>
<script type="text/javascript">
function displayForm()
{
    if ($("#role").is(':checked'))
    {
        $("#faculty").slideUp();
        $("#student").show("slow");
    }
    else
    {
    $("#student").slideUp();
    $("#faculty").show();
    }
}
function load_subcat()
{
       var dept=document.getElementById("deptid").value;
      $.get("gen_subcatlist.do",{deptid:dept},function(data,status){
      document.getElementById("subjects").innerHTML = data;
    });
}
</script>
<!-- End Homepage Specific Elements -->
</head>
<body id="top">
<div class="wrapper row1">
  <div id="header" class="clear">
    <div class="fl_left">
      <h1><a href="index.html">Student Buzz</a></h1>
      <p>Where You and Experts meet!</p>
    </div>
    
    <div id="topnav">
      <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="about.jsp">What is Student Buzz ?</a></li>
        <li class="active"><a href="register.jsp">Register</a></li>
        <li><a href="login.jsp">Login</a></li>
        <li class="last"><a href="contactus.jsp">Contact us</a></li>
      </ul>
    </div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper row3">
  <div id="container" class="clear">
    <!-- ####################################################################################################### -->
    <h1>You are</h1>
    <input type="radio" id="role" name="role" value="1" onclick="displayForm();" <%=stud%>/> Student <input type="radio" name="role" value="2" onclick="displayForm();" <%=fac%>/> Faculty
    <div id="student" <%=studfrm%>>
    <br />
    <h1>Student Registration Form</h1>
    <%
        if(request.getParameter("msg")!=null) {out.println(new MsgDecoder(Integer.parseInt(request.getParameter("msg"))).getHTML());}
           else if(request.getParameter("error")!=null) {out.println(new ErrorDecoder(Integer.parseInt(request.getParameter("error"))).getHTML());}%>
     <div id="respond">
            <form action="add_student.do" method="post">
          <p>
            <label for="name"><small>Name</small></label><br />
            <input type="text" name="name" id="name" size="50"/>
          </p>
          <p>
            <label for="gender"><small>Gender</small></label><br />
            <select name='gender'><option value='1'>Male</option><option value='0'>Female</option></select>
          </p>
          <p>
            <label for="Email"><small>Email</small></label><br />
            <input type="email" name="email" id="email" size="50"/>
          </p>
          <p>
            <label for="password"><small>New Password</small></label><br />
            <input type="password" name="newpassword" id="newpassword" size="50"/>
          </p>
          <p>
            <label for="password"><small>Confirm Password</small></label><br />
            <input type="password" name="cnewpassword" id="cnewpassword" size="50"/>
          </p>
          
          <p>
            <label for="clg"><small>Collage Name</small></label><br />
            <input type="text" name="collage" id="collage" size="50"/>
          </p>
          <p>
            <label for="branch"><small>Branch</small></label><br />
            <input type="text" name="branch" id="branch" size="50"/>
          </p>
          <p>
            <label for="semester"><small>Semester</small></label><br />
            <input type="text" name="semester" id="semester" size="50"/>
          </p>          
          <p><br />
            <input name="submit" type="submit" id="submit" value="Register" />
            &nbsp;
            <a href="index.jsp"><input name="back" type="button" id="reset" tabindex="6" value="Back" /></a>
          </p>
          
        </form>
        </div>
    
    
    </div>
    <div id="faculty" <%=facfrm%>>
    <br />
    <h1>Faculty Registration Form</h1>
    <%
        if(request.getParameter("msg")!=null) {out.println(new MsgDecoder(Integer.parseInt(request.getParameter("msg"))).getHTML());}
               else if(request.getParameter("error")!=null) {out.println(new ErrorDecoder(Integer.parseInt(request.getParameter("error"))).getHTML());}%>
     <div id="respond">
            <form action="add_faculty.do" method="post">
          <p>
            <label for="name"><small>Name</small></label><br />
            <input type="text" name="name" id="name" size="50"/>
          </p>
          <p>
            <label for="gender"><small>Gender</small></label><br />
            <select name='gender'><option value='1'>Male</option><option value='0'>Female</option></select>
          </p>
          <p>
            <label for="Email"><small>Email</small></label><br />
            <input type="text" name="email" id="email" size="50"/>
          </p>
          <p>
            <label for="password"><small>New Password</small></label><br />
            <input type="password" name="newpassword" id="newpassword" size="50"/>
          </p>
          <p>
            <label for="password"><small>Confirm Password</small></label><br />
            <input type="password" name="cnewpassword" id="cnewpassword" size="50"/>
          </p>
          
          <p>
            <label for="clg"><small>Collage Name</small></label><br />
            <input type="text" name="collage" id="collage" size="50"/>
          </p>
          <p>
            <label for="desg"><small>Designation</small></label><br />
            <input type="text" name="designation" id="designation" size="50"/>
          </p>
          <p>
            <label for="dept"><small>Department</small></label><br />
            <%=deptlist%>
          </p>          
          <p>
            <label for="desg"><small>Subjects</small></label><br />
            <div id="subjects">First select a Department</div>
          </p>
          <p><br />
            <input name="submit" type="submit" id="submit" value="Add" />
            &nbsp;
            <a href="index.jsp"><input name="back" type="button" id="reset" tabindex="6" value="Back" /></a>
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
        <li><a href="index.jsp">&raquo; Homepage</a></li>
        <li><a href="#">&raquo; What is Student Buzz?</a></li>
        <li><a href="#">&raquo; Register</a></li>
        <li><a href="#">&raquo; Login</a></li>
        <li><a href="#">&raquo; Contact Us</a></li>
      </ul>
    </div>
    <div class="fl_right">
      <div id="social">
        <h2>Connect With Us</h2>
        <ul>
          <li><a href="#"><img src="images/social/facebook.gif" alt="" /></a></li>
          <li><a href="#"><img src="images/social/twitter.gif" alt="" /></a></li>
          <li><a href="#"><img src="images/social/flickr.gif" alt="" /></a></li>
          <li><a href="#"><img src="images/social/youtube.gif" alt="" /></a></li>
          <li class="last"><a href="#"><img src="images/social/rss.gif" alt="" /></a></li>
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
    <p class="fl_right">Developed by <a href="contactus.jsp">Anubhav Chodhary | Shravani Mallick</a></p>
  </div>
</div>
</body>
</html>
