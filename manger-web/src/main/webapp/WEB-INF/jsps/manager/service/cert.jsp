<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
        }
    </style>
</head>

<body>
<%pageContext.setAttribute("navinfo", "资质维护"); %>
<%@include file="/WEB-INF/include/navbar.jsp" %>

<div class="container-fluid">
    <div class="row">

        <%@include file="/WEB-INF/include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;" method="post"
                          action="${ctp}/service/cert/list.html">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" name="search" value="${search}"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="del_all" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" id="add_btn" class="btn btn-primary" style="float:right;"
                    ><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="35">#</th>
                                <th width="30"><input id="seletive_all" type="checkbox"></th>
                                <th width="20">名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageInfo.list}" var="cert">
                                <tr>
                                    <td>${cert.id}</td>
                                    <td><input type="checkbox" class="check"></td>
                                    <td>${cert.name}</td>
                                    <td>

                                        <a type="button" class="btn btn-primary btn-xs edit_btn" id="${cert.id}">
                                            <i class=" glyphicon glyphicon-pencil"></i>
                                        </a>
                                        <a type="button" class="btn btn-danger btn-xs del_btn">
                                            <i class=" glyphicon glyphicon-remove"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li>
                                            <a href="${ctp}/service/cert/list.html?page=1&size=${pageInfo.pageSize}&search=${search}"
                                               aria-label="Previous">首页</a>
                                        </li>
                                        <li>
                                            <a
                                                    <c:if test="${pageInfo.hasPreviousPage}">href="${ctp}/service/cert/list.html?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}&search=${search}" </c:if> >上一页</a>
                                        </li>
                                        <c:forEach begin="${(pageInfo.pageNum-4)<=0?1:(pageInfo.pageNum-4)}"
                                                   end="${ (pageInfo.pageNum+5) > pageInfo.pages ? pageInfo.pages : (pageInfo.pageNum+5)}"
                                                   var="pageNum">
                                            <li class="${pageNum==pageInfo.pageNum?"active":""}">
                                                <a href="${ctp}/service/cert/list.html?page=${pageNum}&size=${pageInfo.pageSize}&search=${search}">${pageNum}</a>
                                            </li>
                                        </c:forEach>

                                        <li>
                                            <a <c:if test="${pageInfo.hasNextPage}">
                                                href="${ctp}/service/cert/list.html?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}&search=${search}"
                                            </c:if>
                                            >下一页</a>
                                        </li>
                                        <li>
                                            <a href="${ctp}/service/cert/list.html?page=${pageInfo.pages}&size=${pageInfo.pageSize}&search=${search}"
                                               aria-label="Next">尾页</a>
                                        </li>
                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://lib.sinaapp.com/js/jquery/2.0.2/jquery-2.0.2.min.js"></script>
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


    //给每个用户后面的删除按钮添加点击事件
    $(".del_btn").click(function () {
        var del_ids = $(this).parents("tr").find("td:eq(0)").text();
        var names = $(this).parents("tr").find("td:eq(2)").text();
        var reslut = confirm("是否要删除以下条目：" + names);

        if (reslut == true) {
            //点确认就发送ajax请求
            $.ajax(
                {
                    url: "${ctp}/service/cert/del?ids=" + del_ids,
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


    //勾选全部
    $("#seletive_all").click(function () {

            //获取当前全选框的状态：是选中还是没选中
            var flag = $(this).prop("checked");
            //将下面每个emp前面的选择框都设为和全选框一样的状态
            $(".check").prop("checked", flag);

        }
    );


    //给删除全部按钮添加事件
    $("#del_all").click(function () {

        var names = "";
        var del_ids = "";
        //如果选中的用户数量大于0，就执行删除方法
        if ($(".check:checked").length > 0) {

            //拿到选中的用户  他们的id和用户名
            $.each($(".check:checked"), function () {
                names = names + $(this).parents("tr").find("td:eq(2)").text() + ",";
                del_ids = del_ids + $(this).parents("tr").find("td:eq(0)").text() + "-";

            });
            //把末尾多出来的那一个逗号删掉
            names = names.substring(0, names.length - 1);
            del_ids = del_ids.substring(0, del_ids.length - 1);

            var reslut = confirm("是否要删除以下条目：" + names);

            if (reslut == true) {

                //点击删除就弹出是否要删除这些员工

                //点确认就发送ajax请求
                $.ajax(
                    {
                        url: "${ctp}/service/cert/del?ids=" + del_ids,
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
        }


    });


    showPageTree("${ctp}/service/cert/list.html");


    //新增分类
    $("#add_btn").click(function () {

        var name = prompt("输入新条目名字");
        if (name.length > 0) {

            $.ajax({
                    url: "${ctp}/service/cert/add",
                    type: "POST",
                    data: "name=" + name,
                    success: function (result) {

                        if (result.code == 200) {
                            location.href = window.location.href;
                        }

                    }
                }
            );


        }


    });


    //修改分类名称
    $(".edit_btn").click(function () {

        var id = $(this).attr("id");

        var name = prompt("输入新名字");
        if (name.length > 0) {

            $.ajax({
                    url: "${ctp}/service/cert/update",
                    type: "POST",
                    data: "id="+id+"&name=" + name,
                    success: function (result) {

                        if (result.code == 200) {
                            location.href = window.location.href;
                        }

                    }
                }
            );


        }

    });

</script>
</body>
</html>
