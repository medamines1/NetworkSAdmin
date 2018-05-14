package error_pack;

import java.util.HashMap;

import org.json.JSONObject;

public class error_handl {
	public static String true_err(String error) {
		 HashMap<String, String> myMap = new HashMap<String, String>();
		 myMap.put("resp","true");
		 myMap.put("error","true");
		 myMap.put("error_msg",error);
		 myMap.put("state","error");
		 return new JSONObject(myMap).toString();
	}
	
	public static String false_err(String rs,String action,String resp, String hidden,String exec) {
		
		HashMap<String, String> myMap = new HashMap<String, String>();
		 myMap.put("data",rs);
		 myMap.put("resp",resp);
		 myMap.put("hiden",hidden);
		 myMap.put("error","false");
		 myMap.put("action",action);
		 myMap.put("exec",exec);
		 myMap.put("state","success");
	
		return new JSONObject(myMap).toString();
		
	}
}
