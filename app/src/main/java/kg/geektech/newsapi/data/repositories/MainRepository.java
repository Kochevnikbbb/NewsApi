package kg.geektech.newsapi.data.repositories;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.newsapi.common.RequestState;
import kg.geektech.newsapi.data.models.MainResponse;
import kg.geektech.newsapi.data.remote.NewsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private final String API_KEY = "4bf390d73ba740d798284d28d31262a0";
    private NewsApi api;
    private MutableLiveData<RequestState<MainResponse>> liveData = new MutableLiveData<>();

    @Inject
    public MainRepository(NewsApi api) {
        this.api = api;
    }

    public MutableLiveData<RequestState<MainResponse>> getTopNews() {
        liveData.setValue(RequestState.loading());
        api.getTopNews("ru", API_KEY).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(RequestState.success(response.body()));
                } else {
                    liveData.setValue(RequestState.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(RequestState.error(t.getLocalizedMessage(), null));

            }
        });
        return liveData;
    }

    public MutableLiveData<RequestState<MainResponse>> getNewsByKeyWord(String keyWord) {
        liveData.setValue(RequestState.loading());
        api.getNewsByKeyWord(keyWord, API_KEY).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(RequestState.success(response.body()));
                } else {
                    liveData.setValue(RequestState.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(RequestState.error(t.getLocalizedMessage(), null));

            }
        });
        return liveData;
    }
}
