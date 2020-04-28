<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@include file="/WEB-INF/include/css.jsp" %>
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
<%--顶栏--%>
<%pageContext.setAttribute("navinfo", "用户维护"); %>
<%@include file="/WEB-INF/include/navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%--侧边栏--%>
        <%@include file="/WEB-INF/include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;" action="${ctp}/permission/user/list">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" name="search" value="${search}"
                                       placeholder="请输入用户账户/昵称">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" id="del_all"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='add.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="seletive_all" type="checkbox" ></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageInfo.list}" var="user">
                                <tr>
                                    <td>${user.id}</td>
                                    <td><input type="checkbox" class="check"></td>
                                    <td>${user.loginacct}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs"><i
                                                class=" glyphicon glyphicon-check"></i></button>
                                        <button type="button" class="btn btn-primary btn-xs"><i
                                                class=" glyphicon glyphicon-pencil"></i></button>
                                        <button type="button" class="btn btn-danger btn-xs"><i
                                                class=" glyphicon glyphicon-remove"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li>
                                            <a href="${ctp}/permission/user/list?page=1&size=${pageInfo.pageSize}&search=${search}"
                                               aria-label="Previous">首页</a>
                                        </li>
                                        <li>
                                            <a
                                                    <c:if test="${pageInfo.hasPreviousPage}">href="${ctp}/permission/user/list?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}&search=${search}" </c:if> >上一页</a>
                                        </li>
                                        <c:forEach begin="${(pageInfo.pageNum-4)<=0?1:(pageInfo.pageNum-4)}"
                                                   end="${ (pageInfo.pageNum+5) > pageInfo.pages ? pageInfo.pages : (pageInfo.pageNum+5)}"
                                                   var="pageNum">
                                            <li class="${pageNum==pageInfo.pageNum?"active":""}">
                                                <a href="${ctp}/permission/user/list?page=${pageNum}&size=${pageInfo.pageSize}&search=${search}">${pageNum}</a>
                                            </li>
                                        </c:forEach>

                                        <li>
                                            <a <c:if test="${pageInfo.hasNextPage}">
                                                href="${ctp}/permission/user/list?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}&search=${search}"
                                            </c:if>
                                            >下一页</a>
                                        </li>
                                        <li>
                                            <a href="${ctp}/permission/user/list?page=${pageInfo.pages}&size=${pageInfo.pageSize}&search=${search}"
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
    $("tbody .btn-success").click(function () {
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function () {
        window.location.href = "edit.html";
    });


    showPageTree("${ctp}/permission/user/list");

    //勾选全部
    $("#seletive_all").click(function () {

        //获取当前全选框的状态：是选中还是没选中
        var flag = $(this).prop("checked");
        //将下面每个emp前面的选择框都设为和全选框一样的状态
        $(".check").prop("checked", flag);

        }
    );

    //给每个用户前面的选项框添加点击事件
  /*  $(".check").click(function () {

        alert( $(this).parents("tr").find("td:eq(0)").text());

    });
*/
    //给删除全部按钮添加事件
    $("#del_all").click(function () {

        var names = "";
        var del_ids = "";
        //如果选中的用户数量大于0，就执行删除方法
        if($(".check:checked").length>0){

            //拿到选中的用户  他们的id和用户名
            $.each($(".check:checked"),function () {
                names = names +  $(this).parents("tr").find("td:eq(2)").text() + ",";
                del_ids = del_ids +  $(this).parents("tr").find("td:eq(0)").text() + "-";

            });
            //把末尾多出来的那一个逗号删掉
            names = names.substring(0,names.length-1);
            del_ids = del_ids.substring(0, del_ids.length - 1);

            alert("是否要删除以下员工：" + names);
            //点击删除就弹出是否要删除这些员工
            // if () {
                //点确认就发送ajax请求
                $.ajax(
                    {
                        url: "${ctp}/permission/user/del?ids=" + del_ids,
                        type: "post",
                        success: function (result) {
                            alert(result);
                            location.href=window.location.href;
                        }
                    }

                );

            // }
        }



    });


</script>
</body>
</html>
