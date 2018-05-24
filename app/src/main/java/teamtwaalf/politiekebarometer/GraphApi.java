package teamtwaalf.politiekebarometer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import teamtwaalf.politiekebarometer.model.AlertMessage;
import teamtwaalf.politiekebarometer.model.Graph;

/**
 * Created by biema on 16/05/2018.
 */

public interface GraphApi {

    String BASE_URL = "http://10.134.216.25:8012/";
    @GET("/api/GraphApi/getGraph")
    Call<List<Graph>> grafiekenPerUser(@Query(value = "id", encoded = true) String id);
    @GET("/api/UserApi/Login")
    Call<String> getUserId(@Query("email") String email, @Query("password") String password);
    @GET("/api/AlertApi/GetAlerts")
    Call<List<AlertMessage>> getUserAlerts(@Query(value = "id", encoded = true) String id);
}
