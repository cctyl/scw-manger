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


    <script type="text/javascript">


        //给校验器设置一些策略
        $.validator.setDefaults({
            showErrors:function(map, list) {

                //将每个元素自己的错误信息显示在自己的图标下
                //先清除所有错误；清状态
                $(".errorinfo").empty();
                $(".form-group").removeClass("has-success has-error has-warning")

                $.each(list,function(){
                    //当前发生错误的详细信息；
                    //element当前错误元素
                    //错误信息
                    $(this.element).nextAll(".errorinfo").text(this.message);
                    //改变input的状态；
                    $(this.element).parent("div.form-group").addClass("has-error")


                })
            }
        });
        $().ready(function () {
            //输入你的表单id，调用一个validate方法
            //方法内部通过输入框的 name值，来确定这个输入框要怎么校验
         $("#reg_form").validate({
                //校验规则
                rules: {
                    /* 对 name ="username" 的输入框进行校验*/
                    loginacct: {
                        required: true, /*表示不能为空*/
                        minlength: 6,   /*表示最小长度*/
                        maxlength:10

                    },
                    userpswd: {
                        required: true, /*表示不能为空*/
                        minlength: 6,  /*表示最小长度*/
                        maxlength:10
                    },

                    email:{
                        required:true,
                        email:true
                    }


                },
                //校验不通过时提示的信息
                messages: {
                    //某一项属性不通过时，提示不同的信息
                    loginacct: {
                        required: "必须输入用户名",
                        minlength: "最少6个字",
                        maxlength:"最多10个字"
                    },
                    userpswd: {
                        required: "必须输入密码",
                        minlength: "最少6个字",
                        maxlength:"最多10个字"

                    },
                    email: "请输入正确的邮箱格式"

                }
            });


        });
        //提交表单
        function submit() {

            var role = $("select.form-control").val();
            if (role=="会员"){

                alert("此功能尚未开通");
            }else{

                 $("#reg_form").submit();

            }


        }

    </script>
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

    <form class="form-signin" id="reg_form" role="form" action="${ctp}/permission/user/reg" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" name="loginacct" id="loginacct_input" placeholder="请输入登录账号" value="${TUser.loginacct}"
                   autofocus>
            <span
                    class="glyphicon glyphicon-user form-control-feedback"> </span>
            <span class="errorinfo" style="color: red">${regError}</span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" name="userpswd" id="userpswd_input" placeholder="请输入登录密码"
                   style="margin-top:10px;">
            <span
                    class="glyphicon glyphicon-user form-control-feedback"> </span>
            <span class="errorinfo" style="color: red"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" name="email" id="email_input" value="${TUser.email}" placeholder="请输入邮箱地址"
                   style="margin-top:10px;">
            <span
                    class="glyphicon glyphicon-user form-control-feedback"> </span>
            <span class="errorinfo" style="color: red"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control">
                <option>会员</option>
                <option>管理</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="${ctp}/login.jsp">我有账号</a>
            </label>
        </div>
        <%--        <a calass="btn btn-lg btn-success btn-block" href="${ctp}/member.jsp" > 注册</a>--%>

        <a class="btn btn-lg btn-success btn-block"  onclick="submit()">注册</a>
    </form>
</div>

</body>
</html>