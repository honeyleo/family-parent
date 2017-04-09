package dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.family.common.dao.MemberDAO;
import com.family.common.model.Member;
import com.family.common.model.MemberDTO;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class MemberTest {

	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void tree() {
		try {   
	        List<Member> itemList = memberDAO.list(17L); 
	        Map<Long, MemberDTO> allMap = Maps.newHashMap();
	        List<MemberDTO> result = new ArrayList<MemberDTO>();  
	        for(Member item : itemList){  
	            if(item.getFatherId() == 0 && item.getGender() == 1) {
	            	MemberDTO root = new MemberDTO();
		        	try {
						BeanUtils.copyProperties(root, item);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
		        	allMap.put(root.getId(), root);
	                this.getChildren(root, itemList, allMap);  
	                result.add(root);  
	            }  
	        } 
	        for(Member item : itemList) {
	        	if(item.getGender() == 2 && item.getSpouseId() > 0) {
	        		MemberDTO memberDTO = allMap.get(item.getSpouseId());
	        		MemberDTO spouse = new MemberDTO();
	        		try {
						BeanUtils.copyProperties(spouse, item);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
	        		memberDTO.getSpouses().add(spouse);
	        	}
	        }
	        System.out.println(JSON.toJSONString(result));
	    }catch(Exception e){  
	        
	    }  
		
	}
	
	private MemberDTO getChildren(MemberDTO memberDTO, List<Member> itemList, Map<Long, MemberDTO> allMap) {  
	    Set<MemberDTO> sonList = Sets.newTreeSet();  
	    for(Member item : itemList){  
	        if(memberDTO.getId() == item.getFatherId()){  
	        	MemberDTO son = new MemberDTO();
	        	try {
					BeanUtils.copyProperties(son, item);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
	            sonList.add(son);  
	            allMap.put(son.getId(), son);
	            this.getChildren(son,itemList, allMap);  
	        }  
	    }  
	    memberDTO.setChildrens(sonList); 
	    return memberDTO;
	}   
}
