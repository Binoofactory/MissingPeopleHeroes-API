package com.binoofactory.mph.web.board.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.binoofactory.mph.cmmn.inf.IFBaseVO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@Entity(name="BF_BOARD_BAS")
@NoArgsConstructor 
public class BoardVO implements IFBaseVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_NO")
	public Integer bno;
    @Column(name = "TITLE")
	public String title;
    @Column(name = "BOARD_GRP_DIV_CD")
	public String boardGrpType;
    @Column(name = "BOARD_DIV_CD")
	public String boardType;
    @Lob
    @Column(name = "CONTENTS")
	public String contents;
    @Column(name = "VISIBLE_YN")
	public String isUsed;
    @Column(name = "REG_DT")
	public String regDt;
    @Column(name = "EDT_DT")
	public String edtDt;
    @Column(name = "FILE_GRP_CD")
	public Integer fileGrpCd;
    
    @Builder
    public BoardVO(
    		String title
    		, String boardGrpType
    		, String boardType
    		, String contents
    		, String isUsed
    		, String regDt
    		, String edtDt
    		, Integer fileGrpCd
    		) {
        this.title = title;
        this.boardGrpType = boardGrpType;
        this.boardType = boardType;
        this.isUsed = isUsed;
        this.regDt = regDt;
        this.edtDt = edtDt;
        this.fileGrpCd = fileGrpCd;
    }
}
