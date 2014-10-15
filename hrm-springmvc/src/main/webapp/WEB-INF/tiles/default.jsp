<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${pageTitle}</title>
<link href="<s:url value="/resources"/>/css/default.css" rel="stylesheet" type="text/css"/>
<s:theme code="css" var="themeCSS"/>
<s:url value="/${themeCSS }" var="themeCSSURL"/>
<link rel="stylesheet" type="text/css" media="screen" href="${themeCSSURL }" />
</head>
<body>
	<div id="body">
		<div id="header">
			<t:insertAttribute name="header" />
		</div>
		<div id="content">
			<t:insertAttribute name="content"/>
		</div>
		<div id="footer">
			<t:insertAttribute name="footer"/>
			
		</div>
	</div>
</body>
</html>