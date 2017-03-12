<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>新闻详情</title>
<style>
.news-con-wrap{
padding:10px 3%;
max-width:750px;
margin:auto;
background:#fff;

}
.news-con-wrap p{
text-indext:2em;
padding:5px 0;
}
.news-con-wrap img{
max-width:94%;
display:block;
margin:auto;
}

</style>
</head>
<body style="background:#eee;padding:0;margin:0;">
<div class="news-con-wrap">${news.content}</div>
</body>
</html>