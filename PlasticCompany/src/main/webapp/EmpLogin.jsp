<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<f:view>
		<h:form id="form">

			<h:outputLabel for="username">Username:</h:outputLabel>
			<h:inputText id="username" value="#{emplogin.username}" />
			<span class="message"> <h:message for="username"
					style="display: block; color: red; margin-top: 0rem;" />
			</span>
			<br />
			
			<h:outputLabel for="password">Password:</h:outputLabel>
			<h:inputText id="password" value="#{emplogin.password}" />
			<span class="message"> <h:message for="password"
					style="display: block; color: red; margin-top: 0rem;" />
			</span>
			<br />
			
			<h:commandButton action="#{employeController.empLogin(emplogin)}" value="Login" />
		</h:form>
	</f:view>
</body>
</html>