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
<div class="index-wrap">
    <div class="content">
        ${news.content}
    </div>
    <!--旅游列表 start-->
    <!--  
    <div class="tourist-list">
        <div class="tourist-item">
            <div class="tourist-title">
                朱家角古镇城隍庙文化景点对外开放
            </div>
            <div class="imgs">
                <div class="img-item">
                    <img src="/resources/img/news.png" class="img"/>
                </div>
                <div class="img-item">
                    <img src="/resources/img/news.png" class="img"/>
                </div>
                <div class="img-item">
                    <img src="/resources/img/news.png" class="img"/>
                </div>
            </div>
            <div class="intro display-box-h">
                <div class="left">看图说事</div>
                <div class="center">10分钟前</div>
                <div class="right flex-one">9839评
                    <span class="point"></span>
                    <span class="point"></span>
                    <span class="point"></span>
                </div>
            </div>
        </div>
        <div class="tourist-item">
            <div class="tourist-title">
                朱家角古镇城隍庙文化景点对外开放
            </div>
            <div class="imgs">
                <div class="img-item">
                    <img src="/resources/img/news.png" class="img"/>
                </div>
                <div class="img-item">
                    <img src="/resources/img/news.png" class="img"/>
                </div>
                <div class="img-item">
                    <img src="/resources/img/news.png" class="img"/>
                </div>
            </div>
            <div class="intro display-box-h">
                <div class="left">看图说事</div>
                <div class="center">10分钟前</div>
                <div class="right flex-one">9839评
                    <span class="point"></span>
                    <span class="point"></span>
                    <span class="point"></span>
                </div>
            </div>
        </div>
    </div>
    -->
    <!--旅游列表 end-->
    <!-- 
    <div class="more-btn">
        还没看够 ，<span class="enter">进入看看</span>
    </div>
     -->
    <div class="hot-discuss">热门评论（${news.comments }）</div>
    <div class="discuss-list">
        
    </div>

</div>
<div class="bottom-fixed display-box-h">
    <div class="left flex-one">
        <input type="text" class="input-text" id="commentContent"/>
    </div>
    <div class="center">
        ${news.comments }
    </div>
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
</body>
<script src="/resources/js/common/common.js"></script>
<script src="/resources/js/common/jquery-asyncbox.js"></script>
<script type="text/javascript">
$(document).ready(function () {
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
	$(".center").click(function(){
		var content = $("#commentContent").val();
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
					if(result.ret == 0) {
						$("#commentContent").val("");
					}
				}
		);
	});
});

function loadData(start, limit) {
	var param = {
	        start: start,
	        limit: limit,
	        newsId: "${news.id}",
	        access_token: "${access_token}"
	    };
	    $.post("/app/news_home/comments", param, function(result){
	        if ( result.ret == 0 ) {
	            var values = result.data.list;
	            for(var i = 0; i < values.length; i++) {
	            	var comment = '<div class="discuss-item display-box-h">'
	            					+ '<div class="left">'
	                					+ 	'<img src="' + values[i].avatar + '" class="touxiang"/>'
	            					+ '</div>'
	            					+ '<div class="right flex-one display-box-v">'
	                					+ '<div class="top display-box-h">'
	                    					+ '<div class="name flex-one">' + values[i].nickname + '</div>'
	                    					+ '<div class="date">' + values[i].createTime + '</div>'
	                					+ '</div>'
	                					+ '<div class="bottom">'
	                   					+ values[i].content
	                					+ '</div>'
	            					+ '</div>'
	        					+ '</div>';
	        		$(".discuss-list").append(comment);
	            }
	        } else {
	            asyncbox.alert(result.msg,"提示");
	        }
	    });
}
</script>
</html>