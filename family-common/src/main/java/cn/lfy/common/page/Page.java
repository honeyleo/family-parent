package cn.lfy.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.family.common.model.Phone;

/**
 * 分页对象
 * @author liaopng@163.com
 * @date 2017-02-24
 */
public class Page<T> implements Serializable {
	
	private static final long serialVersionUID = -8133942644763377252L;

	public static final int PAGE_SIZE = 20;
	
	/**
	 * 每页显示记录数
	 */
	private int pageSize;
	/**
	 * 总记录数
	 */
	private int totalResult;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 总页数
	 */
	private int totalPage;
	
	private List<T> list = new ArrayList<T>();
	
	public Page() {
		
	}
	public Page(List<T> list, int currentPage, int pageSize, int totalResult) {
		this.currentPage = currentPage;
		this.totalResult = totalResult;
		this.pageSize = pageSize;
		this.list = list;
	}
	
	public int getTotalPage() {
		if(totalResult%pageSize == 0) {
			totalPage = totalResult/pageSize;
		} else {
			totalPage = totalResult/pageSize + 1;
		}
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		if(currentPage <= 0)
			currentPage = 1;
		if(currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", totalResult=" + totalResult
				+ ", currentPage=" + getCurrentPage() + ", totalPage=" + getTotalPage()
				+ "]";
	}
	
	public static void main(String[] args) {
		Page<Phone> page = new Page<Phone>();
		page.setCurrentPage(1);
		page.setPageSize(10);
		page.setTotalPage(10);
		page.setTotalResult(100);
		
		System.out.println(JSON.toJSONString(page));
	}
}
