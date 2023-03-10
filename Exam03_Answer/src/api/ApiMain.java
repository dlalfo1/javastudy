package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiMain {

	public static void main(String[] args) {
		
		HttpURLConnection con = null;
		URL url = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		try {
			
			StringBuilder urlBuilder = new StringBuilder();
			String serviceKey = "bEQBRPHjt0tZrc7EsL0T8usfsZ1+wT+5jqamBef/ErC/5ZO6N7nYdRmrwR91bh5d3I1AQeY5qdbJOF6Kv0U1CQ==";
			urlBuilder.append("http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuilder.append("&searchYear=2021");
			urlBuilder.append("&siDo=1100");
			urlBuilder.append("&guGun=1125");
			urlBuilder.append("&type=json");
			urlBuilder.append("&numOfRows=10");
			urlBuilder.append("&pageNo=1");
			
			url = new URL(urlBuilder.toString());
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			
			reader.close();
			con.disconnect();
			
			StringBuilder fileBuilder = new StringBuilder();
			List<Accident> accidents = new ArrayList<Accident>();
			JSONObject obj = new JSONObject(sb.toString());
			JSONArray itemList = obj.getJSONObject("items").getJSONArray("item");
			
			
			/*
			 	String occrrncDt;  // λ°μμμΌμ (2019011622)
				String occrrncDayCd;  // λ°μμμΌμ½λ (4)
				int dthDnvCnt;  // μ¬λ§μμ (0)
				int injpsnCnt;  // λΆμμμ (1)
			 */
			for(int i = 0; i < itemList.length(); i++) {
				JSONObject item = itemList.getJSONObject(i);
				String occrrncDt = item.getString("occrrnc_dt"); // λ°μμμΌμ : 2021020618
				String[] week = {"", "μΌ", "μ", "ν", "μ", "λͺ©", "κΈ", "ν "};
				String occrrncDayCd = item.getString("occrrnc_day_cd"); // λ°μμμΌμ½λ : 7
				int dthDnvCnt = item.getInt("dth_dnv_cnt"); // μ¬λ§μμ : 1
				int injpsnCnt = item.getInt("injpsn_cnt"); // λΆμμμ : 1
				fileBuilder.append("λ°μμΌμ ").append(occrrncDt).append(" ").append(week[Integer.parseInt(item.getString("occrrnc_day_cd"))]).append("μμΌ, ");
				fileBuilder.append("μ¬λ§μμ ").append(dthDnvCnt).append("λͺ, ");
				fileBuilder.append("λΆμμμ ").append(injpsnCnt).append("λͺ\n");
				
				// λ°μμΌμ 2021020618 ν μμΌ, μ¬λ§μμ 1λͺ, λΆμμμ 1λͺ 
				// System.out.println(fileBuilder.toString());
				// μ¬κΈ°κΉμ§λ§ ν΄λ μ λ΄μ©μ²λΌ μΆλ ₯ λ¨. fileBuilderλ‘ μ μ΄μ¨μ€λΈμ νΈμ μλλ΄μ© μ€νΈλ§μΌλ‘ λ€ κΈμ΄ μμΌλκΉ
				// λ°μ κ°μ²΄ λ§λ€μ΄μ Listμ λ£λκ±΄ μλ§ νλ² κ·Έλ°κ±° ν΄λ³΄λΌκ³  νμ κ±° μλκΉ μΆμ...
				
				
				Accident accident = new Accident();
				accident.setOccrrncDt(occrrncDt);
				accident.setOccrrncDayCd(occrrncDayCd);
				accident.setDthDnvCnt(dthDnvCnt);
				accident.setInjpsnCnt(injpsnCnt);
				accidents.add(accident);
				
			}
			
		
			
			File file = new File("accident.txt");
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(fileBuilder.toString());
			writer.flush(); // μ€νΈλ¦Όμ λ¨μμλ μμ¬λ¬Όμ νΈμ΄λ΄λκ±΄λ°,, κ΅³μ΄ μ μ¨λ λ¨..
			
			writer.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}