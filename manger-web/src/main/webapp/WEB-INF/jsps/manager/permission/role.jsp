<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <%@include file="/WEB-INF/include/js.jsp"%>
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
<%pageContext.setAttribute("navinfo", "角色维护"); %>
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
                    <form class="form-inline" role="form" style="float:left;" action="${ctp}/permission/role/list.html">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" name="search" value="${search}"
                                       placeholder="请输入角色名称">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='form.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageInfo.list}" var="role">

                                <tr>
                                    <td>${role.id}</td>
                                    <td><input type="checkbox"></td>
                                    <td>${role.name}</td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs assginPer" data-toggle="modal" data-target="#permissionModel" ><i
                                                class=" glyphicon glyphicon-check"></i></button>
                                        <button type="button" class="btn btn-primary btn-xs"><i
                                                class=" glyphicon glyphicon-pencil"></i></button>
                                        <button type="button" class="btn btn-danger btn-xs"><i
                                                class=" glyphicon glyphicon-remove"></i></button>
                                        <button type="button" class="btn btn-danger btn-xs"><i
                                                class=" glyphicon glyphicon-remove" id="managephoto"></i></button>

                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>
                            <tfoot>

                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li>
                                            <a href="${ctp}/permission/role/list.html?page=1&size=${pageInfo.pageSize}&search=${search}"
                                               aria-label="Previous">首页</a>
                                        </li>
                                        <li>
                                            <a
                                                    <c:if test="${pageInfo.hasPreviousPage}">href="${ctp}/permission/role/list.html?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}&search=${search}" </c:if> >上一页</a>
                                        </li>
                                        <c:forEach begin="${(pageInfo.pageNum-4)<=0?1:(pageInfo.pageNum-4)}"
                                                   end="${ (pageInfo.pageNum+5) > pageInfo.pages ? pageInfo.pages : (pageInfo.pageNum+5)}"
                                                   var="pageNum">
                                            <li class="${pageNum==pageInfo.pageNum?"active":""}">
                                                <a href="${ctp}/permission/role/list.html?page=${pageNum}&size=${pageInfo.pageSize}&search=${search}">${pageNum}</a>
                                            </li>
                                        </c:forEach>

                                        <li>
                                            <a <c:if test="${pageInfo.hasNextPage}">
                                                href="${ctp}/permission/role/list.html?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}&search=${search}"
                                            </c:if>
                                            >下一页</a>
                                        </li>
                                        <li>
                                            <a href="${ctp}/permission/role/list.html?page=${pageInfo.pages}&size=${pageInfo.pageSize}&search=${search}"
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
        <!-- Modal -->
        <div class="modal fade" id="permissionModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        <%--模态框内容部分--%>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


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
