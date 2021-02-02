package com.binoofactory.mph.web.calendar.ctr;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binoofactory.mph.cmmn.data.ApiDataRstCd;
import com.binoofactory.mph.utils.ApiResponseUtil;
import com.binoofactory.mph.utils.PageUtil;
import com.binoofactory.mph.utils.StringUtil;
import com.binoofactory.mph.web.calendar.svc.MissingPeopleService;
import com.binoofactory.mph.web.calendar.vo.MissingPeopleVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "실종자", tags="실종자")
@RequestMapping("/api/t1/info/")
public class MissingPeopleController {

	@Autowired
	private MissingPeopleService missingPeopleService;
	@Autowired
	private StringUtil stringUtil;
	
	@Autowired
	private ApiResponseUtil apiResponseUtil;
	@Autowired
	private PageUtil pageUtil;

	private Log log = LogFactory.getLog(MissingPeopleController.class);

	@ApiOperation(value = "missing-people", notes = "실종자 조회 (경찰청 주기적 연동)")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"), 
		@ApiResponse(code = 404, message = "Not Found !!"),
		@ApiResponse(code = 500, message = "Internal Server Error !!") 
	})
	@RequestMapping(method = RequestMethod.GET, value = "missing-people")
	public Map<String, Object> findSimple(
			@ApiParam(value = "일정 조회 월 또는 일 (yyyyMM, yyyyMMdd)", required = true, example = "202012") @RequestParam(required=true) String searchDate,
			@ApiParam(value = "페이지번호", required = false, example = "1") @RequestParam(required=false) Integer pageNo,
			@ApiParam(value = "페이지 크기", required = false, example = "10") @RequestParam(required=false) Integer pageSize
			) throws Exception {

		PageUtil.PageVO pageVO = pageUtil.getDefaultPage(pageNo, pageSize);
		List<MissingPeopleVO> missingPeoples = 
				missingPeopleService.findAll(stringUtil.fillStr(searchDate, "0", 17), pageVO.limit, pageVO.offset);
		log.debug( "Test :: "+ searchDate );

		return apiResponseUtil.makeResult(missingPeoples, pageNo, pageSize, missingPeoples == null ? 0 : missingPeoples.size(), ApiDataRstCd.SEARCH_SUCCESS);
	}
}
