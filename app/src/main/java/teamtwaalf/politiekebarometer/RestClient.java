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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import teamtwaalf.politiekebarometer.model.Graph;
import teamtwaalf.politiekebarometer.model.GraphList;
import teamtwaalf.politiekebarometer.model.GraphType;

public class RestClient {
    //  api/user parametiseren
    //Retrofit of OKhttp
    private static final String LINK = "http://10.0.2.2:58182/api/User";
    private Context context;
    private String result;
    private char[] chars = new char[200];
    private StringBuilder builder = new StringBuilder();
    private int charsRead = -1;
    private Random random = new Random();

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
            if (conn.getResponseCode() == 200) {
                Log.d("LOGKEY", "Connectie geslaagd!");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((result = br.readLine()) != null) {
                Log.d("LOGKEY", result);
            }
            // NIET VERGETEN!
            conn.disconnect();
        } catch (IOException e) {
            Log.d("LOGKEY", e.getMessage());
        }
        return null;
    }

    public void InitialiseGraphList() {
        String title = "";
        for (int i = 0; i < 5; i++) {
            //Initialise title
            StringBuilder builder = new StringBuilder();
            builder.append("Grafiek ");
            builder.append(String.valueOf(i));
            title = builder.toString();


            //Initialise labels
            List<String> labels = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                labels.add(String.valueOf((j)));
            }

            //Initialise subjects
            String firstSubject = "Bart De Wever";
            String secondSubject = "Geert Bourgeois";
            String thirdSubject = "Imade Annouri";
            String fourthSubject = "Theo Francken";
            String fifthSubject = "Bart Somers";

            //Initialise data
            List<String> dataFirstSubject = new ArrayList<>();
            List<String> dataSecondSubject = new ArrayList<>();
            List<String> dataThirdSubject = new ArrayList<>();
            List<String> dataFourthSubject = new ArrayList<>();
            List<String> dataFifthSubject = new ArrayList<>();
            for (int j = 0; j < labels.size(); j++) {
                dataFirstSubject.add(String.valueOf(random.nextInt(100)));
                dataSecondSubject.add(String.valueOf(random.nextInt(100)));
                dataThirdSubject.add(String.valueOf(random.nextInt(100)));
                dataFourthSubject.add(String.valueOf(random.nextInt(100)));
                dataFifthSubject.add(String.valueOf(random.nextInt(100)));
            }
            Graph g = new Graph(1,
                    GraphType.line,
                    labels, dataFirstSubject,
                    dataSecondSubject,
                    dataThirdSubject,
                    dataFourthSubject,
                    dataFifthSubject,
                    title,
                    firstSubject,
                    secondSubject,
                    thirdSubject,
                    fourthSubject,
                    fifthSubject
            );
            GraphList.graphList.add(g);
        }

    }

}
