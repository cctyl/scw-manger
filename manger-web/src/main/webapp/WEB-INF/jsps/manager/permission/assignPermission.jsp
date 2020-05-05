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
<%pageContext.setAttribute("navinfo", "角色维护"); %>
<%@include file="/WEB-INF/include/navbar.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限分配列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <button class="btn btn-success">分配许可</button>
                    <br><br>
                    <ul >
                        <li >
                            <input type="checkbox" class="button chk checkbox_true_full" treenode_check=""></input>
                                <span >
                                    <i class="glyphicon glyphicon-dashboard"></i>
                                    系统权限菜单
                                </span>
                            <ul >
                                <%--父菜单--%>
                                <c:forEach items="${sort}" var="tPermission">
                                    <li >
                                        <input type="checkbox" ${tPermission.chk}/>

                                        <span>
                                            <i class="${tPermission.icon}"></i>
                                            ${tPermission.name}
                                            <span class="badge" style="float:right">${fn:length(tPermission.childs)}</span>
                                        </span>
                                        <%--子菜单--%>
                                        <ul>
                                            <c:forEach items="${tPermission.childs}" var="cPermission">
                                                <li >
                                                    <input type="checkbox" ${cPermission.chk} />
                                                    <span>
                                                        <i class="${cPermission.icon}"></i>
                                                            ${cPermission.name}
                                                    </span>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:forEach>
                            </ul>
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
    showPageTree("${ctp}/permission/role/list.html");

</script>
</body>
</html>
