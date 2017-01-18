package cn.lfy.base.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.common.service.DictService;
import com.google.common.collect.Lists;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.Dict;
import cn.lfy.base.model.LoginUser;
import cn.lfy.base.model.TreeNode;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.RequestUtil;
import cn.lfy.common.utils.Validators;

@Controller
@RequestMapping("/manager/dict")
public class DictController {

    public static final int listPageSize = 20;

    @Autowired
    private DictService dictService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request) throws ApplicationException {
        return "/system/dict/dict-tree";
    }
    
    @RequestMapping("/api/tree")
    @ResponseBody
    public Object api_tree(HttpServletRequest request, LoginUser account) {
		Criteria criteria = new Criteria();
		List<Dict> dicts = dictService.getByCriteria(criteria);
		List<TreeNode> tree = Lists.newArrayList();
		for(Dict dict : dicts){  
			tree.add(new TreeNode(dict.getId(), dict.getName(), dict.getParentId(), false));   
		}
		tree.add(new TreeNode(0, "ROOT", -1, false));
        Message.Builder builder = Message.newBuilder();
        builder.data(tree);
    	return builder.build();
    }

    @RequestMapping("/del")
    @ResponseBody
    public Object del(HttpServletRequest request) throws ApplicationException {
        Long id = RequestUtil.getLong(request, "id");
        dictService.delete(id);
        return Message.newBuilder().build();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Object detail(HttpServletRequest request) throws ApplicationException {
    	Message.Builder builder = Message.newBuilder();
        Long id = RequestUtil.getLong(request, "id");
        Dict dict = dictService.getById(id);
        builder.data(dict);
        return builder.build();
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object add(Dict dict, HttpServletRequest request) throws ApplicationException {
    	if(dict.getParentId() > 0) {
    		Dict parent = dictService.getById(dict.getParentId());
            Validators.notNull(parent, ErrorCode.PARAM_ILLEGAL, "parentId");
            dict.setType(parent.getType());
            dict.setParentIdPath(parent.getParentIdPath() + dict.getParentId() + "$");
    	} else {
    		dict.setParentIdPath("$");
    	}
        dict.setUpdateTime(System.currentTimeMillis()/1000);
        dictService.insert(dict);
        return Message.newBuilder().build();
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(Dict dict, HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        dictService.updateByIdSelective(dict);
        return Message.newBuilder().build();
    }
}
