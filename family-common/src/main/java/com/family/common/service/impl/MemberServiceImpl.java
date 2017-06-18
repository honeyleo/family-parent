package com.family.common.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.family.common.dao.UserContributionDAO;
import com.family.common.dao.UserDetailDAO;
import com.family.common.enums.ContributionType;
import com.family.common.enums.InOrOutType;
import com.family.common.model.Member;
import com.family.common.model.MemberDTO;
import com.family.common.model.MemberDTOWrapper;
import com.family.common.model.UserContributionRecord;
import com.family.common.model.UserDetailDTO;
import com.family.common.service.MemberService;
import com.family.common.service.UserContributionService;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.lfy.common.framework.exception.ApplicationException;
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
	
	@Autowired
	private UserContributionService userContributionService;
	
	@Autowired
	private UserContributionDAO userContributionDAO;
	
	@Override
	@Transactional
	public int delete(long userId, Long id) {
		Member member = get(id);
		if(member.getUserId().longValue() == userId) {
			throw ApplicationException.newInstance(ErrorCode.SERVER_ERROR, "不能删除自己");
		}
		if(member != null) {
			if(member.getCreatorUserId().longValue() == userId) {
				Boolean hasHusband = memberDAO.hasHusband(id);
				if(hasHusband != null && hasHusband) {
					throw ApplicationException.newInstance(ErrorCode.SERVER_ERROR, "有丈夫，请先删除丈夫");
				}
				Boolean hasDaughtershusband = memberDAO.hasDaughtershusband(id);
				if(hasDaughtershusband != null && hasDaughtershusband) {
					throw ApplicationException.newInstance(ErrorCode.SERVER_ERROR, "有女婿，请先删除女婿");
				}
				if(memberDAO.hasGrandChildren(id)) {
					throw ApplicationException.newInstance(ErrorCode.SERVER_ERROR, "有孙子孙女，请先从叶子节点删除");
				}
				if(member.getGender() == 1) {
					memberDAO.updateChildrenOfFatherId(id);
					memberDAO.updateSpouseIdForZero(member.getId());
				}
				if(member.getGender() == 2) {
					memberDAO.deleteChildren(id, userId);
				}
				return memberDAO.delete(id);
			}
		}
		return 0;
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
	
	public MemberDTOWrapper familyTree(Long userId) {
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
			member.setDivorced(0);
			memberDAO.insert(member);
			
		}
		List<UserContributionRecord> userContributionRecord = userContributionService.getUserContributionRecord(userId, ContributionType.CREATE_FAMILY_TREE, InOrOutType.INCOME);
		if(userContributionRecord == null || userContributionRecord.isEmpty()) {
			UserContributionRecord entity = new UserContributionRecord();
			entity.setUserId(userId);
			entity.setContribution(100);
			entity.setType(ContributionType.CREATE_FAMILY_TREE.getType());
			entity.setInOrOutType(InOrOutType.INCOME.getType());
			entity.setCreateTime(System.currentTimeMillis()/1000);
			int ret = userContributionDAO.addUserContributionRecord(entity);
			if(ret > 0) {
				userDetailDAO.addContribution(userId, 100);
			}
		}
		int total = 0;
		List<MemberDTO> result = new ArrayList<MemberDTO>(); 
		Set<Long> daughtersHusband = Sets.newHashSet();
		try {   
	        List<Member> itemList = memberDAO.list(userId); 
	        total = itemList.size();
	        Map<Long, MemberDTO> allMap = Maps.newHashMap();
	        for(Member member : itemList) {
	        	MemberDTO memberDTO = new MemberDTO();
	        	try {
					BeanUtils.copyProperties(memberDTO, member);
					if(StringUtils.isNotBlank(memberDTO.getAvatar())) {
						memberDTO.setAvatar(imageUrl + memberDTO.getAvatar());
					}
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
	        	allMap.put(member.getId(), memberDTO);
	        }
	        for(Member member : itemList) {
	        	MemberDTO memberDTO = allMap.get(member.getId());
	        	if(member.getMotherId() > 0) {
	        		allMap.get(member.getMotherId()).getChildrens().add(memberDTO);
	        	} 
	        	if(member.getMotherId() == 0 && member.getFatherId() > 0) {
	        		allMap.get(member.getFatherId()).getChildrens().add(memberDTO);
	        	} 
	        	if(member.getGender() == 2) {
	        		MemberDTO spouse = allMap.get(member.getSpouseId());
	        		if(spouse != null) {
	        			spouse.getSpouses().add(memberDTO);
	        		}
	        	}
	        	if(member.getMotherId() > 0 && member.getFatherId() > 0 && member.getGender() == 2) {
	        		MemberDTO spouse = allMap.get(member.getSpouseId());
	        		if(spouse != null) {
	        			daughtersHusband.add(member.getSpouseId());
	        			memberDTO.getSpouses().add(spouse);
	        		}
	        	}
	        	if(member.getFatherId() == 0 && member.getMotherId() == 0 && member.getGender() == 1) {
	        		result.add(memberDTO);
	        	}
	        }
	        Iterator<MemberDTO> it = result.iterator();
	        while(it.hasNext()) {
	        	MemberDTO member = it.next();
	        	if(daughtersHusband.contains(member.getId())) {
	        		Set<MemberDTO> set = Sets.newHashSet();
	        		member.setSpouses(set);
	        		it.remove();
	        	}
	        }
	        if(result.isEmpty()) {
	        	for(Member member : itemList) {
	        		if(member.getFatherId() == 0 && member.getMotherId() == 0 && member.getGender() == 2 && member.getSpouseId() == 0) {
	        			MemberDTO memberDTO = allMap.get(member.getId());
		        		result.add(memberDTO);
		        	}
	        	}
	        }
	    } catch(Exception e) {  
	        
	    }  
		MemberDTOWrapper wrapper = new MemberDTOWrapper(total, result);
		return wrapper;
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
		
		if(relation != 22) {
			member.setGender(relation%2 == 0 ? 2 : 1);
		}
		switch (relation) {
		case 11://父亲
			Member father = memberDAO.getFather(memberId);
			Validators.isFalse(father != null, ErrorCode.VALUE_EXIST, "父亲");
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
		case 13://继父
			Member father2 = memberDAO.getFather(memberId);
			Validators.isFalse(father2 != null, ErrorCode.VALUE_EXIST, "母亲");
			int ret2 = memberDAO.insert(member);
			Validators.isFalse(ret2 <= 0, ErrorCode.SERVER_ERROR);
			Member tmp2 = new Member();
			tmp2.setId(memberId);
			tmp2.setFatherId(member.getId());
			memberDAO.updateParent(tmp2);
			break;
		case 22://配偶
			Member current = memberDAO.get(memberId);
			if(current.getGender() == 1) {//给男丁添加配偶
				member.setSpouseId(memberId);
				memberDAO.insert(member);
//				memberDAO.updateChildrenOfMother(memberId, member.getId());
			} else {//给女丁添加配偶
				memberDAO.insert(member);
				Member tmp22 = new Member();
				tmp22.setId(memberId);
				tmp22.setSpouseId(member.getId());
				memberDAO.update(tmp22);
				memberDAO.updateChildrenOfFather(memberId, member.getId());
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
			if(currentParent.getGender() == 2) {
				member.setMotherId(memberId);
				Member currentFather = memberDAO.get(currentParent.getSpouseId());
				if(currentFather != null) {
					member.setFatherId(currentFather.getId());
				}
				memberDAO.insert(member);
			} else {
				throw ApplicationException.newInstance(ErrorCode.SERVER_ERROR, "子女只能从女性上添加");
			}
			break;
		
		case 43://添加继子/继女，只能从男性添加
		case 44:
			Member current2 = memberDAO.get(memberId);
			if(current2.getGender() == 1) {
				member.setFatherId(memberId);
				memberDAO.insert(member);
			} else {
				throw ApplicationException.newInstance(ErrorCode.SERVER_ERROR, "继子女只能从男性上添加");
			}
			break;
		default:
			break;
		}
	}

}
