var users = {
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
            
            var status = value[i].state == 0 ? '下架' : '发布';
            users.sunNum = (i+1)+(currentPage*PAGE_SIZE);
            $opera = '<a href="#" class="operation J_delete" data-toggle="modal" data-target="#myModal" data-value=' + value[i].id + '>删除</a>' + 
                '<a href="#" class="operation dialog-editor" data-toggle="modal" data-target="#editorDialog"  data-value=' + value[i].id + '>编辑</a>' + 
                '</td>';
            arr.push([users.sunNum,value[i].id,status, value[i].createTime, $opera]);
        }
        self.num++;
        result.draw = self.num;
        result.recordsTotal = result.total;
        result.recordsFiltered = result.total;
        result.data = arr;
    },
    query: function (refresh) {
        var action = "/manager/guide/api/list", argument;
            argument = [
                {test:"test"}
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
            $.get("/manager/user/detail",{"id":id}, function (result) {
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
            $('#myModalLabel').text("编辑用户");
            users.pickerLoaded = false;
            var sure = $('.modal-footer .btn-primary');
            sure.addClass("none");
            $(".J_sure").removeClass("none");
            $("#updContent").show();
            var id = $(this).attr("data-value");
            self.clearData();
            $.getJSON("/manager/guide/detail", {id: id}, function(result){
            	if (result.ret == 0) {
            		$('#search_dropDown-status1').attr("value", result.data.type);
                    $("#id").val(result.data.id);
                    
                    var portrait_img = result.data.img;
                    if (portrait_img == "" || portrait_img == undefined) {
                        $(".vertical .modify_icon").hide();
                        $("#uploadPortrait").html("").show();
                    } else {
                        $("#uploadPortrait").prevAll().remove();
                        var temp = $("#iconTemplate").tmpl(portrait_img);
                        $('#uploadPortrait').before(temp);
                        $("#uploadPortrait").hide();
                        $(".icon_img").attr("src", portrait_img);
                        $("#iconM").val(portrait_img);
                    }
                    
                    $("#editorDialog").delegate(".J_del_img", "click", function () {
                        $(this).parents("td").find(".browse_file").show();
                        $(this).parent().remove();
                    });
                   util.uploadFile('uploadPortrait', self.completeIconImg);
            	} else {
            		asyncbox.alert(""+result.msg,"提示");
            	}
            });
        });
        $("#updContent").click(function(){
        	var img = $(".vertical .modify_icon .icon_img").attr("src");
            var param = {
                id: $("#id").val(),
                state: $("#search_dropDown-status1").attr("value"),
                img : img,
            };
            $.post("/manager/guide/update", param, function(result){
                if ( result.ret == 0 ) {
                    self.query();
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
            
            $(".modify_icon").remove();
            $("#uploadPortrait").html("").show();
            util.uploadFile('uploadPortrait', self.completeIconImg);
        	self.addSure();
        });
        
        $("#editorDialog").delegate(".J_del_img", "click", function () {
            $(this).parents("td").find(".browse_file").show();
            $(this).parent().remove();
        });
        $("#editorDialog .close,#editorDialog .J_close_sure").click(function(){
            //location.reload();
        });
    },
    insertInfo: function (result) {
    	var data = result.data;
    	$(".idText").text(data.id);
        $(".usernameText").text(data.username);
        $(".nicknameText").text(data.nickname);
        $(".phoneText").text(data.phone);
        var statusText = "";
        if (data.state == 0) {
            statusText = "禁用";
        } else {
            statusText = "有效";
        }
        $(".stateText").text(statusText);
        var createTime = new Date(data.createTime);
        data.createTime = createTime.format("yyyy-MM-dd hh:mm:ss");
        $(".createTimeText").text(data.createTime);
    },
    addSure: function () {
        var self = this;
        $(".J_add_sure").unbind('click').click(function () {
        	self.confirmSubmit();
        });
    },
    confirmSubmit:function(){
    	var self = this;
    	var img = $(".vertical .modify_icon .icon_img").attr("src");
    	var param = {
                state: $("#search_dropDown-status1").attr("value"),
                img : img
            };
            $.post("/manager/guide/add", param, function(result){
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
            $.get("/manager/guide/del",{"id":id},function(result){
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
        this.dropDown('modify_search_status1', 'search_dropDown-status1', 'status1');
    },
    completeIconImg: function (data) {
        var json = jQuery.parseJSON(data);
        var imgUrl = '/upload/' + json.value[0].url;
        var Img = new Image();
        Img.src = imgUrl;
        Img.onload = function () {
            var _width = Img.width, _height = Img.height;
                if (json.code == 200) {
                    var tData = [
                        {
                            url: '/upload/' + json.value[0].url
                        }
                    ];
                    var temp = $("#iconTemplate").tmpl(tData);
                    $('#uploadPortrait').before(temp);
                    $("#uploadPortrait").hide();
                    $("#icon_name").text("");
                } else {
                    asyncbox.alert("上传失败，请重试","提示");
                }
        }
    },
};
$(function () {
    users.init();
});


