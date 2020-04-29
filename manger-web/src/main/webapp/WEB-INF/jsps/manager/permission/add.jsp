<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@include file="/WEB-INF/include/css.jsp" %>
    <link rel="stylesheet" href="${ctp}/css/main.css">
    <link rel="stylesheet" href="${ctp}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }
    </style>
</head>

<body>
<%--顶栏--%>
<%pageContext.setAttribute("navinfo", "用户维护"); %>
<%@include file="/WEB-INF/include/navbar.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%--侧边栏--%>
        <%@include file="/WEB-INF/include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="${ctp}/#">首页</a></li>
                <li><a href="${ctp}/#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form role="form" id="edit_form" action="${ctp}/permission/user/add" method="post">
                        <input type="hidden" name="id" >
                        <div class="form-group">
                            <label >登陆账号</label>
                            <input type="text" class="form-control" name="loginacct" >
                            <span class="errorinfo" style="color: red" >${errorMsg==null?"":errorMsg}</span>
                        </div>
                        <div class="form-group">
                            <label >用户密码</label>
                            <input type="password" class="form-control"  name="userpswd" >
                            <span class="errorinfo" style="color: red"></span>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" name="email" >
                            <span class="errorinfo" style="color: red"></span>
                        </div>
                        <button type="submit" class="btn btn-success" ><i class="glyphicon glyphicon-edit"></i> 修改
                        </button>
                        <a type="button" class="btn btn-danger" href="${ctp}/permission/user/add.html" ><i class="glyphicon glyphicon-refresh"></i> 重置
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<%@include file="/WEB-INF/include/js.jsp" %>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });


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
        $("#edit_form").validate({
            //校验规则
            rules: {
                /* 对 name ="loginacct" 的输入框进行校验*/
                loginacct: {
                    required: true, /*表示不能为空*/
                    minlength: 6,   /*表示最小长度*/
                    maxlength:10


                },
                userpswd: {
                    required: true, /*表示不能为空*/
                    minlength: 6 , /*表示最小长度*/
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
                    required: "必须输入昵称",
                    minlength: "最少6个字",
                    maxlength:"最多10个字"
                },
                email: "请输入正确的邮箱格式"

            }
        });

    });
</script>
</body>
</html>
