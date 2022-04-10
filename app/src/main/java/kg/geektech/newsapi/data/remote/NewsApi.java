package kg.geektech.newsapi.data.remote;

import kg.geektech.newsapi.data.models.MainResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<MainResponse> getTopNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<MainResponse> getNewsByKeyWord(
            @Query("q") String keyWord,
            @Query("apiKey") String apiKey
    );

}
