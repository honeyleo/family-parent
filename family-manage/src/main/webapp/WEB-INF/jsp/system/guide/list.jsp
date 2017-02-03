<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>首页管理</title>
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
    <link href="<%=basePath%>resources/plugins/uploadify/uploadify.css" rel="stylesheet" />
    <link href="<%=basePath%>resources/plugins/uploadify/html5uploader.css" rel="stylesheet" />
    <link type="text/css" rel="stylesheet" href="<%=basePath %>resources/plugins/zTree/metroStyle.css"/>
</head>
<body>
<div class="panel_con main_con">
    <div class="current_position">
        <ol>
            <li class="glyphicon glyphicon-folder-open icon_home"></li>
            <li>业务管理&nbsp;&nbsp;&gt; </li>
            <li class="active">引导页管理</li>
        </ol>
    </div>
    <div class="panel panel-default main_contents">
        <div class="panel-body search_list">
            <form class="navbar-form navbar-left" role="search">
            	<div class="col-width">
                    <span class="label_style">新闻类型：</span>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-default-disable dropDown-style"><i
                                id="search_dropDown-status" value="">全部</i></button>
                        <button type="button"
                                class="btn btn-default dropdown-toggle  btn-default-disable search_status_list"
                                data-toggle="dropdown">
                            <span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu modify_search_status scrollBar" role="menu">
                            <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="0">全部</a></li>
                            <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="1">民俗文化</a></li>
                            <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="2">百家姓文化</a></li>
                             <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="3">新闻</a></li>
                        </ul>
                    </div>
                    <input type="hidden" value="50" name="type" />
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
                <th>ID</th>
                <th>标题</th>
                <th>类型</th>
                <th width="180px">创建时间</th>
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
                <h4 class="modal-title">编辑用户</h4>
            </div>
            <div class="modal-body1" style="padding: 20px;">
                <table cellspacing="0" width="100%" class="modifyTable">
                	<tr>
                        <th>状态：</th>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default btn-default-disable dropDown-style"><i
                                        id="search_dropDown-status1" value="1">发布</i></button>
                                <button type="button"
                                        class="btn btn-default dropdown-toggle  btn-default-disable search_status_list"
                                        data-toggle="dropdown">
                                    <span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu modify_search_status1 scrollBar" role="menu">
                                    <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="1">发布</a></li>
                                    <li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0);' value="0">下架</a></li>
                                </ul>
                            </div>
                            <input type="hidden" value="50" name="type1" />
                        </td>
                    </tr>
                    <tr>
                        <th>图片：</th>
                        <td class="vertical">
                            <div id="uploadPortrait" class="icon browse_file"></div>
                            <span class="ver_tips none" id="icon_name"></span>
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
<script id="iconTemplate" type="text/x-jquery-tmpl">
    <div class="modify_icon">
        <img src="{{= url}}" alt="logo" class="icon_img img_style" />
        <input type="hidden" value="{{= url}}" id="iconM" name="iconM">
        <a class="del_img J_del_img del_img_style" href="javascript:;">x</a>
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
<script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/plugins/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="<%=basePath%>resources/plugins/uploadify/jquery-uploadify.min.js"></script>
<script src="<%=basePath%>resources/plugins/uploadify/jquery.html5uploader.js"></script>
<script src="<%=basePath%>resources/js/guide.js"></script>
</body>
</html>
