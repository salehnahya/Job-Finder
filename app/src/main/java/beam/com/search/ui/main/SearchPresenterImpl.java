
package beam.com.search.ui.main;

import java.util.List;

import beam.com.search.R;
import beam.com.search.data.Callback;
import beam.com.search.data.IResult;
import beam.com.search.domain.SearchUseCase;
import beam.com.search.logging.LoggingHelper;

public class SearchPresenterImpl implements SearchPresenter {

    private static final String TAG = "SearchPresenterImpl";

    private SearchView view;

    private SearchUseCase searchUseCase;

    private LoggingHelper loggingHelper;

    public SearchPresenterImpl(SearchView view, SearchUseCase searchUseCase, LoggingHelper loggingHelper) {
        this.view = view;
        this.searchUseCase = searchUseCase;
        this.loggingHelper = loggingHelper;
    }

    @Override
    public void onViewReady() {
        view.showLoadingIndicator();
        view.hideResultItems();
        view.hideNoResultsContainer();

        searchUseCase.execute(null, null, new Callback<List<IResult>>() {
            @Override
            public void onSuccess(List<IResult> result) {
                view.hideLoadingIndicator();
                if (!result.isEmpty()) {
                    view.showResultItems(result);
                } else {
                    view.showNoResultsContainer();
                }
            }

            @Override
            public void onError(Throwable error) {
                loggingHelper.error(TAG, "An error occurred while getting the search result ", error);
                view.hideLoadingIndicator();
                view.showToast(R.string.an_error_occurred);
            }
        });


    }


    @Override
    public void onSearch(String jobName, String location) {

        search(jobName, location);
    }


    private void search(String jobName, String location) {
      /*  if (jobName != null & jobName.isEmpty()) {
            view.showToast(R.string.enter_search_query);
        } else*/ {
            view.showLoadingIndicator();
            view.hideResultItems();
            view.hideNoResultsContainer();

            searchUseCase.execute(jobName, location, new Callback<List<IResult>>() {
                @Override
                public void onSuccess(List<IResult> result) {
                    view.hideLoadingIndicator();
                    if (!result.isEmpty()) {
                        view.showResultItems(result);
                    } else {
                        view.showNoResultsContainer();
                    }
                }

                @Override
                public void onError(Throwable error) {
                    view.hideNoResultsContainer();
                    loggingHelper.error(TAG, "An error occurred while getting the search result ", error);
                    view.hideLoadingIndicator();
                    view.showToast(R.string.an_error_occurred);
                }
            });
        }
    }
}
