package kg.geektech.newsapi.ui.news_list;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.newsapi.base.BaseFragment;
import kg.geektech.newsapi.databinding.FragmentNewsListBinding;
import kg.geektech.newsapi.ui.NewsAdapter;

@AndroidEntryPoint
public class NewsListFragment extends BaseFragment<FragmentNewsListBinding> {

    private NewsAdapter adapter;
    private NewsListViewModel viewModel;


    @Override
    protected FragmentNewsListBinding bind() {
        return FragmentNewsListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initValues() {

        adapter = new NewsAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(NewsListViewModel.class);
    }

    @Override
    protected void setupViews() {
        binding.recycler.setAdapter(adapter);

    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected void setupObservers() {
        viewModel.liveData.observe(getViewLifecycleOwner(), mainResponseResource -> {
            switch (mainResponseResource.status) {
                case SUCCESS: {
                    adapter.setNewsList(mainResponseResource.data.getArticles());
                    break;
                }
                case ERROR: {
                    Log.e("faa","esfs: ERROR");

                    break;
                }
                case LOADING: {
                    Log.e("faa","esfs: LOADING");

                    break;
                }
            }
        });
    }

    @Override
    protected void callRequests() {
        viewModel.getTopNews();
    }
}