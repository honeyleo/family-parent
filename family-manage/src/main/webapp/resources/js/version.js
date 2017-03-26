var versions = {
	State : {0 : "初始", 1 : "上架", 2 : "下架"},
    sunNum : 0,
    editor: null,
    pickerLoaded: true,
    sourceArray: [],
    textCon:null,
    init: function () {
        this.bindEvents();
        this.loadTable();
    },
    //加载表格数据
    loadTable: function () {
        var self = this;
        self.query(true);
    },
    load: function (action, argument, refresh) {
        var self = this;
        oTable.dataTable(action, argument, self.insertResult, {refresh: refresh, pageSize: PAGE_SIZE});
    },
    insertResult: function (result,currentPage) {
        var arr = [], $opera;
        if (result.ret != 0) {
            return;
        }
        var value = result.value;
        for (var i = 0; i < value.length; i++) {
            var createTime = new Date(value[i].createTime*1000);
            value[i].createTime = createTime.format("yyyy-MM-dd hh:mm:ss");
            var state = versions.State[value[i].state];
            versions.sunNum = (i+1)+(currentPage*PAGE_SIZE);
            var title = '<a href="#" class="operation J_strategyInfo" data-toggle="modal" data-target="#myModal" data-value=' + value[i].id + '>' + value[i].title + '</a>';
            $opera = '<a href="#" class="operation J_delete" data-toggle="modal" data-target="#myModal" data-value=' + value[i].id + '>删除</a>' + 
                '<a href="#" class="operation dialog-editor" data-toggle="modal" data-target="#editorDialog"  data-value=' + value[i].id + '>编辑</a>' + 
                '</td>';
            arr.push([versions.sunNum, value[i].id, value[i].type, title, '<div class="text_l">'+ value[i].description +'</div>', value[i].versionName, value[i].versionCode, value[i].url, value[i].createTime, state, $opera]);
        }
        self.num++;
        result.draw = self.num;
        result.recordsTotal = result.total;
        result.recordsFiltered = result.total;
        result.data = arr;
    },
    query: function (refresh) {
        var action = "/manager/version/api/list", argument;
            argument = [
                {name:"test", value:"test"}
            ];
        this.load(action, argument, refresh);
    },
    dropDown: function (id, text, inp) {
        $('.' + id).delegate('li a', 'click', function () {
            $("#" + text).text($(this).text());
            var value = $(this).attr('value');
            $("input[name =" + inp + "]").attr("value", value);
            $("#" + text).attr("value", value);
        });
    },
    bindEvents: function () {
        var self = this;
        self.dropDown('modify_search_status1', 'search_dropDown-status1', 'status1');
        self.dropDown('modify_search_status2', 'search_dropDown-status2', 'status2');
        $('.J_search').click(function () {
            self.query(true);
        });
        $('#table').delegate(".J_strategyInfo","click",function(){
            //详情
            $('.modal-body').html($("#infoDialog").tmpl());
            var sure = $('.modal-footer .btn-primary');
            sure.addClass("none");
            $(".J_detail").removeClass("none");
            $('#myModalLabel').text("详情");
            $('.modal-dialog .modal-body').css({'overflow':"auto",'height':''});
            $('.modal-dialog .modal-content').css({'width':'800px'});
            var id = $(this).attr("data-value");
            $.get("/manager/version/detail",{"id":id}, function (result) {
                if (result.ret == 0) {
                    self.insertInfo(result);
                }
            });
        }).delegate(".J_delete","click",function(){
        	//删除
            $('.modal-body').empty().html("确定要删除吗？");
            $('#myModalLabel').text("删除用户");
            var sure = $('.modal-footer .btn-primary');
            sure.addClass("none");
            $(".J_delete_sure").removeClass("none");
            $('.modal-dialog .modal-body').css({'overflow':"auto",'height':''});
            $('.modal-dialog .modal-content').css({'width':'auto'});
            var id = $(this).attr("data-value");
            self.deleteSure(id);
        });
        // editor
        $("#table").on("click", ".dialog-editor", function(){
            $('#myModalLabel').text("编辑版本");
            versions.pickerLoaded = false;
            var sure = $('.modal-footer .btn-primary');
            sure.addClass("none");
            $(".J_sure").removeClass("none");
            $("#updContent").show();
            var id = $(this).attr("data-value");
            self.clearData();
            $.getJSON("/manager/version/detail", {id: id}, function(result){
            	if (result.ret == 0) {
                    $('#search_dropDown-status1').attr("value", result.data.type).text(result.data.type);
                    $("#id").val(result.data.id);
                    $("#title").val(result.data.title);
                    $("#description").val(result.data.description);
                    $("#versionName").val(result.data.versionName);
                    $("#versionCode").val(result.data.versionCode);
                    $("#url").val(result.data.url);
                    $('#search_dropDown-status2').attr("value", result.data.state).text(versions.State[result.data.state]);
            	}
            });
        });
        $("#updContent").click(function(){
        	var self = this;
        	var param = {
        			id: $("#id").val(),
                    type: $("#search_dropDown-status1").attr("value"),
                    title: $("#title").val(),
                    description: $("#description").val(),
                    versionName: $("#versionName").val(),
                    versionCode: $("#versionCode").val(),
                    url: $("#url").val(),
                    state:$("#search_dropDown-status2").attr("value")
                };
            $.post("/manager/version/update", param, function(result){
                if ( result.ret == 0 ) {
                    versions.query();
                    $(".btn-default").trigger("click");
                } else {
                    asyncbox.alert(result.msg,"提示");
                }
            });

        });
        //新建
        $(".add_dialog").click(function () {
        	var sure = $('.modal-footer .btn-primary');
            sure.addClass("none");
            $(".J_add_sure").removeClass("none");
            self.clearData();
        	self.addSure();
        });

        $("#editorDialog .close,#editorDialog .J_close_sure").click(function(){
            //location.reload();
        });
    },
    insertInfo: function (result) {
    	var data = result.data;
    	$(".idText").text(data.id);
    	$(".typeText").text(data.type);
        $(".titleText").text(data.title);
        $(".descriptionText").text(data.description);
        $(".versionNameText").text(data.versionName);
        $(".versionCodeText").text(data.versionCode);
        $(".stateText").text(versions.State[data.state]);
        var createTime = new Date(data.createTime*1000);
        data.createTime = createTime.format("yyyy-MM-dd hh:mm:ss");
        $(".createTimeText").text(data.createTime);
        $(".urlText").text(data.url);
    },
    addSure: function () {
        var self = this;
        $(".J_add_sure").unbind('click').click(function () {
        	self.confirmSubmit();
        });
    },
    confirmSubmit:function(){
    	var self = this;
    	var param = {
                type: $("#search_dropDown-status1").attr("value"),
                title: $("#title").val(),
                description: $("#description").val(),
                versionName: $("#versionName").val(),
                versionCode: $("#versionCode").val(),
                url: $("#url").val(),
                state:$("#search_dropDown-status2").attr("value")
            };
            $.post("/manager/version/add", param, function(result){
                if ( result.ret == 0 ) {
                    self.query();
                    $(".btn-default").trigger("click");
                } else {
                    asyncbox.alert(result.msg,"提示");
                }
            });
    },
    deleteSure:function(id){
        var self =this;
        $(".J_delete_sure").unbind('click');
        $(".J_delete_sure").click(function () {
            $.get("/manager/version/del",{"id":id},function(result){
                if (result.ret == 0) {
                    $('#myModal').modal('hide');
                    self.query();
                } else {
                    asyncbox.alert("删除失败！\n"+result.msg,"提示");
                    $('#myModal').modal('hide');
                }
            });
        });
        $('#myModal').modal('show');
    },
    dropDown: function (id, text, inp) {
        $('.' + id).delegate('li a', 'click', function () {
            $("#" + text).text($(this).text());
            var value = $(this).attr('value');
            $("input[name =" + inp + "]").attr("value", value);
            $("#" + text).attr("value", value);
        });
    },
    clearData:function(){
    	$("#title").val("");
        $("#description").val("");
        $("#versionName").val("");
        $("#versionCode").val("");
        $("#url").val("");
    },
};
$(function () {
    versions.init();
});


