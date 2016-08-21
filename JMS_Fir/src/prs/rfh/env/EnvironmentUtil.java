package prs.rfh.env;

import java.util.Iterator;
import java.util.Properties;

public class EnvironmentUtil {

		
	public static void main(String[] args) {
		
		System.setProperty("12", "123123");
		System.out.println(System.getProperty("12"));
		
//		EnvironmentUtil.setENVByKey("xmlPath", "../config/xml/");
		Iterator ir2 = System.getenv().keySet().iterator();
		while(ir2.hasNext()){
			String key = ir2.next().toString();
			System.out.print(key);
			System.out.println(":\t\t\t"+System.getenv(key));
			System.out.printf("%s",System.getenv(key));
		}
		
		
		System.out.println("****************************************************************");
		Properties pro = System.getProperties();
		
		Iterator ir = pro.keySet().iterator();
		
		while(ir.hasNext()){
			String key = ir.next().toString();
			System.out.print(key);
			System.out.println(":\t"+System.getProperty(key));
		}
//		System.out.println(EnvironmentUtil.getENVByKey("xmlPath"));
	}
	
	public static Object getENVByKey(String key){
//		System.getenv()
		return System.getProperty("wtp.deploy");
	}
	public static void setENVByKey(String key ,String value){
		System.setProperty(key, value);
	}
}
