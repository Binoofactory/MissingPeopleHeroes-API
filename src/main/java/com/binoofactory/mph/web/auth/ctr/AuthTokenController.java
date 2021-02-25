package com.binoofactory.mph.web.auth.ctr;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.binoofactory.mph.cmmn.data.ApiDataRstCd;
import com.binoofactory.mph.utils.ApiResponseUtil;
import com.binoofactory.mph.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "AuthTokenController", tags="인증 토큰")
@RequestMapping("/api/t1/auth/")
public class AuthTokenController {

	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private ApiResponseUtil apiResponseUtil;
	
	private final static String API_VERSION = "t.1";

	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(AuthTokenController.class);
	
	@ApiOperation(value = "version", notes = "버전 체크")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"), 
		@ApiResponse(code = 404, message = "Not Found !!"),
		@ApiResponse(code = 500, message = "Internal Server Error !!") 
	})
	@RequestMapping(method = RequestMethod.GET, value = "version")
	public Map<String, Object> chkVersion() {
		Map<String, Object> result = new HashMap<>();
		result.put("name", "witty");
		result.put("reqestTime", dateUtil.getDatetimeDetail());
		result.put("version", API_VERSION);
		result.put("encryptType", "SEED");
		return apiResponseUtil.makeResult(ApiDataRstCd.SEARCH_SUCCESS);
	}
	
	@ApiOperation(value = "token", notes = "신규 토큰 발급용 AUTH 계정 발급")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"), 
		@ApiResponse(code = 404, message = "Not Found !!"),
		@ApiResponse(code = 500, message = "Internal Server Error !!") 
	})
	@RequestMapping(method = RequestMethod.POST, value = "token")
	public Map<String, Object> newToken() {
		Map<String, Object> result = new HashMap<>();
		result.put("name", "witty");
		result.put("reqDate", dateUtil.getDatetimeDetail());
		result.put("version", API_VERSION);
		result.put("efctEncryptType", "SEED");
		return apiResponseUtil.makeResult(ApiDataRstCd.SEARCH_SUCCESS);
	}
}