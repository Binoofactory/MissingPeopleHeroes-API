package com.binoofactory.mph.web.calendar.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.binoofactory.mph.cmmn.inf.IFCommonService;
import com.binoofactory.mph.web.calendar.repos.MissingPeopleRepos;
import com.binoofactory.mph.web.calendar.vo.MissingPeopleVO;

@Service
public class MissingPeopleService implements IFCommonService<MissingPeopleVO>{

	@Autowired
	private MissingPeopleRepos videoInfoRepos;

	@Override
	@Transactional(readOnly = true)
	public MissingPeopleVO find(MissingPeopleVO vo) {
		// TODO Auto-generated method stub
		return videoInfoRepos.findByNo(vo.lostNo);
	}
	@Transactional(readOnly = true)
	public List<MissingPeopleVO> findAll(String sttTime, int offset, int limit) {
		return videoInfoRepos.findList(sttTime, offset, limit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MissingPeopleVO> findAll(MissingPeopleVO vo) {
		return videoInfoRepos.findAll();
	}

	@Override
	public int count(MissingPeopleVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean save(MissingPeopleVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(MissingPeopleVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

}
