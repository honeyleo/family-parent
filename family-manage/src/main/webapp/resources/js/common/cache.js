var Enums = {
	NewsType : [
	    {"value" : 1, "text" : "首页"},
	    {"value" : 2, "text" : "咨询页"}
	],
	NewsSubType : [
	    {"value" : 1, "text" : "民俗文化"},
	    {"value" : 2, "text" : "百家姓文化"},
	    {"value" : 3, "text" : "新闻"},
	    {"value" : 11, "text" : "家族新闻"},
	    {"value" : 12, "text" : "家族活动"}
    ]
};

var Map = {
	GuideState : {0 : "已下架", 1 : "待发布", 2 : "已发布"},
	NewsSubType : {1 : "民俗文化", 2 : "百家姓文化", 3 : "新闻"},
	NewsConsultType : {1 : "家族新闻", 2 : "家族活动"},
	//0-无图；1-上文下一大图；2-上文下三小图；3-左文右一小图
	ImgShowMode : {
		0 : {text :"无图", count : 0}, 
		1 : {text : "上文下一大图", count : 1}, 
		2 : {text : "上文下三小图", count : 3}, 
		3 : {text :"左文右一小图", count : 1}
	}
}