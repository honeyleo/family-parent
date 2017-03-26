package cn.lfy.common.page;

/**
 * 分页SQL拼接器
 * @author liaopng@163.com
 * @date 2017-02-24
 */
public class MySql5Dialect extends Dialect {  
	  
    public String getLimitString(String querySqlString, int offset, int limit) {  
        return querySqlString + " limit " + offset + " ," + limit;  
    }  
  
    @Override  
    public String getCountString(String querySqlString) {  
  
        int limitIndex = querySqlString.lastIndexOf("limit");  
  
        if(limitIndex != -1){  
            querySqlString = querySqlString.substring(0, limitIndex != -1 ? limitIndex : querySqlString.length() - 1);  
        }  
        int index = querySqlString.lastIndexOf("from ");
        if(index == -1) {
        	index = querySqlString.lastIndexOf("FROM ");
        }
        StringBuilder sql = new StringBuilder("SELECT COUNT(0) ");
        sql.append(querySqlString.substring(index));
        return sql.toString();  
    }  
  
    public boolean supportsLimit() {  
        return true;  
    }  
}  
