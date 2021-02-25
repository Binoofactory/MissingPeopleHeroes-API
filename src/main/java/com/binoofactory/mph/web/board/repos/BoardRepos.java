package com.binoofactory.mph.web.board.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.binoofactory.mph.web.board.vo.BoardVO;

@Repository
public interface BoardRepos extends JpaRepository<BoardVO, Long> 
{
    @Query("SELECT nboard FROM BF_BOARD_BAS nboard WHERE nboard.bno = :bno")
    public BoardVO findByNo(@Param("bno")int bno);

	@Query(value=
			"      SELECT nboard.B_NO, nboard.TITLE, nboard.BOARD_GRP_DIV_CD, nboard.CUST_NO, nboard.CONTENTS, nboard.VISIBLE_YN "
			+ "        , nboard.REG_DT, nboard.EDT_DT, nboard.FILE_GRP_CD "
			+ "    FROM bf_board_bas nboard "
			+ "    WHERE nboard.BOARD_GRP_DIV_CD = :boardGrpType "
			+ "    AND nboard.BOARD_DIV_CD = :boardType "
			+ "    AND nboard.VISIBLE_YN = :isUsed "
			+ "    ORDER BY nboard.B_NO desc "
			+ "    LIMIT :limit OFFSET :offset "
			, nativeQuery=true)
	public List<BoardVO> findList(
			@Param("boardGrpType")String boardGrpType
			, @Param("boardType")String boardType
			, @Param("isUsed")String isUsed
			, @Param("offset")int offset
			, @Param("limit")int limit);
}