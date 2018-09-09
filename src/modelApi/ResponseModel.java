package modelApi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;





public class ResponseModel {
	
	public static String response_success() {
		 HashMap<String, String> myMap = new HashMap<String, String>();
		 myMap.put("resp","true");
		 myMap.put("error","false");
		 myMap.put("state","success");
		 return new JSONObject(myMap).toString();
	}
	
	public static String response_success(String data) {
		 HashMap<String, String> myMap = new HashMap<String, String>();
		 myMap.put("resp","true");
		 myMap.put("error","false");
		 myMap.put("data",data);
		 myMap.put("state","success");
		 return new JSONObject(myMap).toString();
	}
	public static String response_failed(String error) {
		 HashMap<String, String> myMap = new HashMap<String, String>();
		 myMap.put("resp","true");
		 myMap.put("error","true");
		 myMap.put("error_msg",error);
		 myMap.put("state","error");
		 return new JSONObject(myMap).toString();
		
		
	}
	public static <T extends tableH> Map<String,String> fromList(List<T> li) {
	 	Map<String,String> val = new LinkedHashMap<String,String>();
	 	int i=0;
	 	if (!li.isEmpty())
	 		for ( T e : li) {
	 			val.put(i+"",e.toList());
	 			i++;
			}

		return val;
	}
	

}
