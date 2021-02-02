package com.binoofactory.mph.web.missingpeople.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity(name="PRJ01_LST_INFO")
@NoArgsConstructor 
public class MissingPeopleVO {
	
	@Id
    @Column(name = "LST_NO")
	public int lostNo;
    @Column(name = "LST_NM")
	public String name;
    @Column(name = "LST_GD")
	public String gender;
    @Column(name = "LST_CLTH")
	public String descriptionCloth;
    @Column(name = "LST_PR_AGE")
	public String ageOfNow;
    @Column(name = "LST_PS_AGE")
	public String ageAtTime;
    @Column(name = "LST_DEPT")
	public String lostDepart;
    @Column(name = "LST_LOC")
	public String lostLocation;
    @Column(name = "LST_IMG")
	public String profileImage;
    @Column(name = "LST_TM")
	public String lostTime;
    @Column(name = "REQ_NO")
	public String reqNo;
    @Column(name = "REQ_DT")
	public String reqDt;
    @Column(name = "EFCT_END_DT")
	public String efectEndTime;
    @Column(name = "LST_EDIT_DT")
	public String lastestUpdateTime;
    @Column(name = "LST_DT")
	public String lostDt;
    
    @Builder
    public MissingPeopleVO(
    		String name
    		, String gender
    		, String descriptionCloth
    		, String ageOfNow
    		, String ageAtTime
    		, String lostDepart
    		, String lostLocation
    		, String profileImage
    		, String lostTime
    		, String reqNo
    		, String reqDt
    		, String efectEndTime
    		, String lastestUpdateTime
    		, String lostDt
    		) {
        this.name = name;
        this.gender = gender;
        this.descriptionCloth = descriptionCloth;
        this.ageOfNow = ageOfNow;
        this.ageAtTime = ageAtTime;
        this.lostDepart = lostDepart;
        this.lostLocation = lostLocation;
        this.profileImage = profileImage;
        this.lostTime = lostTime;
        this.reqNo = reqNo;
        this.reqDt = reqDt;
        this.efectEndTime = efectEndTime;
        this.lastestUpdateTime = lastestUpdateTime;
        this.lostDt = lostDt;
    }
}
