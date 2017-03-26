package cn.lfy.common.page;

/**
 * 分页SQL拼接抽象
 * @author liaopng@163.com
 * @date 2017-02-24
 */
public abstract class Dialect {

	public static enum Type {  
        MYSQL,
        ;
    }  
  
    public abstract String getLimitString(String querySqlString, int offset, int limit);  
  
    public abstract String getCountString(String querySqlString);
}
