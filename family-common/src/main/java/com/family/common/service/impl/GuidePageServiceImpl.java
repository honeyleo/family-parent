package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.PageInfo;

import com.family.common.dao.GuidePageDAO;
import com.family.common.model.GuidePage;
import com.family.common.service.GuidePageService;

@Service
public class GuidePageServiceImpl implements GuidePageService {

	@Autowired
	private GuidePageDAO guidePageDAO;
	
	@Override
	public int countByCriteria(Criteria criteria) {
		return guidePageDAO.countByExample(criteria);
	}

	@Override
	public int insert(GuidePage record) {
		long updateTime = System.currentTimeMillis()/1000;
		record.setCreateTime(updateTime);
		record.setUpdateTime(updateTime);
		record.setUrl("");
		return guidePageDAO.insert(record);
	}

	@Override
	public List<GuidePage> getByCriteria(Criteria criteria) {
		return guidePageDAO.selectByExample(criteria);
	}

	@Override
	public GuidePage getById(Long id) {
		return guidePageDAO.selectByPrimaryKey(id);
	}

	@Override
	public int updateByIdSelective(GuidePage record) {
		record.setUrl("");
		record.setUpdateTime(System.currentTimeMillis()/1000);
		return guidePageDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Long id) {
		return guidePageDAO.deleteByPrimaryKey(id);
	}
	
	@Override
    public PageInfo<GuidePage> findListByCriteria(Criteria criteria, int pageNo, int pageSize) {
        PageInfo<GuidePage> res = new PageInfo<GuidePage>(pageNo, pageSize);
        int total=this.countByCriteria(criteria);
        res.setTotal(total);
        
        criteria.setOffset(res.getOffset());
        criteria.setRows(pageSize);
        List<GuidePage> list = this.getByCriteria(criteria);
        res.setData(list);
        return res;
    }

	@Override
	public List<GuidePage> getAvariableGuidePage() {
		Criteria criteria = new Criteria();
		criteria.put("state", 2);
		return this.getByCriteria(criteria);
	}

}
