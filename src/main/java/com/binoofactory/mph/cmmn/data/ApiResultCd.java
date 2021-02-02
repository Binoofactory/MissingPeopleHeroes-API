package com.binoofactory.mph.cmmn.data;

import lombok.Getter;

@Getter
public enum ApiResultCd 
{
	FAILURE("F", false)
	, SUCCESS("S", true);
	
	private String sttCd;
	private boolean isApiResultStt;

	ApiResultCd(String sttCd, boolean isApiResultStt) 
	{
		this.sttCd = sttCd;
		this.isApiResultStt = isApiResultStt;
	}
}