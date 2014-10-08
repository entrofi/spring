<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix ="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix ="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<sf:form method="POST" modelAttribute="contact">
		<fieldset>
			<table cellspacing="0">
				<TR >
					<TH>
						<sf:label path="firstName">First Name</sf:label>
					</TH>
					<TD>
						<sf:input path="firstName" size="15" />
						<sf:errors path="firstName" cssClass="fieldError"/>
					</TD>
				</TR>
				<TR >
					<TH>
						<sf:label path="lastName">Last Name</sf:label>
					</TH>
					<TD>
						<sf:input path="lastName" size="15" />
						<sf:errors path="lastName" cssClass="fieldError"/>
					</TD>
				</TR>
				<TR >
					<TH>
						<sf:label path="description">Description</sf:label>
					</TH>
					<TD>
						<sf:textarea path="description" size="15" />
						<sf:errors path="description" cssClass="fieldError"/>
					</TD>
				</TR>
				<TR >
					<TD>
						<input type="submit" name="commit" value="save" />
					</TD>
				</TR>
			</table>
		</fieldset>
	</sf:form>

</div>