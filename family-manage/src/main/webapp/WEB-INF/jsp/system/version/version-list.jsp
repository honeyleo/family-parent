<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>版本管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="<%=basePath%>resources/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/css/common.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/css/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/css/asyncbox.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/css/box.css" rel="stylesheet" type="text/css" />
    <link type="text/css" rel="stylesheet" href="<%=basePath %>resources/plugins/zTree/metroStyle.css"/>
</head>
<body>
<div class="panel_con main_con">
    <div class="current_position">
        <ol>
            <li class="glyphicon glyphicon-folder-open icon_home"></li>
            <li>系统管理&nbsp;&nbsp;&gt; </li>
            <li class="active">版本管理</li>
        </ol>
    </div>
    <div class="panel panel-default main_contents">
        <div class="panel-body search_list">
            <form class="navbar-form navbar-left" role="search">
            	<div class="col-width">
                    <span class="label_style">状态：</span>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-default-disable dropDown-style"><i
                                id="search_dropDown-status" value="">全部</i></button>
                        <button type="button"
                                class="btn btn-default dropdown-toggle  btn-default-disable search_status_list"
                                data-toggle="dropdown">
                            <span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu modify_search_status scrollBar" role="menu">
                            <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="">全部</a></li>
                            <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="1">上架</a></li>
                            <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="2">下架</a></li>
                        </ul>
                    </div>
                    <input type="hidden" value="50" name="status" />
                </div>
                <div class="col-width">
                    <button class="btn btn-primary add_dialog applic_btn" data-toggle="modal" data-target="#editorDialog">新建</button>
                    <button type="button" class="btn btn-primary J_search applic_btn">查询</button>
                </div>
            </form>

        </div>
        <table id="example" class="display custom-table fixed_table" cellspacing="0" >
            <thead>
            <tr>
                <th width="40px">序号</th>
                <th width="50px">ID</th>
                <th>终端类型</th>
                <th>标题</th>
                <th width="140px">描述</th>
                <th>版本名</th>
                <th>版本号</th>
                <th>摘要</th>
                <th>文件大小</th>
                <th>下载链接</th>
                <th width="180px">创建时间</th>
                <th>状态</th>
                <th width="220px">操作</th>
            </tr>
            </thead>
            <tbody id="table" style="margin-left:15px;border:1px solid #000;"></tbody>
        </table>
    </div>
