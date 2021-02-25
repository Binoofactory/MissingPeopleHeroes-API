package com.binoofactory.mph.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class PageUtil {

	@Value("${board.default.page.number}")
	public Integer pageNo;
	@Value("${board.default.page.size}")
	public Integer pageSize;
	
	public class PageVO
	{
		public Integer pageNo = 0;
		public Integer pageSize = 0;
		public Integer limit = 0;
		public Integer offset = 0;
		
		public PageVO(Integer pageNo, Integer pageSize) 
		{
			this.pageNo = pageNo;
			this.pageSize = pageSize;
			this.limit = pageSize;
			this.offset = pageSize*(pageNo-1);
		}
	}

	public PageUtil.PageVO getDefaultPage()
	{
		return new PageUtil.PageVO(pageNo, pageSize);
	}
	public PageUtil.PageVO getDefaultPage(Integer pageNo, Integer pageSize)
	{
		if(pageNo == null || pageNo <= 0) pageNo = this.pageNo;
		if(pageSize == null || pageSize <= 0) pageSize = this.pageSize;
		
		return new PageUtil.PageVO(pageNo, pageSize);
	}
}
