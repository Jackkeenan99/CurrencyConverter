import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.reflect.Field;
import javax.swing.*; 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class main {
	
	private static HashMap <String,String> currency = new HashMap<String,String>();
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public static void main(String[] args) {
	
		JSONParser parser = new JSONParser();
		try {
		
			JSONObject obj = (JSONObject) parser.parse(new FileReader("currency.json"));
			convertObjectToMap(obj);
			currencyBox currencyBox = new currencyBox();
			Object [] objs =  obj.values().toArray();
			String [] currencyKeys =  Arrays.asList(objs).toArray(new String[objs.length]);
			currencyBox.showBox(currencyKeys);
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		

	}
	
	public static void convertObjectToMap(JSONObject obj) {
		Set <String> set = obj.keySet();
		for(String s: set) {	
			currency.put(obj.get(s).toString(), s);	
		}	
		
	}
	
	public static void getRates(String from, String to) {
		String fromCurrencyCode = currency.get(from);
		String toCurrencyCode = currency.get(to);
		Get get = new Get();
		get.getCall(fromCurrencyCode, toCurrencyCode);
		
	}
	public static void convert(String rate, String from, String to) {
		double xRate = Double.parseDouble(rate);
		double input  = Double.parseDouble(JOptionPane.showInputDialog("Convert " + from + " to " + to));
		double converted = Double.parseDouble(df2.format(input * xRate));
		JOptionPane.showMessageDialog(null, "Exchange rate is 1:" + xRate + "\n\n" + input + " " + from + " = " + converted + " " + to);	
	}

}
