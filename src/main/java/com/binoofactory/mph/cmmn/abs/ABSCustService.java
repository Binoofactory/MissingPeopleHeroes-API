package com.binoofactory.mph.cmmn.abs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binoofactory.mph.cmmn.inf.IFBaseVO;
import com.binoofactory.mph.cmmn.inf.IFCommonService;

@SuppressWarnings({"unchecked","rawtypes"})
public abstract class ABSCustService implements IFCommonService<IFBaseVO> 
{
	protected JpaRepository jpaRepository;
	
	public abstract IFBaseVO find(IFBaseVO vo);
	public abstract List<IFBaseVO> findAll(IFBaseVO vo);
}
