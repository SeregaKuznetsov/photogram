<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 09.11.2016
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">

    <title>photogram</title>
</head>
<body>


<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <h3>Profile</h3>
            <h3>Posts</h3>
        </div>
        <div class="span10">
            <h1>
                <%
                    String name = request.getParameter("username");
                    if (name == null || name.length() == 0) {
                %>
                Hello, world !
                <%            } else {
                %>
                Hello, world ! I'm <%= name%>
                <%
                    }
                %>
            </h1>
        </div>
    </div>
</div>
<%--<h1>
    <%
        String name = request.getParameter("username");
        if (name == null || name.length() == 0) {
    %>
    Hello, world !
    <%            } else {
    %>
    Hello, world ! I'm <%= name%>
    <%
        }
    %>
</h1>--%>
</body>
</html>
