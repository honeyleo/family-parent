var feedback = {
	State : {0 : "未处理", 1 : "已处理"},
    sunNum : 0,
    editor: null,
    pickerLoaded: true,
    sourceArray: [],
    textCon:null,
    init: function () {
        this.bindEvents();
        this.loadTable();
        util.showFullImage("example", "scn_img");
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
        	var image = '<img src="' + value[i].images + '" width="100" class="scn_img"/>';
            var createTime = new Date(value[i].createTime*1000);
            value[i].createTime = createTime.format("yyyy-MM-dd hh:mm:ss");
            var state = feedback.State[value[i].status];
            feedback.sunNum = (i+1)+(currentPage*PAGE_SIZE);
            var title = '<a href="#" class="operation J_strategyInfo" data-toggle="modal" data-target="#myModal" data-value=' + value[i].id + '>' + value[i].title + '</a>';
            $opera = '<a href="#" class="operation J_delete" data-toggle="modal" data-target="#myModal" data-value=' + value[i].id + '>删除</a>' + 
                '<a href="#" class="operation dialog-editor" data-toggle="modal" data-target="#editorDialog"  data-value=' + value[i].id + '>编辑</a>' + 
                '</td>';
            arr.push([feedback.sunNum, value[i].id, image, '<div class="text_l">'+ value[i].description +'</div>', value[i].createTime, state, $opera]);
        }
        self.num++;
        result.draw = self.num;
        result.recordsTotal = result.total;
        result.recordsFiltered = result.total;
        result.data = arr;
    },
    query: function (refresh) {
    	var status = $("#search_dropDown-status").attr("value");
        var action = "/manager/feedback/api/list", argument;
            argument = [
                {name:"status", value:status}
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
        self.dropDown('modify_search_status', 'search_dropDown-status', 'status');
        self.dropDown('modify_search_status1', 'search_dropDown-status1', 'status1');
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
            $.get("/manager/feedback/detail",{"id":id}, function (result) {
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
            feedback.pickerLoaded = false;
            var sure = $('.modal-footer .btn-primary');
            sure.addClass("none");
            $(".J_sure").removeClass("none");
            var id = $(this).attr("data-value");
            self.clearData();
            $.getJSON("/manager/feedback/detail", {id: id}, function(result){
            	if (result.ret == 0) {
                    $("#id").val(result.data.id);
                    $("#description").val(result.data.description);
                    $('#search_dropDown-status1').attr("value", result.data.status).text(feedback.State[result.data.status]);
            	} else {
            		asyncbox.alert(result.msg,"提示");
            	}
            });
        });
        $("#updContent").click(function(){
        	var self = this;
        	var param = {
        			id: $("#id").val(),
                    description: $("#description").val(),
                    status:$("#search_dropDown-status1").attr("value")
                };
            $.post("/manager/feedback/update", param, function(result){
                if ( result.ret == 0 ) {
                    feedback.query();
                    $(".btn-default").trigger("click");
                } else {
                    asyncbox.alert(result.msg,"提示");
                }
            });

        });

        $("#editorDialog .close,#editorDialog .J_close_sure").click(function(){
            //location.reload();
        });
    },
    insertInfo: function (result) {
    	var data = result.data;
    	$(".idText").text(data.id);
        $(".descriptionText").text(data.description);
        $(".stateText").text(feedback.State[data.status]);
        var createTime = new Date(data.createTime*1000);
        data.createTime = createTime.format("yyyy-MM-dd hh:mm:ss");
        $(".createTimeText").text(data.createTime);
        $(".urlText").text(data.url);
    },
    deleteSure:function(id){
        var self =this;
        $(".J_delete_sure").unbind('click');
        $(".J_delete_sure").click(function () {
            $.get("/manager/feedback/del",{"id":id},function(result){
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
    clearData:function(){
        $("#description").val("");
        $('#search_dropDown-status1').attr("value", '0').text("未处理");
    },
};
$(function () {
    feedback.init();
});


