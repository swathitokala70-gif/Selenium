package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility 
{
    public String FetchProertyData(String Key) throws IOException
    {
    	FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
    	Properties p=new Properties();
    	p.load(fis);
    	String value = p.getProperty(Key);
    	return value;
    }
    
    public void WriteBackPropertyData() throws IOException
    {
    	FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
    	Properties p=new Properties();
    	p.load(fis);
    	FileOutputStream fos=new FileOutputStream("./src/test/resources/CommonData.properties");
    	p.store(fos, "data updated");

    }
}
