<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%@include file="/WEB-INF/include/js.jsp" %>
    <%@include file="/WEB-INF/include/css.jsp" %>
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

    <form class="form-signin" role="form" id="reset_form" method="post" action="${ctp}/permission/user/reset">
        <h2 class="form-signin-heading"> 密码找回——重置密码</h2>
        <div class="form-group has-success has-feedback">

            <input type="text" class="form-control" id="loginacct_input" name="loginacct" readonly  value="${resetUser}"
                    autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback" id="loginacct_msg"></span>

        </div>

        <div class="form-group has-success has-feedback">
            <span>
               输入新密码： <input type="password" class="form-control"  name="password" placeholder="请输入邮箱验证码"
                       style="margin-top:10px;">
            </span>

        </div>


        <label  class="btn btn-lg btn-success btn-block" id="submit"> 提交</label>
    </form>
</div>

<script type="text/javascript">

    $(function () {



        //点击提交表单
        $("#submit").click(function () {

                $("#reset_form").submit();
        });

    });




</script>
</body>
</html>