</div>
<div class="loading_wrap"> </div>
<!-- 弹出框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary J_sure">确定</button>
                <button type="button" class="btn btn-primary J_delete_sure none">确定</button>
                <button type="button" class="btn btn-primary J_add_sure none">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editorDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog application_dialog">
        <div class="modal-content" id="editorModal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑版本</h4>
            </div>
            <div class="modal-body1" style="padding: 20px;">
                <table cellspacing="0" width="100%" class="modifyTable">
                	<tr>
                        <th>终端类型：</th>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default btn-default-disable dropDown-style"><i
                                        id="search_dropDown-status1" value="ANDROID">ANDROID</i></button>
                                <button type="button"
                                        class="btn btn-default dropdown-toggle  btn-default-disable search_status_list"
                                        data-toggle="dropdown">
                                    <span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu modify_search_status1 scrollBar" role="menu">
                                    <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="ANDROID">ANDROID</a></li>
                                    <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="IOS">IOS</a></li>
                                </ul>
                            </div>
                            <input type="hidden" value="50" name="status1" />
                        </td>
                    </tr>
                    <tr>
	                    <th>标题：</th>
	                    <td>
	                        <input type="text" class="form-control input_common300" id="title" placeholder="标题">
	                        <input type="hidden" class="form-control input_common" id="id">
	                    </td>
	                </tr>
                    <tr>
	                    <th>描述：</th>
	                    <td>
	                    	<textarea rows="2" id="description" style="width:100%"></textarea>
	                    </td>
	                </tr>
	                <tr>
	                    <th>版本名：</th>
	                    <td>
	                        <input type="text" class="form-control input_common300" id="versionName" placeholder="版本名">
	                    </td>
	                </tr>
	                <tr>
	                    <th>版本号：</th>
	                    <td>
	                        <input type="text" class="form-control input_common300" id="versionCode" placeholder="版本号">
	                    </td>
	                </tr>
	                <tr>
	                    <th>摘要：</th>
	                    <td>
	                        <input type="text" class="form-control input_common600" id="digest" placeholder="文件摘要">
	                    </td>
	                </tr>
	                <tr>
	                    <th>文件大小：</th>
	                    <td>
	                        <input type="number" class="form-control input_common300" id="size">
	                    </td>
	                </tr>
	                <tr>
	                    <th>下载链接：</th>
	                    <td>
	                        <input type="text" class="form-control input_common600" id="url" placeholder="下载链接">
	                    </td>
	                </tr>
                    <tr>
                        <th>状态：</th>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default btn-default-disable dropDown-style"><i
                                        id="search_dropDown-status2" value="0">初始</i></button>
                                <button type="button"
                                        class="btn btn-default dropdown-toggle  btn-default-disable search_status_list"
                                        data-toggle="dropdown">
                                    <span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu modify_search_status2 scrollBar" role="menu">
                                	<li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="0">初始</a></li>
                                    <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="1">上架</a></li>
                                    <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="2">下架</a></li>
                                </ul>
                            </div>
                            <input type="hidden" value="50" name="status2" />
                        </td>
                    </tr>
                </table>
            </div>
            <div id="strategyTextView" style="display:none;">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary J_sure" id="updContent">确定</button>
                <button type="button" class="btn btn-primary J_add_sure none">确定</button>
                <button type="button" class="btn btn-default J_close_sure" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script id="infoDialog" type="text/x-jquery-tmpl">
  <div class="modify">
  <table cellspacing="0" width="100%" class="modifyTable">
    <tr>
    <th>ID：</th>
     <td><span class="idText"></span></td>
     <th>终端类型：</th>
     <td><span class="typeText"></span></td>
    </tr>
   <tr>
   		<th>标题：</th>
        <td colspan="3"><span class="titleText"></span></td>
  </tr>
  <tr>
	<th>描述：</th>
    <td colspan="3"><span class="descriptionText"></span></td>
  </tr>
   <tr>
        <th>版本名：</th>
        <td><span class="versionNameText"></span></td>
        <th>版本号：</th>
        <td><span class="versionCodeText"></span></td>
    </tr>
	<tr>
        <th>文件摘要：</th>
        <td><span class="digestText"></span></td>
        <th>文件大小：</th>
        <td><span class="sizeText"></span></td>
    </tr>
	<tr>
        <th>状态：</th>
        <td><span class="stateText"></span></td>
        <th>创建时间：</th>
        <td><span class="createTimeText"></span></td>
    </tr>
	<tr>
        <th>下载链接：</th>
        <td colspan="3"><span class="urlText"></span></td>
    </tr>
  </table>
  </div>
</script>

<script src="<%=basePath%>resources/js/common/common.js"></script>
<script src="<%=basePath%>resources/js/common/bootstrap.js"></script>
<script src="<%=basePath%>resources/plugins/zTree/jquery.ztree.all.js"></script>
<script src="<%=basePath%>resources/js/common/bootstrap-typeahead.js"></script>
<script src="<%=basePath%>resources/js/common/jquery.dataTables.js"></script>
<script src="<%=basePath%>resources/js/common/dataTables.bootstrap.js"></script>
<script src="<%=basePath%>resources/js/common/jquery-asyncbox.js"></script>
<script src="<%=basePath%>resources/js/common/jquery-ui.min.js"></script>
<script src="<%=basePath%>resources/js/common/public.js"></script>
<script src="<%=basePath %>/resources/js/common/sha256.min.js"></script>
<script src="<%=basePath%>resources/js/version.js"></script>
</body>
</html>
