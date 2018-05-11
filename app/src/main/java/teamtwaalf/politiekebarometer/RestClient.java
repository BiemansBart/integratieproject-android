package teamtwaalf.politiekebarometer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by biema on 11/05/2018.
 */

public class RestClient {
    private static final String LINK = "http://10.0.2.2:58182/api/User";
    private Context context;
    private String result;
    private char[] chars = new char[200];
    private StringBuilder builder = new StringBuilder();
    private int charsRead = -1;

    public RestClient(Context c) {
        this.context = c;
    }

    public String getResult() throws IOException {
        try {
            URL url = new URL(LINK);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed HTTP Error Code" + conn.getResponseCode());
            }
            if(conn.getResponseCode() == 200){
                Log.d("LOGKEY","Connectie geslaagd!");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((result = br.readLine()) != null) {
                Log.d("LOGKEY",result);
            }
            // NIET VERGETEN!
            conn.disconnect();
        }catch (IOException e){
            Log.d("LOGKEY",e.getMessage());
        }
        return null;
    }
}
