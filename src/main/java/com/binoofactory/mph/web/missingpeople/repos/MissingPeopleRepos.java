package com.binoofactory.mph.web.missingpeople.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.binoofactory.mph.web.missingpeople.vo.MissingPeopleVO;

@Repository
public interface MissingPeopleRepos extends JpaRepository<MissingPeopleVO, Long> 
{
	@Query("SELECT info FROM PRJ01_LST_INFO info WHERE info.lostNo = :lostNo")
	public MissingPeopleVO findByNo(@Param("lostNo")int lostNo);

	@Query("SELECT info FROM PRJ01_LST_INFO info WHERE info.userNo = :userNo AND info.lastestUpdateTime > :sttTime ")
	public MissingPeopleVO findOutLiveItem(@Param("userNo")int userNo, @Param("sttTime")String sttTime);
	
	@Query(value=
			"      SELECT lost.LST_NO, lost.LST_NM, lost.LST_GD, lost.LST_CLTH, lost.LST_PR_AGE, lost.LST_PS_AGE, lost.LST_DEPT, lost.LST_LOC "
			+ "        , lost.LST_IMG, lost.LST_TM, lost.REQ_NO, lost.REQ_DT, lost.EFCT_END_DT, lost.LST_EDIT_DT, lost.LST_DT "
			+ "    FROM PRJ01_LST_INFO lost "
			+ "    WHERE lost.lastestUpdateTime > :sttTime "
			+ "    ORDER BY lost.LST_NO desc "
			+ "    LIMIT :limit OFFSET :offset "
			, nativeQuery=true)
	public List<MissingPeopleVO> findList(
			@Param("sttTime")String sttTime
			, @Param("offset")int offset
			, @Param("limit")int limit);
}
