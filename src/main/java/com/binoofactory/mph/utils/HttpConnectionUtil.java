package com.binoofactory.mph.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minidev.json.JSONObject;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Component
public class HttpConnectionUtil {

	public String sendHttpJson(Map<String,String> headerMap, String targetUrl, Map<String,Object> bodyMap, RequestMethod methodType) 
	{
		URLConnection urlConnection;
        StringBuilder stb = new StringBuilder();
        boolean isSSL = false;
        try {
        	if(RequestMethod.GET.equals(methodType))
            {
            	StringBuilder stbParamData = new StringBuilder();
            	if(bodyMap != null)
            	{
                	for(String key : bodyMap.keySet())
                	{
                		stbParamData.append(key);
                		stbParamData.append("=");
                		if(bodyMap.get(key) instanceof String)
                			stbParamData.append(URLEncoder.encode((String)bodyMap.get(key), "UTF-8"));
                		else
                			stbParamData.append(bodyMap.get(key));
                		stbParamData.append("&");
                	}
                	String param = stbParamData.toString();
                	targetUrl += "?" + param.substring(0, param.length()-1);
            	}
                System.out.println("request : "+targetUrl);
            }
            URL url = new URL(targetUrl);
            if( "https".contains(targetUrl) )
            	isSSL = true;

            urlConnection = (isSSL ? (HttpsURLConnection) url.openConnection(): (HttpURLConnection) url.openConnection());

            for(String key : headerMap.keySet()) 
            	urlConnection.setRequestProperty(key, headerMap.get(key)); 
            
            OutputStream outputStream;
            if(RequestMethod.POST.equals(methodType))
            {
                if( isSSL )
                    ((HttpsURLConnection) urlConnection).setRequestMethod(methodType.name());
                else
                    ((HttpURLConnection) urlConnection).setRequestMethod(methodType.name());
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                outputStream = (isSSL ? (HttpsURLConnection) urlConnection : (HttpURLConnection) urlConnection).getOutputStream();
                outputStream.write( JSONObject.toJSONString(bodyMap).getBytes(StandardCharsets.UTF_8) );
                outputStream.flush();
                outputStream.close();
            }else if(RequestMethod.GET.equals(methodType))
            {
            	StringBuilder stbParamData = new StringBuilder();
            	if(bodyMap != null)
            	{
                	for(String key : bodyMap.keySet())
                	{
                		stbParamData.append(key);
                		stbParamData.append("=");
                		stbParamData.append(URLEncoder.encode((String)bodyMap.get(key), "UTF-8"));
                		stbParamData.append("&");
                	}
                	targetUrl += "?" + stbParamData.toString();
            	}
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            String line = "";
            while( (line = reader.readLine()) != null )
                stb.append(line);
            
            reader.close();
            if( isSSL )
                ((HttpsURLConnection)urlConnection).disconnect();
            else
                ((HttpURLConnection)urlConnection).disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stb.toString();
	}
	public JsonObject sendHttpPostThenJson(String url, String token, Map bodyMap)
	{
		Map<String,String> headerMap = new HashMap<String,String>();
//		headerMap.put("Accept-Charset", "UTF-8");
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Authorization", "Bearer " + token);
		JsonElement jsonElement = new JsonParser().parse(sendHttpJson(headerMap, url, bodyMap, RequestMethod.POST));
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		return jsonObject;
	}
	public JsonObject sendAuthKeyHttpPostThenJson(String url, String key, Map bodyMap)
	{
		Map<String,String> headerMap = new HashMap<String,String>();
//		headerMap.put("Accept-Charset", "UTF-8");
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Authorization", "key=" + key);
		JsonElement jsonElement = new JsonParser().parse(sendHttpJson(headerMap, url, bodyMap, RequestMethod.POST));
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		return jsonObject;
	}
	public JsonObject sendHttpGetThenJson(String url, String token, Map bodyMap)
	{
		Map<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Accept-Charset", "UTF-8");
		headerMap.put("Context_Type", "application/json");
//		headerMap.put("Authorization", "Bearer " + token);
		JsonElement jsonElement = new JsonParser().parse(sendHttpJson(headerMap, url, bodyMap, RequestMethod.GET));
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		return jsonObject;
	}
}
