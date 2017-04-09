package com.family.common.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.common.dao.MemberDAO;
import com.family.common.dao.UserDetailDAO;
import com.family.common.model.Member;
import com.family.common.model.MemberDTO;
import com.family.common.model.UserDetailDTO;
import com.family.common.service.MemberService;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.Validators;

/**
 * /**
	 *  11-父亲；12-母亲；13-伯/叔父；14-婶婶；16-姑姑；
		21-祖父；22-祖母；23-伯/叔祖父；24-伯/叔祖母；
		31-曾祖父；32-曾祖母；
		41-高祖父；42-高祖母；
		51-天祖父；52-天祖母；
		61-烈祖父；62-烈祖母；
		71-太祖父；72-太祖母；
		81-远祖父；81-远祖母；
		91-鼻祖父；92-鼻祖母；
		1-自己；2-妻子；3-兄弟；4-姐妹；5-堂兄弟；6-堂姐妹；
		-11-儿子；-12-女儿；-13-侄子；-14-侄女；
		-21-孙子；-22-孙女；
		-31-曾孙；-32-曾孙女；
		-41-玄孙；-42-玄孙女；
		-51-来孙；-52-来孙女；
		-61-晜孙；-62-晜孙女；
		-71-仍孙；-72-仍孙女；
		-81-云孙；-82-云孙女；
		-91-耳孙；-92-耳孙女；
 * @author honeyleo
 *
 */
@Service
public class MemberServiceImpl implements MemberService {

	private static final Logger LOG = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private UserDetailDAO userDetailDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int delete(Long id) {
		return memberDAO.delete(id);
	}
	
	@Override
	public int insert(Member record) {
		int ret = memberDAO.insert(record);
		return ret;
	}
	
	@Override
	public Member get(Long id) {
		Member member = memberDAO.get(id);
		if(member != null && StringUtils.isNotBlank(member.getAvatar())) {
			member.setAvatar(imageUrl + member.getAvatar());
		}
		return member;
	}
	
	@Override
	public int update(Member record) {
		return memberDAO.update(record);
	}
	
