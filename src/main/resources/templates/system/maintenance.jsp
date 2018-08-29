<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath().toString();
%>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="zh-cn">
<head>
    <title>系统正在维护中…</title>
	<jsp:include page="../commons/meta.jsp"></jsp:include>
    <!-- 自定义CSS -->
    <link rel="stylesheet" href="<%=path %>/static/admui/css/errors.css">
    <!-- 图标 -->
    <link rel="stylesheet" href="<%=path %>/static/admui/fonts/7-stroke/7-stroke.css">
    <script src="<%=path %>/static/admui/plugins/jquery/jquery.min.js"></script>
<script src="<%=path %>/static/admui/plugins/bootstrap/bootstrap.min.js"></script>
</head>

<body class="page-errors page-maintenance layout-full">

<div class="site-page">
    <div class="page vertical-align text-center animation-scale-up page-error">
        <div class="page-content vertical-align-middle">
            <header>
                <i class="icon pe-paint" aria-hidden="true"></i>
                <h3>正在维护中…</h3>
                <p>系统正在维护中，请稍后访问</p>
            </header>
            <footer class="page-copyright">
                <p>&copy; 2016
                    <a href="http://www.bj.xushi.com" target="_blank">序时</a>
                </p>
            </footer>
        </div>
    </div>
</div>

</body>

</html>
