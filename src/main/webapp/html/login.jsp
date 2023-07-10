<!DOCTYPE html>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page errorPage="error.jsp" isErrorPage="false" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <meta charset="ISO-8859-1">
    <title>H+ Sport</title>
</head>
<body>
<%@include file="header.jsp"%>
<!-- #home -->


<section>
    <%=displayDate()%>
</section>
<section id="login" class="section">
    <div class="container tagline">
        <% if (request.getAttribute("error") != null) { %>
        <em>
            <%=request.getAttribute("error")%>
        </em><br/>
        <%} %>

        <em>LOGIN USER</em>
        <form action="login" method="post">
            <label>Username</label> <input type="text" name="username" id="username"><br/> <label>Password</label> <input
                type="password" name="password" id="password"><br/> <input
                type="submit" value="Login">
        </form>
    </div>
</section>
<!-- #products -->

<%@include file="footer.jsp"%>
<!-- footer -->
<%!

    public String displayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
        Date toDate = Calendar.getInstance().getTime();
        return dateFormat.format(toDate);
    }

%>
</body>
</html>