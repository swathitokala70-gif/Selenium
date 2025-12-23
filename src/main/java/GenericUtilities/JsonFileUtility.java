package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.jdbc.Driver;

public class JsonFileUtility {
	public String fetchDatafromJsonFile(String key) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parse=new JSONParser();
		Object obj = parse.parse(new FileReader("./src/test/resources/vtigercrmCMD.json"));
		JSONObject js=(JSONObject)obj;
		String data = js.get(key).toString();
		return data;
	}

	}


