<%--
  Created by IntelliJ IDEA.
  User: greatlin
  Date: 18-9-13
  Time: 下午1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>欢迎登录XXX管理系统</title>
    <%@ include file="/views/commons/common-top.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/login.css" type="text/css" />
</head>
<body>
    <div class="login">
        <div class="message">XXX管理系统-管理登录</div>
        <div id="darkbannerwrap"></div>
        <form id="loginForm">
            <input id="username" name="username" placeholder="用户名" required="" type="text">
            <hr class="hr15" style="opacity:0">
            <input id="password" placeholder="密码" required="" type="password">
            <input id="realPass" name="password" type="hidden" value="">
            <hr class="hr15" style="opacity:0">
            <input id="verifyCode" name="verifyCode" placeholder="验证码" required="" type="text" style="width:50%;float:left">
            <img class="vcodeImg" onclick="newVcoed()" title="看不清?换一张" style="margin-left:20px;" src="${ pageContext.request.contextPath}/vcode">
            <hr class="hr15" style="opacity:0">
            <input id="loginButton" value="登录" style="width:100%;" type="button">
            <hr class="hr20" style="opacity:0">
        </form>

    </div>

    <%@ include file="/views/commons/commons-bottom.jsp"%>
    <script src="${pageContext.request.contextPath}/statics/js/security/rsa.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/login/login.js"></script>
    <script type="text/javascript">
        var pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI/762w8Dttofmsqylc+zrEoWqjpmf3ZnQA7jiVV5LlHV12qWk/HJJhzoebDCyxVSlNtxYKyT7gde96QpIt4QPihdR25hXkQxxAuWPkGtMnq9vsnaPO8H2OINujsIlHF0QOj7SWYzOfvO6PFHpJO0RkMs+Tqua4PhCzjwI9P+lzQIDAQAB";
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey(pub);
        var password = encrypt.encrypt("sslgo123456");
        console.log(password);
    </script>
</body>
</html>
