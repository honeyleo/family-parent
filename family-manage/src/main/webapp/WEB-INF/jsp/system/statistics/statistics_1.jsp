<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ include file="../common/common_css.jsp"%>
</head>
<body class="no-skin">

	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">业务管理</a>
				</li>
				<li class="active">注册统计</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>

		<!-- /section:basics/content.breadcrumbs -->
		<div class="page-content">

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="space-6"></div>

						<div class="col-sm-7 infobox-container">
							<div class="infobox infobox-green  ">
								<div class="infobox-icon">
									<i class="icon-comments"></i>
								</div>

								<div class="infobox-data">
									<span class="infobox-data-number">${today}</span>
									<div class="infobox-content">新注册用户数</div>
								</div>
							</div>

							<div class="infobox infobox-blue  ">
								<div class="infobox-icon">
									<i class="icon-twitter"></i>
								</div>

								<div class="infobox-data">
									<span class="infobox-data-number">${total }</span>
									<div class="infobox-content">总注册用户数</div>
								</div>

							</div>
						</div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content -->
	<%@ include file="../common/common_js.jsp"%>
</body>
</html>
