package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiMain {

	public static void main(String[] args) {

		
		String serviceKey = "+h82WwRALt+3L4awyNYZmmtJWZkKscUT/Fm7NDCD2dinRfTWm+jbUFT7xCUVAENXwgn1So0f2X0WpNuE69Gymw==";
		String apiURL = "http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath";
		URL url = null;
		HttpURLConnection con1 = null;
		BufferedReader reader = null;
		
		
		try {
		
			//D
				StringBuilder sbURL = new StringBuilder();
				sbURL.append(apiURL); 
				sbURL.append("?serviceKey="+ URLEncoder.encode(serviceKey, "UTF-8")); 
				sbURL.append("&searchYear=2021");
				sbURL.append("&siDo=1100");
				sbURL.append("&guGun=1125");
				sbURL.append("&type=json");
				sbURL.append("&numOfRows=10");
				sbURL.append("&pageNo=1");
				
				
				url = new URL(sbURL.toString());
				con1 = (HttpURLConnection)url.openConnection();
				
				con1.setRequestMethod("GET");
				con1.setRequestProperty("content-type", "application/json; charset=UTF-8"); 
				
				int responseCode = con1.getResponseCode();
				
				if(responseCode == 200) {
					reader = new BufferedReader(new InputStreamReader(con1.getInputStream()));
					
				} else {
					reader = new BufferedReader(new InputStreamReader(con1.getErrorStream()));
				}
				
				String line = null;
				StringBuilder sb = new StringBuilder();
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				reader.close();
				con1.disconnect();
				
				JSONObject obj = new JSONObject(sb.toString()); 
				JSONObject items = obj.getJSONObject("items");
				JSONArray itemarr = items.getJSONArray("item"); 
				for(int i = 0; i < itemarr.length(); i++) { 		
					JSONObject item = itemarr.getJSONObject(i); 	
					
					
					String occrrncdt = item.getString("occrrnc_dt"); // ???????????????
					String occrrncDayCd = item.getString("occrrnc_day_cd");  // ??????????????????
					int dthdnvcnt = item.getInt("dth_dnv_cnt");		 // ????????????
					int injpsncnt = item.getInt("injpsn_cnt");		 // ????????????
					
					
					System.out.println("?????????????????? : " + occrrncdt + " ?????????????????? : " + occrrncDayCd + " ???????????? : " + dthdnvcnt + "??? ???????????? : " + injpsncnt + "???" );
					
					Accident accidents = new Accident(occrrncdt, occrrncDayCd, dthdnvcnt, injpsncnt);
					List<Map<String, Object>> accident = new ArrayList<Map<String,Object>>();
				}
				
				
				File file = new File("C:" + File.separator + "storage", "accident.txt");
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(sb.toString());
				
				writer.close();
				
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
