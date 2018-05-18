package teamtwaalf.politiekebarometer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import teamtwaalf.politiekebarometer.model.Graph;

/**
 * Created by biema on 16/05/2018.
 */

public interface GraphApi {
    @GET("/api/GraphApi{user}")
    Call<List<Graph>> grafiekenPerUser(@Path("user") String user);
}
