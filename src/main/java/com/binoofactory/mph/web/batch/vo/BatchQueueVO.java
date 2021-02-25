package com.binoofactory.mph.web.batch.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Entity(name="UM_QUEUE_BAT")
@NoArgsConstructor 
public class BatchQueueVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Q_NO")
	public int qNo;
    @Column(name = "CUST_NO")
	public Integer userNo;
    @Column(name = "REG_DT")
	public String regDt;
    @Column(name = "REQ_CD")
	public String reqCd;
    @Column(name = "REQ_MSG")
	public String reqMsg;
    @Column(name = "PROC_YN")
	public String procYn;
    @Column(name = "PROC_DATA")
	public String procData;
    
    @Builder
    public BatchQueueVO(
    		Integer userNo
    		, String regDt
    		, String reqCd
    		, String reqMsg
    		, String procYn
    		, String procData
    		) {
        this.userNo = userNo;
        this.regDt = regDt;
        this.reqCd = reqCd;
        this.reqMsg = reqMsg;
        this.procYn = procYn;
        this.procData = procData;
    }
}
