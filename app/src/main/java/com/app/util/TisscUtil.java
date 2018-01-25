package com.app.util;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TisscUtil {
	
	 
	 public static String getGuid()
	 {
		    
		      // 创建 GUID 对象
		      UUID uuid = UUID.randomUUID();
		      // 得到对象产生的ID
		      String guid = uuid.toString();
		      // 转换为大写
		      guid = guid.toLowerCase();
		      // 替换 -
		      // a = a.replaceAll("-", "");
		      return guid;
		    
	 }
	 
	 public static final String GetMd5(String s)
	    {
	        char hexDigits[] = {
	            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	            'a', 'b', 'c', 'd', 'e', 'f'
	        };
	        char str[];
	        byte strTemp[] = s.getBytes();
	        try {
	            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	            mdTemp.update(strTemp);
	            byte md[] = mdTemp.digest();
	            int j = md.length;
	            str = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++)
	            {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }

	            return new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	    }
	 
	 	/**
	 	 * by whj 2016-06-17
	 	 * 通过KEY 获取 VALUE 返回
	 	 * @param content 传入JSON
	 	 * @param key
	 	 * @return
	 	 */
		public static String  findValue(String content ,String key) { 
			//通过KEY 获取 VALUE 返回
//			String content ="test";
//			String key ="ERP_ID";
			content=content.trim();
			
		    String re1="(\""+key+"\")";	// key 
		    String re2="(\\s*)";	// White Space  
		    String re3="(:)";	// Any Single Character 1
		    String re4="(\\s*)";	// White Space 
		    String re5="\"(.*?)\"";	// value 
		    String retValue =""; 
		    Pattern p = Pattern.compile(re1+re2+re3+re4+re5,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(content);
		    if (m.find())
		    {
		     
		        retValue=m.group(5);
		       
		    }  
			return retValue;

		}
	 
	 public static void main(String[] args) throws Exception {
		 
//        String s = "{\"message\":\"success\",\"messageJson\":\"\",\"status\":\"1\"}";  
//		
//		
//		
//		System.out.println(rt.getMessage()); 
//		 String value="";
//		 value = findValue("  \"LEVEL_DISCOUNT_XJ\": 0,     \"B2C_ID\":\"139232323\",     \"IsPromotion\": false","b2c_id");
//		 
		 System.out.println(TisscUtil.GetMd5("312312312"));

		}
}
