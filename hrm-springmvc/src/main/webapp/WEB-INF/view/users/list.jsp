<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix ="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="labels.jsp" %>
<div>
	
		<h2>The users</h2>
		<table class="dataTable">
			<thead>
				<tr>
					<th>
						<div class="actions">
							<a href="<c:url value="/users/edit" />">${labelAdd}</a>
						</div>
					</th>
				</tr>
				<tr>
					<th>${labelId}</th>
					<th>${labelUserFirstname}</th>
					<th>${labelUserLastname}</th>
					<th>${labelUserEmail }</th>
					<th>${labelAction }</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty userList}">
				<c:forEach items="${userList}" var="entity">
					<tr>
						<td><a href="<c:url value="/users/edit?id=${entity.id }" />">${entity.id }</a></td>
						<td><c:out value="${entity.name}" /></td>
						<td><c:out value="${entity.lastName}" /></td>
						<td><c:out value="${entity.email}" /></td>
						<td><a href="<c:url value="/users/edit?id=${entity.id }" />">${labelEdit}</a></td>						
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
	
</div>