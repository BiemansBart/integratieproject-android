package teamtwaalf.politiekebarometer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by biema on 11/05/2018.
 */

public class RestClient {
    private static final String URL = "localhost:58182/api/User";
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
            ConnectivityManager connMgr =
                    (ConnectivityManager) context.getSystemService(
                            Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                java.net.URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                InputStream resultStream = conn.getInputStream();
                Reader resultReader = new InputStreamReader(resultStream);
                do {
                    charsRead = resultReader.read(chars,0,chars.length);
                    if(charsRead > 0){
                        builder.append(chars,0,charsRead);
                    }

                }while(charsRead > 0);
                result = builder.toString();
                return result;

            }
        } catch (IOException e) {
            Log.d("LOGKEY",e.getMessage());
        }
        return null;
    }
}
