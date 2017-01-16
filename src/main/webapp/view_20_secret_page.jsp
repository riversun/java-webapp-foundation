<%@page import="java.util.Date"%>
<%@page import="org.riversun.string_grabber.StringGrabber"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="true"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="webapp.models.SecretInfo"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>

<c:set var="header_title" value="My Secret Page" scope="request"></c:set>
<c:import url="include_00_header.jsp" charEncoding="UTF-8" />

<div style="text-align: left; background-color: #f0f0f0">
	<strong>Secret contents Today: <jsp:useBean id="today"
			class="java.util.Date" scope="page" /> <fmt:formatDate
			value="${today}" pattern="E MM/dd/yyyy" />
	</strong>
</div>
<br />
<c:if test="${fn:length(requestScope.secretInfoList) >0}">

	<section>

		<c:forEach var="obj" items="${secretInfoList}" varStatus="status">
			<%
				//as code
						SecretInfo favoriteProgSeason = (SecretInfo) pageContext.getAttribute("obj");
			%>


			<b>(<c:out value="${status.index}" />)
			</b>
			<b>${obj.secretName}</b>
			<br />


		</c:forEach>
	</section>

</c:if>


<%@ include file="include_01_footer.jsp"%>


</body>
</html>