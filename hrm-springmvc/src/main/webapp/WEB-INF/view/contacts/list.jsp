<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix ="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="labels-common.jsp" %>
<div>
<p>This is contatcs list</p>
	<c:if test="${not empty contacts}">
		<h2>The contacts list</h2>
		<table class="dataTable">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Description</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contacts}" var="contact">
					<tr>
						<td><c:out value="${contact.getFirstName()}" /></td>
						<td>${contact.lastName}</td>
						<td>${contact.description }</td>
						<td><a href="<c:url value="/contacts/edit?contactId=${contact.id }" />">${labelEdit }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>