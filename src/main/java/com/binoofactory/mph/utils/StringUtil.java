package com.binoofactory.mph.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.binoofactory.mph.cmmn.lib.KISASeedCbc;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minidev.json.JSONArray;

@Component
public final class StringUtil {

	public final int UTIL_VERSION = 1;
	public final String CHAR_SET = "UTF-8";

	@Value("${encrypt.seed.key}")
	public String pbszUserKey;
	
	@Value("${encrypt.seed.iv}")
	public String pbszIV;
	
	public String fillStr(String target, String symbol, int size)
	{
		if(isEmpty(target)) target = "";
		if(target.length() >= size) return target;
		StringBuffer sb = new StringBuffer();
		sb.append(target);
		
		int pt = target.length();
		do
		{
			sb.append(symbol); 
			pt++;
		}
		while(pt < size);
		return sb.toString();
	}
	
	public boolean isNumeric(String target)
	{
		if(isEmpty(target)) return false;
		String regx = "^[0-9]+";
		return target.matches(regx);
	}
	
	public int toNumber(String target)
	{
		if(!isNumeric(target)) return 0;
		return Integer.parseInt(target);
	}
	
	public boolean isEmpty(String target) 
	{
		return target == null || "".equals(target);
	}
	
	public boolean isEmptyString(String target) 
	{
		return "".equals(target);
	}
	
	public boolean isNull(String target) 
	{
		return target == null;
	}
	
	public String safeTrim(String target) 
	{
		return (isEmpty(target) ? "" : target.trim());
	}
	
	public String doEncryptSeed(String target) 
	{
		try {
			return new String(encryptSeed(target), CHAR_SET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String doEncryptSha256(String msg) throws Exception {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(msg.getBytes());
	    StringBuilder builder = new StringBuilder();
	    for (byte b: md.digest()) 
	    {
	    	builder.append(String.format("%02x", b));
	    }
	    return builder.toString();
	}
	
	private byte[] encryptSeed(String target)
	{
		try 
		{
			byte[] tmp = null;
			byte[] pbszUserKey = this.pbszUserKey.getBytes();
			byte[] pbszIV = this.pbszIV.getBytes();
			tmp = KISASeedCbc.SEED_CBC_Encrypt(pbszUserKey, pbszIV, target.getBytes(CHAR_SET), 0, target.getBytes(CHAR_SET).length);
			Encoder encoder = Base64.getEncoder();
			return encoder.encode(tmp);
		}catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public JsonObject doPasing(String serializedStr) 
	{
		JsonElement jsonElement = new JsonParser().parse(serializedStr);
		return jsonElement.getAsJsonObject();
	}
	public String doSerialized(JSONArray cmmnCds)
	{
		if(cmmnCds == null) return null;
		
		StringBuilder tmp = new StringBuilder();
		
		for(int inx=0; inx<cmmnCds.size(); inx++)
		{
			tmp.append(cmmnCds.get(inx)+",");
		}
		return tmp.toString();
	}
	public JSONArray toJsonArray(String strArr)
	{
		if(strArr == null) return null;
		
		String[] tmp = strArr.split(",");
		JSONArray result = new JSONArray();
		
		for(int inx=0; inx<tmp.length; inx++)
		{
			if(this.isEmpty(tmp[inx])) continue;
			result.add(tmp[inx]);
		}
		return result;
	}
}
