package com.binoofactory.mph.utils;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

	@Value("${user.upload.path}")
	public String uploadPath;
	
	@Value("${user.upload.web.path}")
	public String webPath;
	
	@Value("${user.upload.allow.type}")
	public String allowType;

	@Autowired
	private DateUtil dateUtil;
	
	public String getExt(MultipartFile mFile)	
	{
		if(mFile == null) return "";
		
		return FilenameUtils.getExtension(mFile.getOriginalFilename());
	}
	public boolean chkFileType(MultipartFile file)
	{
		String[] allowExtTypes = allowType.split(",");
		String ext = (""+getExt(file)).toLowerCase();
		
		for(String allowExtType : allowExtTypes)
			if(ext.equals(allowExtType)) return true;
		return false;
	}
	public String save(MultipartFile file, String basePath) throws Exception
	{
		if(!chkFileType(file)) 
			throw new Exception("");

		StringBuilder serverFilePath = new StringBuilder();
		serverFilePath.append( uploadPath );
		serverFilePath.append( basePath );
		serverFilePath.append( "/" );
		serverFilePath.append( dateUtil.getDatetime() );
		serverFilePath.append( "/" );
		
		if(!new File(serverFilePath.toString()).exists())
			new File(serverFilePath.toString()).mkdirs();
		
		serverFilePath.append( dateUtil.getDatetimeZoneDetail() );
		serverFilePath.append( "." + getExt(file) );
		file.transferTo(new File(serverFilePath.toString()));
		
		return serverFilePath.toString();
	}
}
