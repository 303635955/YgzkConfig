package com.yunguo.TenantUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class HttpPostUtil {
	
	/**
	 * Http Post 请求
	 * @param urlStr  url地址
	 * @param paramStr  参数
	 * @return
	 */
	static public String PostStringToUrl(String urlStr, String paramStr){
		return PostStringToUrl(urlStr,paramStr,"param");
	}
	
	static public String PostStringToUrl(String urlStr, String paramStr,String key) {
		
		String res = "";
		try {  
			String params = key + "=" + paramStr;
			byte[] data = params.getBytes();
			URLEncoder.encode(params,"GBK") ;
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", data.length + "");
			
			conn.setDoOutput(true);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(data);
			out.flush();
			out.close();

			StringBuffer strBuf = new StringBuffer();
			strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			String ress = strBuf.toString();
			res = URLDecoder.decode(ress, "UTF-8");
			reader.close();
			reader = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return res;
	}
}
