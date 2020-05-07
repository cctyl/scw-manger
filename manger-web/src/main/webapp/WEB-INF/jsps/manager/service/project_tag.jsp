<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
<%pageContext.setAttribute("navinfo", "业务管理"); %>
<%@include file="/WEB-INF/include/navbar.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 项目标签列表</div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>



<script src="${ctp}/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctp}/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctp}/script/docs.min.js"></script>
<script src="${ctp}/ztree/jquery.ztree.all-3.5.min.js"></script>
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

        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                },

                key: {
                    url: "haha"
                }

            },
            edit: {
                enable: true
            },

            view: {
                selectedMulti: false,
                addDiyDom: function showIcon(treeId, treeNode) {

                    $("#" + treeNode.tId + "_ico").removeClass()
                        .addClass("glyphicon glyphicon-tag");
                },
                addHoverDom: function (treeId, treeNode) {

                    if (treeNode.editNameFlag || $("#btnGroup" + treeNode.tId).length > 0) return;
                    var s = '<span id="btnGroup' + treeNode.tId + '">';
                    if (treeNode.level == 0) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="${ctp}/#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if (treeNode.level == 1) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="${ctp}/#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        if (treeNode.children.length == 0) {
                            s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="${ctp}/#" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                        }
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="${ctp}/#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if (treeNode.level == 2) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="${ctp}/#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="${ctp}/#">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                    }

                    s += '</span>';
                    aObj.after(s);
                },
                removeHoverDom: function (treeId, treeNode) {
                    $("#btnGroup" + treeNode.tId).remove();
                }
            },

            async: {
                enable: true,
                url: "${ctp}/service/tag/getTag",
                autoParam: ["id", "name=n", "level=lv"]
            },
            callback: {
                beforeRemove: del,
                beforeRename: beforeRename
            }
        };
        $.fn.zTree.init($("#treeDemo"), setting); //异步访问数据


    });

    //给当前节点更改名字
    //编辑并保存节点
    function beforeRename(treeId, treeNode, newName, isCancel) {
        if (newName.length == 0) { //节点名称判断
            alert("不能为空。");
            return false;
        }
        else {
            $.ajax({   //数据入库
                type: "Post",
                url: "${ctp}/service/tag/update",
                data: {  "id": treeNode.id, "name": newName  },
                succes: function (data) {
                    if (data.code == "100") {
                        alert("保存失败");
                        return false;
                    }


                }
            });
        }
    }





    //删除当前节点
    function del( treeId, treeNode){



            if (treeNode.isParent==true) {
                alert("包含下级，无法删除。");
                return false;
            } else {
                if (confirm("该操作会将关联数据同步删除，是否确认删除？") == true) {
                    $.ajax({
                        type: "Post",
                        url: "${ctp}/service/tag/del",
                        data: { "id": treeNode.id },
                        success: function (data) {
                            if (data.code == "100") {
                                alert("删除失败。");
                                return false;
                            }

                        }
                    });
                }else {
                    return false;
                }
            }


    }


    showPageTree("${ctp}/service/tag/tag.html");
</script>
</body>
</html>
