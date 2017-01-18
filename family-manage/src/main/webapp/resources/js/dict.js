var roles = {
    editor: null,
    sourceArray: [],
    textCon:null,
    init: function () {
        this.loadTree();
    },
    //加载数据
    loadTree: function () {
        var self = this;
        var setting = {
        	check: {
        		chkboxType : { "Y" : "", "N" : "ps" }
        	},
            view: {
                addHoverDom: self.addHoverDom,
                removeHoverDom: self.removeHoverDom,
                addDiyDom : self.addDiyDom,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
    				pIdKey: "parentId",
                }
            },
            edit: {
                enable: true
            },
            callback: {
            	beforeEditName: self.edit,
            	beforeRemove: self.beforeRemove
            }
        };
        $.post("/manager/dict/api/tree", function(result) {
    		if(result.ret == 0) {
    			var zNodes = result.data;
    			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
    		}
    		
    	});
        
    },
    addHoverDom : function(treeId, treeNode) {
    	if ($("#diyBtn_"+treeNode.id).length>0) return;
			
    	var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='添加字典' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        if(treeNode.id != 0) {
        	$("#type").attr("disabled",true);
        } else {
        	$("#type").attr("disabled",false);
        }
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) {
        	btn.bind("click", function() {
        		roles.clearData();
        		$('.modal-title').text("新增角色");
        		var sure = $('.modal-footer .btn-primary');
                sure.addClass("none");
                $(".J_add_sure").removeClass("none");
                $('#editorDialog').modal('show');
                roles.addSure(treeNode.id);
                return false;
            });
        }
    },
    removeHoverDom : function(treeId, treeNode) {
    	$("#addBtn_"+treeNode.tId).unbind().remove();
    	$("#diyBtn_"+treeNode.id).unbind().remove();
    },
    addDiyDom : function(treeId, treeNode) {
    	
    },
    edit : function(treeId, treeNode) {
    	roles.clearData();
    	$('.modal-title').text("编辑角色");
        var sure = $('.modal-footer .btn-primary');
        sure.addClass("none");
        $(".J_sure").removeClass("none");
        $('#editorDialog').modal('show');
        $(".J_sure").unbind('click').click(function () {
        	roles.editConfirmSubmit();
        });
        $.getJSON("/manager/dict/detail", {id: treeNode.id}, function(result){
        	if (result.ret == 0) {
                if (result.data.onMenu == 0) {
                	$('#search_dropDown-status1').attr("value",'0').text("否");
                } else {
                	$('#search_dropDown-status1').attr("value", '1').text("是");
                }
                $("#id").val(result.data.id);
                $("#parentId").val(result.data.parentId);
                $("#name").val(result.data.name);
                $("#type").val(result.data.type);
        	}
        });
        return false;
    },
    editConfirmSubmit : function() {
    	var self = this;
    	var param = {
    			id: $("#id").val(),
    			parentId : $("#parentId").val(),
                name: $("#name").val(),
                url: $("#url").val(),
                orderNo: $("#orderNo").val(),
                onMenu:$("#search_dropDown-status1").attr("value")
            };
            $.post("/manager/dict/update", param, function(result){
                if ( result.ret == 0 ) {
                    self.loadTree();
                    $(".btn-default").trigger("click");
                } else {
                    asyncbox.alert(result.msg,"提示");
                }
            });
    },
    beforeRemove : function(treeId, treeNode) {
    	$('.modal-body').empty().html("确定要删除吗？");
    	var sure = $('.modal-footer .btn-primary');
        sure.addClass("none");
        $(".J_delete_sure").removeClass("none");
    	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    	zTree.selectNode(treeNode);
    	$(".J_delete_sure").unbind('click');
        $(".J_delete_sure").click(function () {
            $.get("/manager/dict/del",{"id":treeNode.id},function(result){
                if (result.ret == 0) {
                    $('#myModal').modal('hide');
                    roles.loadTree();
                } else {
                    asyncbox.alert("删除失败！\n"+result.msg,"提示");
                    $('#myModal').modal('hide');
                }
            });
        });
        $('#myModal').modal('show');
        return false;
    },
    addSure: function (parentId) {
        var self = this;
        $(".J_add_sure").unbind('click').click(function () {
        	self.confirmSubmit(parentId);
        });
    },
    confirmSubmit:function(parentId){
    	var self = this;
    	var param = {
    			parentId: parentId,
                name: $("#name").val(),
                type: $("#type").val(),
            };
            $.post("/manager/dict/add", param, function(result){
                if ( result.ret == 0 ) {
                    self.loadTree();
                    $(".btn-default").trigger("click");
                } else {
                    asyncbox.alert(result.msg,"提示");
                }
            });
    },
    clearData:function(){
    	$("#id").val("");
    	$("#parentId").val("");
    	$("#name").val("");
        $("#type").val("");
    },
};
$(function () {
    roles.init();
});


