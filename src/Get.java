import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.*;
import javax.swing.*; 

public class Get {

	String baseUrl = "https://api.exchangerate.host/";
	private String getRate(String Url) {
		try {

			URL url = new URL(Url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			//Getting the response code
			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				//Write all the JSON data into a string using a scanner
				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();
				JSONParser parser = new JSONParser(); 
				JSONObject jOb = (JSONObject) parser.parse(inline);
				JSONObject r = (JSONObject)jOb.get("info");
				return(r.get("rate").toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";


	}
	public void getCall(String from, String to) {
		String Url = baseUrl + "convert?from=" + from + "&to=" + to;
		String rate = getRate(Url);
		if(rate != "") main.convert(rate, from, to);
		else JOptionPane.showMessageDialog(null, "Error - Something went wrong :(");
		
	}
}

