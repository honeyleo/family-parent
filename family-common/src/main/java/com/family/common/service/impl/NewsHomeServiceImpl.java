package com.family.common.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.PageInfo;

import com.family.common.dao.NewsHomeDAO;
import com.family.common.enums.NewsType;
import com.family.common.model.NewsHome;
import com.family.common.model.Surname;
import com.family.common.service.NewsHomeService;

@Service
public class NewsHomeServiceImpl implements NewsHomeService {

	@Autowired
	private NewsHomeDAO newsHomeDAO;
	
	@Override
	public int countByCriteria(Criteria criteria) {
		return newsHomeDAO.countByExample(criteria);
	}

	@Override
	public int insert(NewsHome record) {
		long updateTime = System.currentTimeMillis()/1000;
		record.setCreateTime(updateTime);
		record.setUpdateTime(updateTime);
		record.setUserId(1L);
		return newsHomeDAO.insert(record);
	}

	@Override
	public List<NewsHome> getByCriteria(Criteria criteria) {
		return newsHomeDAO.selectByExample(criteria);
	}

	@Override
	public NewsHome getById(Long id) {
		return newsHomeDAO.selectByPrimaryKey(id);
	}

	@Override
	public int updateByIdSelective(NewsHome record) {
		record.setUpdateTime(System.currentTimeMillis()/1000);
		return newsHomeDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Long id) {
		return newsHomeDAO.deleteByPrimaryKey(id);
	}
	
	@Override
    public PageInfo<NewsHome> findListByCriteria(Criteria criteria, int pageNo, int pageSize) {
        PageInfo<NewsHome> res = new PageInfo<NewsHome>(pageNo, pageSize);
        int total=this.countByCriteria(criteria);
        res.setTotal(total);
        
        criteria.setOffset(res.getOffset());
        criteria.setRows(pageSize);
        List<NewsHome> list = this.getByCriteria(criteria);
        res.setData(list);
        return res;
    }

	@Override
	public List<NewsHome> list(String surname, NewsType newsType, int type, int start, int limit) {
		Long surnameId = null;
		if(StringUtils.isNotBlank(surname)) {
			Surname surnameObj = newsHomeDAO.getSurname(surname);
			if(surnameObj != null) {
				surnameId = surnameObj.getId();
			}
		}
		return newsHomeDAO.list(surnameId, newsType.getValue(), type, start, limit);
	}

	@Override
	public int getNewestCount(int type, long lastUpdateTime) {
		return newsHomeDAO.getNewestCount(type, lastUpdateTime);
	}

	@Override
	public int incrComments(long id) {
		return newsHomeDAO.incrComments(id);
	}

	@Override
	public List<Surname> getSurnameBySurname(String surname) {
		return newsHomeDAO.getSurnameBySurname(surname);
	}

	@Override
	public Surname getSurname(String surname) {
		return newsHomeDAO.getSurname(surname);
	}

}
