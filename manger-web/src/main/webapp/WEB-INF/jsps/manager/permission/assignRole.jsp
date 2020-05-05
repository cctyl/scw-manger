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
    <link rel="stylesheet"  href="${ctp}/css/main.css">
    <link rel="stylesheet"  href="${ctp}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }
    </style>
</head>

<body>
<%pageContext.setAttribute("navinfo", "用户维护"); %>
<%@include file="/WEB-INF/include/navbar.jsp"%>

<div class="container-fluid">
    <div class="row">

        <%@include file="/WEB-INF/include/sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a  href="${ctp}/#">首页</a></li>
                <li><a  href="${ctp}/#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label >未分配角色列表</label><br>
                            <select class="form-control unroles" multiple size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${unroles}" var="role">
                                    <option value="${role.id }">${role.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label >已分配角色列表</label><br>
                            <select class="form-control roles_select" multiple size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${roles }" var="role">
                                    <option value="${role.id }">${role.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/include/js.jsp"%>
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
    showPageTree("${ctp}/permission/user/list.html");


    //给向右箭头添加点击事件
    $(".glyphicon-chevron-right").click(function () {


        var rids = "";
        //遍历拿到角色id
        var ridlist =  $(".unroles :selected");
        ridlist.each(function () {
            rids= rids+$(this).val()+"-";
        });

        //去掉末尾多出来的-
        rids = rids.substring(0, rids.length - 1);

        //把 选中的角色拿到，添加到另一个列表
        $(".unroles :selected").appendTo(".roles_select");

        //jsp居然可以直接拿到当前url后面的参数
        var uid = ${param.id};


        $.post("${ctp}/permission/user/assignRole",{rids : rids,uid : uid,opt:"add"},function (data) {

            if(data.code==200){

               alert("添加成功");
            }else {
                alert("添加失败")
            }


        });

    });

    //给向左箭头添加点击事件
    $(".glyphicon-chevron-left").click(function () {

        var rids = "";
        //遍历拿到角色id
        var ridlist =  $(".roles_select :selected");
        ridlist.each(function () {
            rids= rids+$(this).val()+"-";
        });

        //去掉末尾多出来的-
        rids = rids.substring(0, rids.length - 1);

        //jsp居然可以直接拿到当前url后面的参数
        var uid = ${param.id};


        $.post("${ctp}/permission/user/assignRole",{rids : rids,uid : uid,opt:"del"},function (data) {

            if(data.code==200){
                alert("删除成功");
                console.log(rids);
            }else {
                alert("删除失败")
            }


        });

        //把 选中的角色拿到，添加到另一个列表
        $(".roles_select :selected").appendTo(".unroles");
    });
</script>
</body>
</html>