	public List<MemberDTO> familyTree(Long userId) {
		Member self = memberDAO.getSelf(userId);
		if(self == null) {
			//创建以自己为中心的家谱
			Member member = Member.newDefaultInstance(0L);
			UserDetailDTO userDetail = userDetailDAO.getUserDetailDTO(userId);
			try {
				BeanUtils.copyProperties(member, userDetail);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			member.setUserId(userId);
			member.setCreatorUserId(userId);
			member.setName(userDetail.getNickname());
			member.setSpouseId(0L);
			member.setFatherId(0L);
			member.setMotherId(0L);
			member.setAlive(1);
			member.setDieTime(0L);
			memberDAO.insert(member);
		}
		List<MemberDTO> result = new ArrayList<MemberDTO>();  
		try {   
	        List<Member> itemList = memberDAO.list(userId); 
	        Map<Long, MemberDTO> allMap = Maps.newHashMap();
	        for(Member item : itemList) {  
	            if(item.getFatherId() == 0 && item.getGender() == 1) {
	            	MemberDTO root = new MemberDTO();
		        	try {
						BeanUtils.copyProperties(root, item);
						if(StringUtils.isNotBlank(root.getAvatar())) {
							root.setAvatar(imageUrl + root.getAvatar());
						}
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
						if(StringUtils.isNotBlank(spouse.getAvatar())) {
							spouse.setAvatar(imageUrl + spouse.getAvatar());
						}
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
	        		memberDTO.getSpouses().add(spouse);
	        	}
	        }
	    } catch(Exception e) {  
	        
	    }  
		return result;
	}
	
	private MemberDTO getChildren(MemberDTO memberDTO, List<Member> itemList, Map<Long, MemberDTO> allMap) {  
	    Set<MemberDTO> sonList = Sets.newTreeSet();  
	    for(Member item : itemList) {  
	        if(memberDTO.getId() == item.getFatherId()) {  
	        	MemberDTO son = new MemberDTO();
	        	try {
					BeanUtils.copyProperties(son, item);
					if(StringUtils.isNotBlank(son.getAvatar())) {
						son.setAvatar(imageUrl + son.getAvatar());
					}
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
	/**
	 *  给指定用户添加
	 * @param userId 创建者
	 * @param memberId 给memberId添加关系成员member
	 * @param relation 11-父亲；12-母亲；22-配偶；31-兄弟；32-姐妹；41-儿子；42-女儿；
	 * @param member 添加的成员
	 * @return
	 */
	@Transactional
	public void addMember(Long userId, Long memberId, int relation, Member member) {
		LOG.info("userId={} give memberId={} add relation={}  of {}", new Object[]{userId, memberId, relation, member});
		member.setGender(relation%2 == 0 ? 2 : 1);
		switch (relation) {
		case 11://父亲
			Member father = memberDAO.getFather(memberId);
			Validators.isFalse(father != null, ErrorCode.VALUE_EXIST, "母亲");
			int ret = memberDAO.insert(member);
			Validators.isFalse(ret <= 0, ErrorCode.SERVER_ERROR);
			Member tmp = new Member();
			tmp.setId(memberId);
			tmp.setFatherId(member.getId());
			memberDAO.updateParent(tmp);
			Member motherTmp = memberDAO.getMother(memberId);
			if(motherTmp != null) {
				motherTmp.setSpouseId(member.getId());
				memberDAO.update(motherTmp);
			}
			break;
		case 12://母亲
			Member mother = memberDAO.getMother(memberId);
			Validators.isFalse(mother != null || mother != null, ErrorCode.VALUE_EXIST, "母亲");
			Member fatherTmp = memberDAO.getFather(memberId);
			if(fatherTmp != null) {
				member.setSpouseId(fatherTmp.getId());
			}
			int ret12 = memberDAO.insert(member);
			Validators.isFalse(ret12 <= 0, ErrorCode.SERVER_ERROR);
			Member tmp12 = new Member();
			tmp12.setId(memberId);
			tmp12.setMotherId(member.getId());
			memberDAO.updateParent(tmp12);
			break;
		case 22://配偶
			Member current = memberDAO.get(memberId);
			if(current.getGender() == 1) {//给男丁添加配偶
				member.setSpouseId(memberId);
				memberDAO.insert(member);
			} else {//给女丁添加配偶
				memberDAO.insert(member);
				Member tmp22 = new Member();
				tmp22.setId(memberId);
				tmp22.setSpouseId(member.getId());
				memberDAO.update(tmp22);
			}
			break;
		case 31:
		case 32://兄弟姐妹
			Member theirFather = memberDAO.getFather(memberId);
			Member theirMother = memberDAO.getMother(memberId);
			if(theirFather == null) {
				theirFather = Member.newDefaultInstance(0L);
				theirFather.setName("父亲");
				theirFather.setGender(1);
				theirFather.setCreatorUserId(userId);
				memberDAO.insert(theirFather);
				Member tmpMember = new Member();
				tmpMember.setId(memberId);
				tmpMember.setFatherId(theirFather.getId());
				memberDAO.updateParent(tmpMember);
			}
			if(theirMother == null) {
				theirMother = Member.newDefaultInstance(0L);
				theirMother.setName("母亲");
				theirMother.setGender(2);
				theirMother.setSpouseId(theirFather.getId());
				theirMother.setCreatorUserId(userId);
				memberDAO.insert(theirMother);
				Member tmpMember = new Member();
				tmpMember.setId(memberId);
				tmpMember.setMotherId(theirMother.getId());
				memberDAO.updateParent(tmpMember);
			}
			member.setFatherId(theirFather.getId());
			member.setMotherId(theirMother.getId());
			memberDAO.insert(member);
			break;
		case 41:
		case 42://儿女
			Member currentParent = memberDAO.get(memberId);
			if(currentParent.getGender() == 1) {
				member.setFatherId(memberId);
				Member currentMother = memberDAO.getFemaleSpouse(memberId);
				if(currentMother != null) {
					member.setMotherId(currentMother.getId());
				}
			} else {
				member.setMotherId(memberId);
				Member currentFather = memberDAO.get(currentParent.getSpouseId());
				member.setFatherId(currentFather.getId());
			}
			memberDAO.insert(member);
			break;
		default:
			break;
		}
	}

}
