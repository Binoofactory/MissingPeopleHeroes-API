package com.binoofactory.mph.web.board.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.binoofactory.mph.cmmn.inf.IFCommonService;
import com.binoofactory.mph.web.board.repos.BoardRepos;
import com.binoofactory.mph.web.board.vo.BoardVO;

@Service
public class BaordService implements IFCommonService<BoardVO>{

	@Autowired
	private BoardRepos boardRepos;

	@Transactional(readOnly = true)
	public List<BoardVO> findList(
			String boardGrpType, String boardType, String isUsed, int offset, int limit) {
		return boardRepos.findList(boardGrpType, boardType, isUsed, offset, limit);
	}

	public boolean remove(BoardVO vo) {
		if(vo == null) return false;
		boardRepos.delete(vo);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public BoardVO find(BoardVO vo) {
		return boardRepos.findByNo(vo.bno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BoardVO> findAll(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardRepos.findAll();
//		return boardRepos.findAll(, Sort.by(Direction.DESC, ""));
	}

	@Override
	public int count(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean save(BoardVO vo) {
		boardRepos.save(vo);
		return true;
	}
}
