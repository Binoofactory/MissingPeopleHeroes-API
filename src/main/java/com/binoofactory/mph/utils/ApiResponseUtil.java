package com.binoofactory.mph.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.binoofactory.mph.cmmn.data.ApiDataRstCd;
import com.binoofactory.mph.cmmn.inf.IFBaseVO;

@Component
public class ApiResponseUtil
{
	@Autowired
	private DateUtil dateUtil;

	public Map<String, Object> makeResult(ApiDataRstCd apiDataRstCd)
	{
		Map<String, Object> rstMap = new HashMap<String, Object>();
		rstMap.put("isSuccess", apiDataRstCd.isSuccess());
		rstMap.put("resultMessage", apiDataRstCd.getSttNm());
		rstMap.put("resultCode", apiDataRstCd.getSttCd());
		rstMap.put("responseTime", dateUtil.getDatetimeDetail());
		return rstMap;
	}
	public Map<String, Object> makeResult(
			Object data
			, Integer pageNo
			, Integer pageSize
			, Integer dataSize
			, ApiDataRstCd apiDataRstCd)
	{
		Map<String, Object> rstMap = new HashMap<String, Object>();
		rstMap.put("data", data);
		rstMap.put("dataSize", dataSize);
		rstMap.put("pageNo", pageNo);
		rstMap.put("pageSize", pageSize);
		rstMap.put("isSuccess", apiDataRstCd.isSuccess());
		rstMap.put("resultMessage", apiDataRstCd.getSttNm());
		rstMap.put("resultCode", apiDataRstCd.getSttCd());
		rstMap.put("responseTime", dateUtil.getDatetimeDetail());
		return rstMap;
	}
	public Map<String, Object> makeResult(
			Object data
			, Integer pageNo
			, Integer pageSize
			, boolean haveNextPage
			, ApiDataRstCd apiDataRstCd)
	{
		Map<String, Object> rstMap = new HashMap<String, Object>();
		rstMap.put("data", data);
		rstMap.put("haveNextPage", haveNextPage);
		rstMap.put("pageNo", pageNo);
		rstMap.put("pageSize", pageSize);
		rstMap.put("isSuccess", apiDataRstCd.isSuccess());
		rstMap.put("resultMessage", apiDataRstCd.getSttNm());
		rstMap.put("resultCode", apiDataRstCd.getSttCd());
		rstMap.put("responseTime", dateUtil.getDatetimeDetail());
		return rstMap;
	}
	public Map<String, Object> makeResult(
			@SuppressWarnings("rawtypes") List data
			, Integer pageNo
			, Integer pageSize
			, Integer size
			, ApiDataRstCd apiDataRstCd)
	{
		Map<String, Object> rstMap = new HashMap<String, Object>();
		rstMap.put("data", data);
		rstMap.put("dataSize", size);
		rstMap.put("pageNo", pageNo);
		rstMap.put("pageSize", pageSize);
		rstMap.put("isSuccess", apiDataRstCd.isSuccess());
		rstMap.put("resultMessage", apiDataRstCd.getSttNm());
		rstMap.put("resultCode", apiDataRstCd.getSttCd());
		rstMap.put("responseTime", dateUtil.getDatetimeDetail());
		return rstMap;
	}
	public Map<String, Object> makeResult(
			@SuppressWarnings("rawtypes") List data, ApiDataRstCd apiDataRstCd)
	{
		Map<String, Object> rstMap = new HashMap<String, Object>();
		rstMap.put("data", data);
		if(data == null)
			rstMap.put("dataSize", 0);
		else
			rstMap.put("dataSize", data.size());
		rstMap.put("isSuccess", apiDataRstCd.isSuccess());
		rstMap.put("resultMessage", apiDataRstCd.getSttNm());
		rstMap.put("resultCode", apiDataRstCd.getSttCd());
		rstMap.put("responseTime", dateUtil.getDatetimeDetail());
		return rstMap;
	}
	public Map<String, Object> makeResult(
			IFBaseVO vo, ApiDataRstCd apiDataRstCd) {
		Map<String, Object> rstMap = new HashMap<String, Object>();
		rstMap.put("data", vo);
		rstMap.put("isSuccess", apiDataRstCd.isSuccess());
		rstMap.put("resultMessage", apiDataRstCd.getSttNm());
		rstMap.put("resultCode", apiDataRstCd.getSttCd());
		rstMap.put("responseTime", dateUtil.getDatetimeDetail());
		return null;
	}
}
