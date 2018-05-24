package teamtwaalf.politiekebarometer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import teamtwaalf.politiekebarometer.model.Graph;

/**
 * Created by biema on 16/05/2018.
 */

public interface GraphApi {

    //String BASE_URL = "https://my-json-server.typicode.com/";
    String BASE_URL = "http://10.134.216.25:8012/";
    @GET("biemansbart/testdata/Graphs")
    Call<List<Graph>> TestDataGrafieken();
    @GET("/api/GraphApi/getGraph")
    Call<List<Graph>> grafiekenPerUser(@Query(value = "id", encoded = true) String id);
    @GET("/api/UserApi/Login")
    Call<String> getUserId(@Query("email") String email, @Query("password") String password);
}
