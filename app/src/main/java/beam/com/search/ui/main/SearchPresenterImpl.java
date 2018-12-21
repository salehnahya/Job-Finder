
package beam.com.search.ui.main;

import java.util.List;

import beam.com.search.R;
import beam.com.search.data.IResult;
import beam.com.search.data.onErrorCallback;
import beam.com.search.data.onSuccessCallback;
import beam.com.search.domain.UseCase;
import beam.com.search.logging.LoggingHelper;

public class SearchPresenterImpl implements SearchPresenter {

    private static final String TAG = "SearchPresenterImpl";

    private SearchView view;

    private UseCase<String, String, List<IResult>> searchUseCase;

    private LoggingHelper loggingHelper;

    public SearchPresenterImpl(SearchView view, UseCase<String, String, List<IResult>> searchUseCase, LoggingHelper loggingHelper) {
        this.view = view;
        this.searchUseCase = searchUseCase;
        this.loggingHelper = loggingHelper;
    }

    @Override
    public void onViewReady() {
        search(null, null);
    }


    @Override
    public void onSearch(String jobName, String location) {

        search(jobName, location);
    }


    private void search(String jobName, String location) {

        {
            view.showLoadingIndicator();
            view.hideResultItems();
            view.hideNoResultsContainer();

            searchUseCase.execute(jobName, location, new onSuccessCallback<List<IResult>>() {
                @Override
                public void onSuccess(List<IResult> result) {
                    view.hideLoadingIndicator();

                    if (result != null && !result.isEmpty()) {
                        view.showResultItems(result);
                    } else {
                        view.showNoResultsContainer();
                    }
                }


            }, new onErrorCallback() {
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

