package utilities;

import java.sql.Date;

public class Utils {


	public static String generateEmailWithTimeStamp() {
		
		Date date = new Date(10);
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "kayc1"+timestamp+"@gmail.com";
	}
}
