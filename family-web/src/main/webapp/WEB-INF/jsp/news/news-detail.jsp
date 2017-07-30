<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>详情</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <script src="/resources/js/common/flexible.js"></script>
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="full-screen" content="yes">
    <meta name="x5-fullscreen" content="true">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="/resources/css/index.css">
    <link href="/resources/css/asyncbox.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="index-wrap display-box-v">
	<div class="header flex-one">

	    <div class="content">
	        ${news.content}
	    </div>
	    <div class="hot-discuss">热门评论（${news.comments }）</div>
	    <div class="discuss-list">
	         
	    </div>
    </div>
	<div class="bottom-fixed display-box-h">
	    <div class="left flex-one">
	        <div class="input-text" id="commentContent" onclick="showTextarea()"></div>
	    </div>
	    <c:choose>
	       <c:when test="${news.comments > 0 }">
	           <div class="center on" id="comments">${news.comments }</div>
	       </c:when>
	       <c:otherwise>
	            <div class="center" id="comments">${news.comments }</div>
	       </c:otherwise>
	    </c:choose>
	    <div class="right">
	        <c:choose>
		   	<c:when test="${isFavor}">  
		   		<img src="/resources/img/favored.png" class="shoucang"/>
		   	</c:when>
		   	<c:otherwise> 
		   		<img src="/resources/img/favor.png" class="shoucang"/>
		   	</c:otherwise>
			</c:choose>
	    </div>
	</div>
</div>

</body>
<script src="/resources/js/common/common.js"></script>

<script src="/resources/js/common/jquery-asyncbox.js"></script>
<div id="popLayer">
	<div class="content">
		<textarea clos="5" class="txt-are" id="messageInfor" placeholder="请输入留言信息"> </textarea>
		<div class="display-box-h btns">
			<div class="flex-one left"><span onclick="cancelLeaveMessage()">取消</span></div>
			<div class="flex-one right" ><span id="publish">发表</span></div>
		</div>
	</div>
</div>
<script type="text/javascript">
function leaveMessage(){
	var txtAreaVal = $("#messageInfor").val(); 
	if(txtAreaVal==''){
		return alert("请输入留言内容");
	}
}
function cancelLeaveMessage(){
	$("#popLayer").hide();
	$("#messageInfor").val(''); 
	
}
function showTextarea(){
	$("#popLayer").show();
}
$(document).ready(function () {
	$("#messageInfor").val(''); 
	$("#popLayer").hide();
	loadData(0, 10);
	$(".shoucang").click(function(){
		if("${isFavor}" == "true") {
			return;
		}
		$.post("/app/news_home/favor", 
				{
					newsId: "${news.id}", 
					access_token: "${access_token}"
				}, 
				function(result) {
					if(result.ret == 0) {
						$(".shoucang").attr("src", "/resources/img/favored.png");
					}
				}
		);
	});
	
	$("#publish").click(function(){
		var content = $("#messageInfor").val();
		if(!content || content == '') {
			return;
		}
		$.post("/app/news_home/comment", 
				{
					newsId: "${news.id}", 
					content:content,
					access_token: "${access_token}"
				}, 
				function(result) {
					var comment = "";
					if("${currentUser.avatar}" == "") {
						avatar = "/resources/img/default.png";
					} else {
						avatar = "${currentUser.avatar}";
					}
					if(result.ret == 0) {
						$("#messageInfor").val("");
						$("#popLayer").hide();
						
						comment = '<div class="display-box-h item">'
				        			+ '<div class="left">'
		        						+ '<img src="' + avatar + '" class="img" />'
		        					+ '</div>'
		        					+ '<div class="right flex-one display-box-v">'
		        						+ '<div class="display-box-h user">'
		        							+ '<div class="name">' + "${currentUser.nickname}" + '</div>'
		        							+ '<div class="zan-num flex-one">'
		        								+ '<span class="num on">' + result.data.praiseCount + '</span>'
		        							+ '</div>'
		        						+ '</div>'
		        						+ '<div class="content flex-one">' + content + '</div>'
		        						+ '<div class="date">' + result.data.createTime + '</div>'
		        					+ '</div>'
		        				+ '</div>';
    					$(".discuss-list").append(comment);
    					$("#comments").attr("class", "center on");
    					var comments = $("#comments").text();
    					$("#comments").text(1 + parseInt(comments));
					}
				}
		);
	});
});
var access_token = "${access_token}";
function praise(commentId) {
	$.post("/app/news_home/praise", {access_token: access_token, commentId : commentId}, function(result){
		if(result.ret == 0) {
			$("#praiseComment_" + commentId).attr("class", "num on");
			var count = $("#praiseComment_" + commentId).text();
			$("#praiseComment_" + commentId).text(parseInt(count) + 1);
		}
	});
}
function loadData(start, limit) {
	var param = {
	        start: start,
	        limit: limit,
	        newsId: "${news.id}",
	        access_token: access_token
	    };
	    $.post("/app/news_home/comments", param, function(result){
	        if ( result.ret == 0 ) {
	            var values = result.data.list;
	            for(var i = 0; i < values.length; i++) {
	            	if(values[i].avatar == "") {
	            		values[i].avatar = "/resources/img/default.png";
	            	}
	            	var comment = '<div class="display-box-h item">'
	        			+ '<div class="left">'
    						+ '<img src="' + values[i].avatar + '" class="img" />'
    					+ '</div>'
    					+ '<div class="right flex-one display-box-v">'
    						+ '<div class="display-box-h user">'
    							+ '<div class="name">' + values[i].nickname + '</div>'
    							+ '<div class="zan-num flex-one">'
    								+ '<span onclick="praise(' + values[i].id + ')" class="num ' + (values[i].isPraised ? 'on' : '') + '" id="praiseComment_' + values[i].id + '">' + values[i].praiseCount + '</span>'
    							+ '</div>'
    						+ '</div>'
    						+ '<div class="content flex-one">' + values[i].content + '</div>'
    						+ '<div class="date">' + values[i].createTime + '</div>'
    					+ '</div>'
    				+ '</div>';
	        		$(".discuss-list").append(comment);
	            }
	        } else {
	            //asyncbox.alert(result.msg,"提示");
	        }
	    });
}
</script>
</html>