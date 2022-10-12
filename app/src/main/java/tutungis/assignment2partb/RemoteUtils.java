package tutungis.assignment2partb;

import android.app.Activity;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Class containing methods for connections to remote REST services
 *
 * @class           RemoteUtils
 * @author          Tristan S. Tutungis
 * @date_created    9/10/2022
 * @last_modified   10/10/2022 10:40
 */
public class RemoteUtils {
    public static HttpURLConnection openConnection(String urlStr,
                                                   Activity uiActivity)
    {
        HttpURLConnection connection = null;

        try
        {
            connection = (HttpURLConnection) new URL(urlStr).openConnection();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        if(connection == null)
        {
            uiActivity.runOnUiThread(() ->
                    Toast.makeText(uiActivity,"Check Internet and Restart Application",
                            Toast.LENGTH_LONG).show());
        }

        return connection;
    }
    
    public static boolean checkConnection(HttpURLConnection connection,
                                          Activity uiActivity)
    {
        try
        {
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) return true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            uiActivity.runOnUiThread(() -> Toast.makeText(uiActivity, "Unable to connect to server",
                    Toast.LENGTH_LONG).show());
        }
        
        return false;
    }
    
    public static String getJSONString(HttpURLConnection connection,
                                       Activity uiActivity)
    {
        try
        {
            return IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
        }
        catch(IOException e)
        {
            uiActivity.runOnUiThread(() ->
                    Toast.makeText(uiActivity,"Error retrieving data from server",
                            Toast.LENGTH_LONG).show());
            
            return "";
        }
    }
}
