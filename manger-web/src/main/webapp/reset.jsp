<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%@include file="WEB-INF/include/js.jsp" %>
    <%@include file="WEB-INF/include/css.jsp" %>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="${ctp}/index.jsp" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form class="form-signin" role="form" method="post" action="${ctp}/permission/user/reset">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户密码找回</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="loginacct_input" name="loginacct" value="${errorUser.loginacct}" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback" ></span>
            <span style="color: red">${msg}</span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="email_input" name="email" value="${errorUser.email}" placeholder="请输入邮箱" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="code_input" name="email"  placeholder="请输入邮箱验证码" style="margin-top:10px;">

            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <span class="btn">获取验证码</span>

        </div>


        <button type="submit" class="btn btn-lg btn-success btn-block"> 提交</button>
    </form>
</div>

</body>
</html>