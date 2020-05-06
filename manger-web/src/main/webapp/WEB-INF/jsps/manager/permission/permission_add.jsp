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
<%pageContext.setAttribute("navinfo", "许可维护"); %>
<%@include file="/WEB-INF/include/navbar.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%--侧边栏--%>
        <%@include file="/WEB-INF/include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="${ctp}/#">首页</a></li>
                <li><a href="${ctp}/#">数据列表</a></li>
                <li class="active">添加</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form role="form" id="edit_form" action="${ctp}/permission/perm/add" method="post">

                        <div class="form-group">
                            <label >父级权限:${permission.name}</label>

                        </div>
                        <input type="hidden" name="pid" value="${permission.id}">
                        <div class="form-group">
                            <label >权限名称</label>
                            <input type="text" class="form-control" name="name" >
                        </div>
                        <div class="form-group">
                            <label >权限图标</label>
                            <input type="text" class="form-control" name="icon" >
                        </div>

                        <div class="form-group">
                            <label >权限url</label>
                            <input type="text" class="form-control" name="url" >
                        </div>

                        <button type="submit" class="btn btn-success" ><i class="glyphicon glyphicon-edit"></i>添加
                        </button>
                        <a type="button" class="btn btn-danger" href="${ctp}/permission/perm/add.html?pid=${permission.id}" ><i class="glyphicon glyphicon-refresh"></i> 重置
                        </a>
                    </form>
                </div>
            </div>
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



    showPageTree("${ctp}/permission/perm/permission.html");
</script>
</body>
</html>
