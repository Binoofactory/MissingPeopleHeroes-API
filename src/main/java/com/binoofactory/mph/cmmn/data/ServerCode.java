package com.binoofactory.mph.cmmn.data;

import lombok.Getter;

@Getter
public enum ServerCode {
	RESOURCE_SERVER(0, "RESOURCE")
	, AUTH_SERVER(1, "AUTH")
	, BATCH_SERVER(2, "BATCH")
	, ADMIN_SERVER(3, "ADMIN")
	, CHAT_SERVER(4, "CHAT")
	
	, DEV_SERVER(9, "DEV");
	
	private int serverDivCd;
	private String serverCaption;

	ServerCode(int serverDivCd, String serverCaption) 
	{
		this.serverDivCd = serverDivCd;
		this.serverCaption = serverCaption;
	}
}
