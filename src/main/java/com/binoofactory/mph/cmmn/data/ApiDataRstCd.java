package com.binoofactory.mph.cmmn.data;

import lombok.Getter;

@Getter
public enum ApiDataRstCd 
{
	EXIST_USR_ERR("해당 아이디로 가입된 유저가 있습니다.", false, "E0101")
	, NONE_EXIST_USR_ERR("해당 아이디로 가입된 유저가 없습니다.", false, "E0102")
	, INTERNAL_USR_DATA_ERR("유저 데이터가 이상합니다. 운영팀에 문의해주세요.", false, "E0103")
	, NONE_MATCH_USR_PW_ERR("비밀번호가 일치하지 않습니다.", false, "E0201")
	, POSIBLE_SIGNUP_ID("해당 아이디로 가입이 가능합니다.", true, "S0101")
	, MODIFY_USR_DATA("수정 되었습니다.", true, "S0102")
	, NONE_SUB_ACCOUNT("해당 유튜버는 서브계정이 하나도 없습니다.", true, "S0301")
	, NONE_YOUTUBER_ACCOUNT("해당 유튜버는 존재하지 않습니다.", false, "E0302")
	, NONE_MANAGER_ACCOUNT("해당 서브계정은 존재하지 않습니다.", false, "E0303")
	, SEARCH_SUCCESS("조회 성공", true, "S0401")
	, EMPTY_RESULT("조회 결과가 없습니다.", true, "S0402")
	, SEND_PASSWORD_SUCCESS("비밀번호 전송 완료", true, "S1001")
	, NOT_ALLOWED_PERMISSION("권한이 부족합니다.", false, "E1001")
	, NOT_ALLOWED_INSERT_CMMN_CD("이미 존재하는 공통 코드 데이터입니다.", false, "E1002")
	, NOT_ALLOWED_EDIT_CMMN_CD("존재하지 않는 공통 코드 데이터입니다.", false, "E1003")
	, OVER_TIME_RESET_PASSWORD("링크 유효시간이 초과하였습니다. 재발급 링크를 다시 받아주세요.", false, "E1004")
	, NOT_FOUND_DATA("존재하지 않는 데이터입니다.", false, "E1004")
	, MODIFY_CMMN_DATA("수정 되었습니다.", true, "S1002")
	, ADD_CMMN_DATA("추가 되었습니다.", true, "S1003")
	, REMOVE_CMMN_DATA("삭제 되었습니다.", true, "S1004")
	, INTERNAL_ERR("오류가 발생했습니다.", false, "E1005")
	, DEV_TEST_OK("테스트 케이스가 추가되었습니다.", true, "S9001")
	;

	private String sttNm;
	private boolean isSuccess;
	private String sttCd;

	ApiDataRstCd(String sttNm, boolean isSuccess, String sttCd) 
	{
		this.sttNm = sttNm;
		this.isSuccess = isSuccess;
		this.sttCd = sttCd;
	}
}