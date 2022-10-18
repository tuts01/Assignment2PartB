package tutungis.assignment2partb;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

public class SearchTask implements Callable<String> {
    private static final String BASE_URL = "https://pixabay.com/api/";
    private static final String API_KEY = "30678871-4ee6dfd2b269d16192a297620";

    private Activity activity;
    private String searchKey;

    public SearchTask(Activity activity)
    {
        this.activity = activity;
        this.searchKey = null;
    }

    public SearchTask(Activity activity, String searchKey)
    {
        this.activity = activity;
        this.searchKey = searchKey;
    }

    public String call()
    {
        String response = null;
        HttpURLConnection connection = RemoteUtils.openConnection(getSearchString(), activity);

        if(connection != null) if(RemoteUtils.checkConnection(connection, activity))
        {
            response = RemoteUtils.getResponseString(connection, activity);
            connection.disconnect();

            try //TODO: Figure out if this is necessary and if not, remove it.
            {
                Thread.sleep(3000);
            } catch(Exception e) { }
        }

        return response; //Stub!
    }

    public void setSearchKey(String key)
    {
        searchKey = key;
    }

    private String getSearchString()
    {
        return Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter("key", API_KEY)
                .appendQueryParameter("q", this.searchKey)
                .build().toString();
    }
}
