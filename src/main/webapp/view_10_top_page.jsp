<%@page import="java.util.Date"%>
<%@page import="org.riversun.string_grabber.StringGrabber"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="true"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="webapp.models.PersonInfo"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>

<!-- example for query param "title" usage -->
<c:if test="${empty param.title}">
	<c:set var="title_name" value="Top Page" />
</c:if>

<c:if test="${!empty param.title }">
	<c:set var="title_name" value="${param.title}" />
</c:if>


<c:set var="header_title" value="${title_name}" scope="request"></c:set>
<c:set var="body_class" value="top" scope="request"></c:set>
<c:import url="include_00_header.jsp" charEncoding="UTF-8" />
<script>
	
</script>

<div style="text-align: left; background-color: #f0f0f0">
	<strong>Secret contents Today: <jsp:useBean id="today"
			class="java.util.Date" scope="page" /> <fmt:formatDate
			value="${today}" pattern="E MM/dd/yyyy" />
	</strong>
</div>
<c:choose>
	<c:when
		test="${!empty sessionScope.SESSION_KEY_IS_USER_LOGGED_IN && sessionScope.SESSION_KEY_IS_USER_LOGGED_IN==true}">
	</c:when>

	<c:otherwise>
		<br />
				You can see password when you log in.<br />
	</c:otherwise>
</c:choose>
<br />
<div>
	<c:forEach var="pi" items="${requestScope.personInfoList}"
		varStatus="status">


		<b>(<c:out value="${status.index}" />)
		</b>
		<b>name is ${pi.name}.</b>
		<b>password is ${pi.password}.</b>

		<%
			//Get attribute as a java object
				PersonInfo personInfo = (PersonInfo) pageContext.getAttribute("pi");
		%>

Lives in
		<c:choose>
			<c:when test="${pi.area=='north'}">
NORTH AREA.
			</c:when>
			<c:when test="${pi.area=='center'}">
CENTER AREA.
			</c:when>
			<c:otherwise>
SOUTH AREA.
			</c:otherwise>
		</c:choose>
		<hr />
	</c:forEach>

</div>


<%@ include file="include_01_footer.jsp"%>





</body>
</html>