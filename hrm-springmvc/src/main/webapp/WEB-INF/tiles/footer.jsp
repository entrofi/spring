<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix ="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../view/commons/labels-common.jsp" %>
<div>
<h1>Footer</h1>
	<p>${messageLoremIpsum }</p>
	<a href="<c:url value="/contacts" />">Contacts</a>
</div>