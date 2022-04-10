package kg.geektech.newsapi.ui.news_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.newsapi.common.RequestState;
import kg.geektech.newsapi.data.models.MainResponse;
import kg.geektech.newsapi.data.repositories.MainRepository;

@HiltViewModel
public class NewsListViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<RequestState<MainResponse>> liveData;

    @Inject
    public NewsListViewModel(MainRepository repository) {
        this.repository = repository;
    }
    public void getTopNews() { liveData = repository.getTopNews();}
    public void getNewsByKeyWord(String s){liveData = repository.getNewsByKeyWord(s);}
}
