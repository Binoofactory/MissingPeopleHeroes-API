package com.binoofactory.mph.web.vo;

import com.binoofactory.mph.cmmn.inf.IFBaseVO;
import com.binoofactory.mph.web.board.vo.BoardVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor 
@ApiModel
public class Board implements IFBaseVO{
	
	public @ApiModelProperty(value = "식별번호", required = false, example = "1") Integer bno;
	public @ApiModelProperty(value = "게시판구분", required = true, example = "notice") String boardGrpType;
	public @ApiModelProperty(value = "게시판명", required = true, example = "event") String boardType;
	public @ApiModelProperty(value = "제목", required = true, example = "제목이 들어갑니다.") String title;
	public @ApiModelProperty(value = "내용", required = true, example = "내용이 들어갑니다.") String contents;
	public @ApiModelProperty(value = "보여줄것인가 (Y/N)", required = false, example = "Y") String isUsed;
	public @ApiModelProperty(value = "생성일시", required = false, example = "20210202141400") String regDt;
	public @ApiModelProperty(value = "수정일시", required = false, example = "20210202141400") String edtDt;
	public @ApiModelProperty(value = "파일그룹코드", required = false, example = "") Integer fileGrpCd;
    
    public Board(BoardVO boardVO) {
    	this.bno = boardVO.bno == null ? 0 : boardVO.bno;
        this.title = boardVO.title == null ? "" : boardVO.title;
        this.boardGrpType = boardVO.boardGrpType == null ? "" : boardVO.boardGrpType;
        this.boardType = boardVO.boardType == null ? "" : boardVO.boardType;
        this.isUsed = boardVO.isUsed == null || "Y".equals(boardVO.isUsed) ? "Y" : "N";
        this.regDt = boardVO.regDt;
        this.edtDt = boardVO.edtDt;
        this.fileGrpCd = boardVO.fileGrpCd == null ? 0 : boardVO.fileGrpCd;
    }
}
