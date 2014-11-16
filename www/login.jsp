<%@page import="edu.studentbuzz.helper.ErrorDecoder"%>
<%@page import="edu.studentbuzz.helper.MsgDecoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN"
	dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Student Buzz</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
<!-- Homepage Specific Elements -->
<script type="text/javascript" src="scripts/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="scripts/jquery.timers.1.2.js"></script>
<script type="text/javascript"
	src="scripts/jquery.galleryview.2.1.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.setup.js"></script>
<!-- End Homepage Specific Elements -->
</head>
<body id="top">
	<div class="wrapper row1">
		<div id="header" class="clear">
			<div class="fl_left">
				<h1>
					<a href="index.html">Student Buzz</a>
				</h1>
				<p>Where You and Experts meet!</p>
			</div>

			<div id="topnav">
				<ul>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="about.jsp">What is Student Buzz ?</a></li>
					<li><a href="register.jsp">Register</a></li>
					<li class="active"><a href="login.jsp">Login</a></li>
					<li class="last"><a href="contactus.jsp">Contact us</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- ####################################################################################################### -->
	<div class="wrapper row3">
		<div id="container" class="clear">
			<!-- ####################################################################################################### -->
			<div id="respond">
				<%
        if(request.getParameter("msg")!=null) {out.println(new MsgDecoder(Integer.parseInt(request.getParameter("msg"))).getHTML());}
         else if(request.getParameter("error")!=null) {out.println(new ErrorDecoder(Integer.parseInt(request.getParameter("error"))).getHTML());}
         %>
				<form method="post" action="login.do">
					<center>

						<table style="width: 300px;">
							<thead>
								<tr><th colspan="2">Account Login</th></tr>
							</thead>
							<tbody>
								<tr>
									<td>Email</td>
									<td><input name='email' id="email" size="40px"
										type="text" /></td>
								</tr>
								<tr style="border-bottom: 1px solid #CCCCCC;">
									<td>Password</td>
									<td><input name='password' id="password" size="40px"
										type="password"/></td>
								</tr>
								<tr align="center">
									<td colspan="2"><a href="register.jsp">Register</a></td>
								</tr>
							</tbody>
						</table>
						<input type="submit" value="Login" name="login" />
					</center>
				</form>
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
						<li><a href="#"><img src="images/social/facebook.gif"
								alt="" /></a></li>
						<li><a href="#"><img src="images/social/twitter.gif"
								alt="" /></a></li>
						<li><a href="#"><img src="images/social/flickr.gif"
								alt="" /></a></li>
						<li><a href="#"><img src="images/social/youtube.gif"
								alt="" /></a></li>
						<li class="last"><a href="#"><img
								src="images/social/rss.gif" alt="" /></a></li>
					</ul>
				</div>
			</div>
			<!-- ####################################################################################################### -->
		</div>
	</div>
	<!-- ####################################################################################################### -->
	<div class="wrapper">
		<div id="copyright" class="clear">
			<p class="fl_left">
				All Rights Reserved - <a href="#">StudentBuzz</a>
			</p>
			<p class="fl_right">
				Developed by <a href="contactus.jsp">Anubhav Chodhary | Shravani
					Mallick</a>
			</p>
		</div>
	</div>
</body>
</html>