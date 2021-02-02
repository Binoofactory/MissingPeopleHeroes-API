package com.binoofactory.mph.web.vo;

import com.binoofactory.mph.cmmn.inf.IFBaseVO;
import com.binoofactory.mph.web.calendar.vo.MissingPeopleVO;

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
public class MissingPeople implements IFBaseVO{

	public @ApiModelProperty(value = "식별번호", required = false, example = "1")Integer lostNo;
	public @ApiModelProperty(value = "이름", required = false, example = "1")String name;
	public @ApiModelProperty(value = "성별", required = false, example = "1")String gender;
	public @ApiModelProperty(value = "실종 당시 옷차림", required = false, example = "1")String descriptionCloth;
	public @ApiModelProperty(value = "현재 나이", required = false, example = "1")String ageOfNow;
	public @ApiModelProperty(value = "실종 당시 나이", required = false, example = "1")String ageAtTime;
	public @ApiModelProperty(value = "실종 구역", required = false, example = "1")String lostDepart;
	public @ApiModelProperty(value = "실종 장소", required = false, example = "1")String lostLocation;
	public @ApiModelProperty(value = "사진", required = false, example = "1")String profileImage;
	public @ApiModelProperty(value = "실종일자", required = false, example = "1")String lostTime;
	public @ApiModelProperty(value = "요청횟수", required = false, example = "1")String reqNo;
	public @ApiModelProperty(value = "최종 요청일시", required = false, example = "1")String reqDt;
	public @ApiModelProperty(value = "유효 실종 일시", required = false, example = "1")String efectEndTime;
	public @ApiModelProperty(value = "최근 업데이트 일시", required = false, example = "1")String lastestUpdateTime;
	public @ApiModelProperty(value = "실종 정보", required = false, example = "1")String lostDt;

    public MissingPeople(MissingPeopleVO missingPeopleVO) {
    	this.lostNo = missingPeopleVO.lostNo;
        this.name = missingPeopleVO.name == null ? "" : missingPeopleVO.name;
        this.gender = missingPeopleVO.gender == null ? "" : missingPeopleVO.gender;
        this.descriptionCloth = missingPeopleVO.descriptionCloth == null ? "" : missingPeopleVO.descriptionCloth;
        this.ageOfNow = missingPeopleVO.ageOfNow == null ? "" : missingPeopleVO.ageOfNow;
        this.ageAtTime = missingPeopleVO.ageAtTime == null ? "" : missingPeopleVO.ageAtTime;
        this.lostDepart = missingPeopleVO.lostDepart == null ? "" : missingPeopleVO.lostDepart;
        this.lostLocation = missingPeopleVO.lostLocation == null ? "" : missingPeopleVO.lostLocation;
        this.profileImage = missingPeopleVO.profileImage == null ? "" : missingPeopleVO.profileImage;
        this.lostTime = missingPeopleVO.lostTime == null ? "" : missingPeopleVO.lostTime;
        this.reqNo = missingPeopleVO.reqNo == null ? "" : missingPeopleVO.reqNo;
        this.reqDt = missingPeopleVO.reqDt == null ? "" : missingPeopleVO.reqDt;
        this.efectEndTime = missingPeopleVO.efectEndTime == null ? "" : missingPeopleVO.efectEndTime;
        this.lastestUpdateTime = missingPeopleVO.lastestUpdateTime == null ? "" : missingPeopleVO.lastestUpdateTime;
        this.lostDt = missingPeopleVO.lostDt == null ? "" : missingPeopleVO.lostDt;
    }
}
