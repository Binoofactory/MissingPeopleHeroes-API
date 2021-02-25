package com.binoofactory.mph.web.batch.svc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.binoofactory.mph.web.batch.repos.BatchQueueRepos;
import com.binoofactory.mph.web.batch.vo.BatchQueueVO;

@Service
public class BatchQueueService {
	
	private Log log = LogFactory.getLog(BatchQueueService.class);
	
	@Autowired
	private BatchQueueRepos batchQueueRepos;

	public void addJob(BatchQueueVO batchQueueVO) 
	{
		batchQueueRepos.save(batchQueueVO);
	}
	public void addJob(Integer userNo, String batchType, String batchComments, String requestTime) 
	{
		BatchQueueVO batchQueueVO = BatchQueueVO.builder()
				.procYn("N")
				.userNo(userNo)
				.regDt(requestTime)
				.reqCd(batchType)
				.reqMsg(batchComments)
				.build();
		batchQueueRepos.save(batchQueueVO);
		
		log.info("Call the Job : \nTitle : ["+batchType+"]\nContents : "
				+batchComments+"\nTicket request time : "+requestTime);
	}
	public void addJobDetail(Integer userNo, String batchType, String batchComments, String requestTime, String procData) 
	{
		BatchQueueVO batchQueueVO = BatchQueueVO.builder()
				.procYn("N")
				.userNo(userNo)
				.regDt(requestTime)
				.reqCd(batchType)
				.reqMsg(batchComments)
				.procData(procData)
				.build();
		batchQueueRepos.save(batchQueueVO);
		
		log.info("Call the Job : \nTitle : ["+batchType+"]\nContents : "
				+batchComments+"\nTicket request time : "+requestTime);
	}
	@Transactional(readOnly = true)
	public List<BatchQueueVO> findJobs(String procYn, String reqCd) 
	{
		return batchQueueRepos.findByReqCdAndProcYn(procYn, reqCd);
	}
	@Transactional(readOnly = true)
	public BatchQueueVO findJob(Integer userNo, String procYn, String reqCd) 
	{
		return batchQueueRepos.findByNoAndProcAndReqCd(procYn, userNo, reqCd);
	}
	@Transactional(readOnly = true)
	public BatchQueueVO findJob(Integer userNo, String procYn, String reqCd, String searchDate) 
	{
		return batchQueueRepos.findByNoAndProcAndReqCdN(procYn, userNo, reqCd, searchDate);
	}
}
