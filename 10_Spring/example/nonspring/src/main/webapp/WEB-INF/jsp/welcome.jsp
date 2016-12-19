<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ikant
  Date: 2016. 07. 26.
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<div class='page-header col-md-10 col-md-offset-1'><h1>Blogs</h1></div>
<c:out value="${blogs}"/>
<c:forEach items="${blogs}" var="blog">
    <div class='col-md-8 col-md-offset-2 bg-info' style='cursor: pointer; margin-bottom: 10px;'>
        <h2><c:out value="${blog.title}"/>
            <small> - by <c:out value="${blog.creator}"/></small>
        </h2>
    </div>
</c:forEach>
<div class='col-md-8 col-md-offset-2' style='margin-top: 20px;'>
    <a class='btn btn-primary' href='newblog' role='button'>Post a new blog</a>
</div>
</body>
</html>
