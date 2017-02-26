package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.common.dao.UserDAO;
import com.family.common.dao.UserDetailDAO;
import com.family.common.model.UserDetail;
import com.family.common.service.UserService;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.PageInfo;
import cn.lfy.base.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private UserDetailDAO userDetailDAO;
    
    @Override
    public int countByCriteria(Criteria criteria) {
        return userDAO.countByExample(criteria);
    }

    @Override
    @Transactional
    public Long add(User record) {
        int result = userDAO.insert(record);
        if(result > 0) {
        	UserDetail detail = UserDetail.newDefaultInstance(record.getId());
        	JSONArray phones = new JSONArray();
        	JSONObject phone = new JSONObject();
        	phone.put("phone", record.getPhone());
        	phone.put("main", true);
        	phones.add(phone);
        	detail.setPhone(phones.toJSONString());
        	userDetailDAO.insert(detail);
        }
        return record.getId();
    }

    @Override
    public List<User> findListByCriteria(Criteria criteria) {
        return userDAO.selectByExample(criteria);
    }

    @Override
    public User findById(Long id) {
        return userDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(User record) {
        return userDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.selectByUsername(username);
    }

    @Override
    public PageInfo<User> findListByCriteria(Criteria criteria, int pageNo, int pageSize) {
        PageInfo<User> res = new PageInfo<User>(pageNo, pageSize);
        int total=this.countByCriteria(criteria);
        res.setTotal(total);
        
        criteria.setOffset(res.getOffset());
        criteria.setRows(pageSize);
        List<User> list = this.findListByCriteria(criteria);
        res.setData(list);
        return res;
    }

	@Override
	public UserDetail getUserDetail(Long id) {
		return userDetailDAO.selectByPrimaryKey(id);
	}

	@Override
	public int updateAvatar(Long id, String avatar) {
		return userDetailDAO.updateAvatar(id, avatar);
	}
    
}
