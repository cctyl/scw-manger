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

    <form class="form-signin" role="form" id="reset_form" method="post" action="${ctp}/permission/user/reset.html">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户密码找回——确认账号</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="loginacct_input" name="loginacct" value="${errorUser.loginacct}"
                   placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback" id="loginacct_msg"></span>
            <span style="color: red">${msg}</span>
        </div>

        <div class="form-group has-success has-feedback">
            <span>
                <input type="text" class="form-control" id="code_input" name="code" placeholder="请输入邮箱验证码"
                       style="margin-top:10px;">


                <span class="btn" id="verify_code">获取验证码</span>
            </span>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>

        </div>


        <label  class="btn btn-lg btn-success btn-block" id="submit"> 提交</label>
    </form>
</div>

<script type="text/javascript">

    $(function () {

        //点击获取验证码
        $("#verify_code").click(function () {


            var loginacct = $("#loginacct_input").val();
            //校验用户名是否合法
            var result2 = loginacct_check(loginacct);
            if (result2) {
                //邮箱格式正确
                //发送请求，获取验证码
                $.ajax(
                    {
                        url: "${ctp}/permission/user/code",
                        type: "post",
                        data: "loginacct=" + loginacct,
                        success: function (result) {
                            if (result.code == 200) {

                                alert("发送成功,请到邮箱查收");

                            } else {
                                var msg = result.extend.msg;
                                alert("发送失败: "+msg);
                            }
                        }
                    }
                );

            }

        });

        //点击提交表单
        $("#submit").click(function () {

            var loginacct = $("#loginacct_input").val();
            //校验用户名是否合法
            var result2 = loginacct_check(loginacct);


            if (result2) {
                $("#reset_form").submit();
            }
        });

    });


    //账户名校验
    function loginacct_check(loginacct) {

        var reg = /^[a-zA-Z0-9_-]{6,16}$/;
        if (reg.test(loginacct)) {
            return true;
        } else {
            alert("账号格式不正确");
            return false;
        }

    }

</script>
</body>
</html>