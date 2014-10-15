<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix ="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="labels.jsp" %>
<div>
	<c:if test="${not empty entities}">
		<h2>The contacts list</h2>
		<table class="dataTable">
			<thead>
				<tr>
					<th>ListTable</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${Entities}" var="entity">
					<tr>
						<td><c:out value="${entity}" /></td>
						<td><a href="<c:url value="/entity/edit?id=${entity.id }" />">${labelEdit }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>