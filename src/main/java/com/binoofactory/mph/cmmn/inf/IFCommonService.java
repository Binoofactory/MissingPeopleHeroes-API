package com.binoofactory.mph.cmmn.inf;

import java.util.List;

public interface IFCommonService<VO> 
{
	public VO find(VO vo);
	public List<VO> findAll(VO vo);
	public int count(VO vo);
	public boolean save(VO vo);
	public boolean remove(VO vo);
}
