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
		cursor:pointer;
	}
	</style>
  </head>

  <body>
  <%pageContext.setAttribute("navinfo", "业务管理"); %>
  <%@include file="/WEB-INF/include/navbar.jsp"%>

    <div class="container-fluid">
      <div class="row">
		  <%@include file="/WEB-INF/include/sidebar.jsp"%>
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
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
				
				var setting = {
					view: {
						selectedMulti: false,
						addDiyDom: function(treeId, treeNode){
							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
							if ( treeNode.icon ) {
								icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
							}
						},
						addHoverDom: function(treeId, treeNode){  
							var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
							aObj.attr("href", "javascript:;");
							if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
							var s = '<span id="btnGroup'+treeNode.tId+'">';
							if ( treeNode.level == 0 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="${ctp}/#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 1 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="${ctp}/#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								if (treeNode.children.length == 0) {
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="${ctp}/#" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
								}
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="${ctp}/#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 2 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="${ctp}/#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="${ctp}/#">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
							}
			
							s += '</span>';
							aObj.after(s);
						},
						removeHoverDom: function(treeId, treeNode){
							$("#btnGroup"+treeNode.tId).remove();
						}
					},
					async: {
						enable: true,
						url:"tree.txt",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick : function(event, treeId, json) {

						}
					}
				};
				//$.fn.zTree.init($("#treeDemo"), setting); //异步访问数据
				
				var d = [
                    {"id":1,"pid":0,"seqno":0,"name":"众筹平台项目标签","url":null,"icon":"glyphicon glyphicon-tags","open":true,"checked":false,"children":[
                        {"id":2,"pid":1,"seqno":0,"name":"手机","url":"dashboard.htm","icon":"glyphicon glyphicon-th-large","open":true,"checked":false,"children":[
                            {"id":8,"pid":7,"seqno":1,"name":"手机","url":"user/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]},
                            {"id":9,"pid":7,"seqno":1,"name":"快充","url":"role/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]},
                            {"id":10,"pid":7,"seqno":1,"name":"超级本","url":"permission/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]}
                        ]},
                        {"id":6,"pid":1,"seqno":1,"name":"数码","url":"message/index.htm","icon":"glyphicon glyphicon-th-large","open":true,"checked":false,"children":[
                            {"id":8,"pid":7,"seqno":1,"name":"电脑","url":"user/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]},
                            {"id":9,"pid":7,"seqno":1,"name":"旋转屏幕","url":"role/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]},
                            {"id":10,"pid":7,"seqno":1,"name":"超级本","url":"permission/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]}
                        ]},
                        {"id":7,"pid":1,"seqno":1,"name":"电脑","url":"","icon":"glyphicon glyphicon-th-large","open":true,"checked":false,"children":[
                            {"id":8,"pid":7,"seqno":1,"name":"电脑","url":"user/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]},
                            {"id":9,"pid":7,"seqno":1,"name":"旋转屏幕","url":"role/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]},
                            {"id":10,"pid":7,"seqno":1,"name":"超级本","url":"permission/index.htm","icon":"glyphicon glyphicon-tag","open":true,"checked":false,"children":[]}
                        ]}
                    ]}
                ];
				$.fn.zTree.init($("#treeDemo"), setting, d);
            });

			showPageTree("${ctp}/service/tag/tag.html");
        </script>
  </body>
</html>
