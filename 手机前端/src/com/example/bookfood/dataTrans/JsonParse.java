package com.example.bookfood.dataTrans;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**   
 * 解析Json数据
 * 
 * @Project BookFood
 * @Package com.example.bookfood.dataTrans
 * @Class JsonParse
 * @Date 2012-11-9 下午6:33:49
 * @author upaman
 * @version 
 * @since 
 */ 

public class JsonParse {
	 public static List<Dish> getListPerson(String urlPath) throws Exception {  
	        List<Dish> mlists = new ArrayList<Dish>();  
	        byte[] data = readParse(urlPath);  
	        JSONArray array = new JSONArray(new String(data));  
	        for (int i = 0; i < array.length(); i++) {  
	            JSONObject item = array.getJSONObject(i);  
	            String name = item.getString("DishName");
	            String type = item.getString("DishSeries");
	            String pic = item.getString("DishImg");  
	            double price = item.getDouble("DishPrice");  
	            mlists.add(new Dish(name, type, price, pic));  
	        }  
	        return mlists;  
}

	private static byte[] readParse(String urlPath) throws Exception {
	     
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[1024];  
        int len = 0;  
        URL url = new URL(urlPath);  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
        InputStream inStream = conn.getInputStream();  
  
        while ((len = inStream.read(data)) != -1) {  
            outStream.write(data, 0, len);  
  
        }  
        inStream.close();  
        return outStream.toByteArray();  
	}
}