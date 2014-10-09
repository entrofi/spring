<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix ="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut
		auctor sapien eu tristique laoreet. Suspendisse porta cursus dui a
		volutpat. Nulla fermentum imperdiet odio, sed bibendum arcu porttitor
		ut. Mauris sodales metus non metus laoreet condimentum. Aliquam
		dignissim dolor enim, et faucibus erat dignissim sit amet. Nam a mi
		pellentesque, pellentesque leo vitae, suscipit turpis. Nullam ipsum
		arcu, elementum quis sagittis ut, interdum in metus. Nulla in lobortis
		orci, id interdum augue. Vestibulum scelerisque ac nulla non ultrices.</p>
	<a href="<c:url value="/contacts/list" />"><s:message code="label.Contacts"/></a>
</div>