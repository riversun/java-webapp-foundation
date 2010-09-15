<%@page import="java.util.Date"%>
<%@page import="org.riversun.string_grabber.StringGrabber"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="true"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.net.URLEncoder"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>


<c:set var="header_title" value="Logged out" scope="request"></c:set>
<c:import url="include_00_header.jsp" charEncoding="UTF-8" />
<br />
Successfully logged out.

</body>
</html>