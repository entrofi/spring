<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix ="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix ="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="labels.jsp" %>
<div>
	<sf:form method="POST" modelAttribute="user" action="${loyloy }" >
		<fieldset>
			<table>
				<thead>
					<tr>
						<td colspan="1">How do we put messages?? </td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<sf:label path="name">${labelUserFirstname }</sf:label>
						</th>
						<td>
							<sf:input path="name" size="15" />
							<sf:errors path="name" cssClass="fieldError"/>
						</td>
						
						<th>
							<sf:label path="lastName">${labelUserLastname}</sf:label>
						</th>
						<td>
							<sf:input path="lastName" size="15" />
							<sf:errors path="lastName" cssClass="fieldError"/>
						</td>
					</tr>
					<tr>
						<th>
							<sf:label path="email">${labelUserEmail }</sf:label>
						</th>
						<td>
							<sf:input path="email" size="15" />
							<sf:errors path="email" cssClass="fieldError"/>
						</td>
						
						<th>
							<sf:label path="password">${labelUserPassword}</sf:label>
						</th>
						<td>
							<sf:password path="password" />
							<sf:errors path="password" cssClass="fieldError"/>
						</td>
					</tr>
					<tr>
						<th>
							<sf:label path="username">${labelUserUsername }</sf:label>
						</th>
						<td>
							<sf:input path="username" size="15" />
							<sf:errors path="username" cssClass="fieldError"/>
						</td>
					</tr>
					<tr >
						<td>
							<input type="submit" name="buttonFormAction" value="${labelSave}"  />
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</sf:form>

</div>