<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="col-sm-3 col-md-2 sidebar">
    <div class="tree">
        <ul style="padding-left:0px;" class="list-group">
            <li class="list-group-item tree-closed">
                <a href="${ctp}/main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a>
            </li>
            <%--父菜单--%>
            <c:forEach items="${userMenus}" var="tPermission">
                <li class="list-group-item tree-closed">
                <span><i class="${tPermission.icon}"></i> ${tPermission.name}
                    <span class="badge" style="float:right">${fn:length(tPermission.childs)}</span>
                </span>
                    <%--子菜单--%>
                    <ul style="margin-top:10px;display:none;">
                        <c:forEach items="${tPermission.childs}" var="cPermission">
                            <li style="height:30px;">
                                <a href="${ctp}/${cPermission.url}" ><i class="${cPermission.icon}"></i> ${cPermission.name}</a>
                            </li>

                        </c:forEach>


                    </ul>
                </li>
            </c:forEach>


        </ul>
    </div>
</div>

<script type="text/javascript">
    function showPageTree(url){
        $("a[href='"+url+"']").css("color","red");
        $("a[href='"+url+"']").parents(".list-group-item").removeClass("tree-closed").show();
        $("a[href='"+url+"']").parent().parent("ul").show(100);
    }
</script>

<%--

 <%@include file="/WEB-INF/include/sidebar.jsp"%>

--%>