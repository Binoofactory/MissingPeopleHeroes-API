package com.binoofactory.mph.web.batch.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.binoofactory.mph.web.batch.vo.BatchQueueVO;

@Repository
public interface BatchQueueRepos extends JpaRepository<BatchQueueVO, Long> 
{
	  @Query("SELECT batch FROM UM_QUEUE_BAT batch WHERE batch.qNo = :qNo")
	  public BatchQueueVO findByNo(@Param("qNo")int qNo);

	  @Query("SELECT batch FROM UM_QUEUE_BAT batch WHERE batch.procYn = :procYn AND batch.userNo = :userNo")
	  public BatchQueueVO findByNoAndProcYn(@Param("procYn")String procYn, @Param("userNo")int userNo);

	  @Query("SELECT batch FROM UM_QUEUE_BAT batch WHERE batch.procYn = :procYn AND batch.reqCd = :reqCd")
	  public List<BatchQueueVO> findByReqCdAndProcYn(@Param("procYn")String procYn, @Param("reqCd")String reqCd);

	  @Query("SELECT batch FROM UM_QUEUE_BAT batch WHERE batch.procYn = :procYn AND batch.userNo = :userNo AND batch.reqCd = :reqCd")
	  public BatchQueueVO findByNoAndProcAndReqCd(@Param("procYn")String procYn, @Param("userNo")int userNo, @Param("reqCd")String reqCd);
	  
	  @Query("SELECT batch FROM UM_QUEUE_BAT batch WHERE batch.procYn = :procYn AND batch.userNo = :userNo AND batch.reqCd = :reqCd AND batch.procData = :procData ")
	  public BatchQueueVO findByNoAndProcAndReqCdN(@Param("procYn")String procYn, @Param("userNo")int userNo, @Param("reqCd")String reqCd, @Param("procData")String procData);
}