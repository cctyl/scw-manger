<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${ctp}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctp}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctp}/css/main.css">
    <link rel="stylesheet" href="${ctp}/css/doc.min.css">
    <link rel="stylesheet" href="${ctp}/ztree/zTreeStyle.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }
    </style>
</head>

<body>
<%pageContext.setAttribute("navinfo", "许可维护"); %>
<%@include file="/WEB-INF/include/navbar.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                </div>
                <div class="panel-body">
                    <ul>
                        <li>
                            <span>
                                    <i class="glyphicon glyphicon-dashboard"></i>
                                    系统权限菜单
                                    <span>
                                            <button type="button" id="0" class="btn btn-success btn-xs tooltip-test"
                                                  data-toggle="tooltip" title="添加子权限">
                                                <i class="fa fa-fw fa-plus rbg "></i>
                                            </button>
                                    </span>
                            </span>
                            <div class="container-fluid">
                                <div class="row">
                                    <ul>
                                        <%--父菜单--%>
                                        <c:forEach items="${sort}" var="tPermission">
                                        <li>
                                            <div class="col-md-8 ">
                                                <i class="${tPermission.icon}"></i>
                                                    ${tPermission.name}
                                            </div>

                                            <div class="col-md-4">
                                                <button type="button"
                                                        class="btn btn-primary btn-xs tooltip-test"
                                                        data-toggle="tooltip" title="修改权限"><i
                                                        class="fa fa-fw fa-edit rbg "></i></button>
                                                <button type="button" id="${tPermission.id}"
                                                        class="btn btn-success btn-xs tooltip-test"
                                                        data-toggle="tooltip" title="添加子权限"><i
                                                        class="fa fa-fw fa-plus rbg "></i></button>

                                                <button type="button" id="${tPermission.id}"
                                                        class="btn btn-danger btn-xs tooltip-test" data-toggle="tooltip"
                                                        msg="${tPermission.name}"
                                                        title="删除权限以及子权限"><i class="fa fa-fw fa-times rbg "></i>
                                                </button>
                                            </div>


                                            <%--子菜单--%>
                                            <ul>
                                                <c:forEach items="${tPermission.childs}" var="cPermission">
                                                    <li>
                                                                <span>

                                                                                <div class="col-md-8">
                                                                                    <i class="${cPermission.icon}"></i>
                                                                                        ${cPermission.name}
                                                                                </div>

                                                                                <div class="col-md-4">

                                                                                    <button type="button"
                                                                                            class="btn btn-primary btn-xs tooltip-test"
                                                                                            data-toggle="tooltip"
                                                                                            title="修改权限"><i
                                                                                            class="fa fa-fw fa-edit rbg "></i></button>


                                                                                    <button type="button"
                                                                                            id="${cPermission.id}"
                                                                                            class="btn btn-success btn-xs tooltip-test"
                                                                                            data-toggle="tooltip"
                                                                                            title="添加子权限"><i
                                                                                            class="fa fa-fw fa-plus rbg "></i></button>

                                                                                    <button type="button"
                                                                                            id="${cPermission.id}"
                                                                                            class="btn btn-danger btn-xs tooltip-test"
                                                                                            data-toggle="tooltip"
                                                                                            msg="${cPermission.name}"

                                                                                            title="删除权限以及子权限"><i
                                                                                            class="fa fa-fw fa-times rbg "></i></button>

                                                                                </div>


                                                                    </span>
                                                                <%--第三层--%>
                                                        <ul>
                                                            <c:forEach items="${cPermission.childs}" var="dPermission">
                                                                <li>
                                                                                <span>

                                                                                            <div class="col-md-8">
                                                                                                <i class="${dPermission.icon}"></i>
                                                                                                    ${dPermission.name}
                                                                                            </div>

                                                                                            <div class="col-md-4">

                                                                                                <button type="button"
                                                                                                        class="btn btn-primary btn-xs tooltip-test"
                                                                                                        data-toggle="tooltip"
                                                                                                        title="修改权限"><i
                                                                                                        class="fa fa-fw fa-edit rbg "></i></button>


                                                                                                <button type="button"
                                                                                                        id="${dPermission.id}"
                                                                                                        class="btn btn-success btn-xs tooltip-test"
                                                                                                        data-toggle="tooltip"
                                                                                                        title="添加子权限"><i
                                                                                                        class="fa fa-fw fa-plus rbg "></i></button>

                                                                                                <button type="button"
                                                                                                        id="${dPermission.id}"
                                                                                                        class="btn btn-danger btn-xs tooltip-test"
                                                                                                        data-toggle="tooltip"
                                                                                                        msg="${dPermission.name}"
                                                                                                        title="删除权限以及子权限"><i
                                                                                                        class="fa fa-fw fa-times rbg "></i></button>

                                                                                            </div>


                                                                                </span>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctp}/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctp}/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctp}/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctp}/script/docs.min.js"></script>

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

    //添加悬浮提示
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });

    //给删除按钮添加点击事件
    $(".btn-danger").click(function () {
        var id = $(this).attr("id");
        var msg = $(this).attr("msg");
        var result = confirm("确定要删除id:" + id+msg+"吗？");
        if (result == true) {
            $.ajax(
                {
                    url: "${ctp}/permission/perm/del?id=" + id,
                    type: "post",
                    success: function (result) {
                        if (result.code == 200) {

                            alert("删除成功");
                            location.href = window.location.href;
                        } else {
                            alert("删除失败");
                        }
                    }
                }
            );
        }

    });


    //给添加按钮加上点击事件
    $(".btn-success").click(function () {
        var pid = $(this).attr("id");

        location.href = " ${ctp}/permission/perm/add.html?pid=" + pid;
    });

</script>
</body>
</html>