package com.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 2018/1/25.
 */
public class ConnectionTest {
    public  String netpostt (String url,String list,String ...header) throws Exception{

        URL realUrl = null;
        PrintWriter out = null;
        StringBuffer buffer = new StringBuffer();

        try {
            realUrl = new URL(url);// +

            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            conn.setDoInput(true);// 鍒涘缓杈撳叆娴侊紝蹇呴』鏈�
            conn.setDoOutput(true);// 鍒涘缓杈撳嚭娴侊紝蹇呴』鏈�
            conn.setUseCaches(false);// 涓嶇紦瀛�
            conn.setConnectTimeout(60000);// 杩炴帴瓒呮椂
            conn.setReadTimeout(60000);// 鍝嶅簲瓒呮椂
            conn.setRequestMethod("POST");
            conn.addRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Length",
                    String.valueOf(list.getBytes("UTF-8").length));// 鏂囦欢澶у皬
            conn.addRequestProperty("Content-Type",
                    "application/json");// json;x-www-form-urlencoded
            conn.addRequestProperty("Connection", "Keep-Alive");// 杩炴帴鏂瑰紡锛屾澶勪负闀胯繛鎺�

            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));
            out.print(list);
            out.flush();
            if (conn.getResponseCode() != 200) {
                throw new IOException(conn.getResponseMessage());
            }

            BufferedReader brd = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),"UTF-8"));

            System.out.println(brd.toString());
            String line = null;

            while ((line = brd.readLine()) != null) {
                buffer.append(line);
            }
            //conn.disconnect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            buffer.append(e.getMessage());
        }

        return buffer.toString();

    }

    public  String sendGet(String url, String string2) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


}
