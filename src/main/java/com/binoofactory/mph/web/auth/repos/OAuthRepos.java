package com.binoofactory.mph.web.auth.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.binoofactory.mph.web.auth.vo.OAuthVO;

@Repository
public interface OAuthRepos extends JpaRepository<OAuthVO, Long> 
{
	  @Query(value=
			"      SELECT tkn "
	  		+ "    FROM UM_AUTH_TKN tkn "
	  		+ "    WHERE tkn.username = :userName ")
	  public OAuthVO findByUserName(@Param("userName") String userName);
}