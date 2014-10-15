<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix ="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="commons/labels-common.jsp" %>
<div>
   <h2>${messageLogin }</h2>
   
   <p>
    If you've been using Spitter from your phone,
    then that's amazing...we don't support IM yet.
   </p>
   
   <s:url var="authUrl" 
          value="/static/j_spring_security_check" /><!--<co id="co_securityCheckPath"/>-->
   <form method="post" class="signin" action="${authUrl}">
   
    <fieldset>
    <table cellspacing="0">
    <tr>
    <th><label for="username_or_email">${labelUsername }</label></th>
    <td><input id="username_or_email" 
               name="j_username" 
               type="text" />  <!--<co id="co_usernameField"/>-->
      </td>
    </tr>
    <tr>
    <th><label for="password">${labelPassword }</label></th>
      <td><input id="password" 
                 name="j_password" 
                 type="password" /> <!--<co id="co_passwordField"/>-->
          <small><a href="/account/resend_password">${labelForgotPassword }</a></small>
      </td>
    </tr>
    <tr>
    <th></th>
    <td><input id="remember_me" 
        name="_spring_security_remember_me" 
        type="checkbox"/> <!--<co id="co_rememberMe"/>-->
        <label for="remember_me" 
               class="inline">${labelRememberMe }</label></td>
    </tr>
    <tr>
    <th></th>
    <td><input name="commit" type="submit" value="${labelLogin }" /></td>
    </tr>
    </table>
    </fieldset>
   </form>
   
   <script type="text/javascript">
    document.getElementById('username_or_email').focus();
   </script>
</div>