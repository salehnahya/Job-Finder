
package beam.com.search.domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import beam.com.search.data.IResult;
import beam.com.search.data.converters.ResponseConverters;
import beam.com.search.data.github.GithubDataSource;
import beam.com.search.data.github.model.GithubResponse;
import beam.com.search.data.onErrorCallback;
import beam.com.search.data.onSuccessCallback;
import beam.com.search.data.searchgov.SearchGovDataSource;
import beam.com.search.data.searchgov.model.SearchGovResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchUseCase implements UseCase<String, String, List<IResult>> {

    private static final String TAG = "SearchUseCase";
    private final GithubDataSource githubDataSource;
    private final SearchGovDataSource searchGovDataSource;


    public SearchUseCase(GithubDataSource githubDataSource, SearchGovDataSource searchGovDataSource) {
        this.githubDataSource = githubDataSource;
        this.searchGovDataSource = searchGovDataSource;
    }


    private void convertGitHubResponse(List<GithubResponse> githubResponses, List<IResult> items) {
        Observable.fromArray(githubResponses).map(githubResponses1 -> {
            List<IResult> results = new ArrayList<>();
            for (GithubResponse item : githubResponses1) {
                IResult result = ResponseConverters.convertGitHubResponseToSearchResult(item);
                results.add(result);
                items.add(result);
            }
            return results;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<List<IResult>>() {
            @Override
            public void onNext(List<IResult> iResults) {
                Log.e(TAG, "onNext: " + iResults.size());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void convertSearchGovResponse(List<SearchGovResponse> searchGovResponses, List<IResult> items) {
        Observable.fromArray(searchGovResponses).map(listOfSearchGov -> {
            List<IResult> results = new ArrayList<>();
            for (SearchGovResponse item : listOfSearchGov) {
                IResult result = ResponseConverters.convertSearchGovResponseToSearchResult(item);
                items.add(result);
                results.add(result);

            }
            return results;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<List<IResult>>() {
            @Override
            public void onNext(List<IResult> iResults) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void execute(String jobName, String location, onSuccessCallback<List<IResult>> callback, onErrorCallback onErrorCallback) {
        Observable<List<GithubResponse>> github = githubDataSource.searchByJobNameOrLocation(jobName, location);
        Observable<List<SearchGovResponse>> searchGov = searchGovDataSource.searchByJobNameOrLocation(jobName, location);


        Observable<List<IResult>> zip = Observable.zip(github, searchGov, (githubResponses, searchGovResponses) -> {
            List<IResult> items = new ArrayList<>();

            convertGitHubResponse(githubResponses, items);
            convertSearchGovResponse(searchGovResponses, items);


            return items;
        });

        zip.subscribe(new DisposableObserver<List<IResult>>() {
            @Override
            public void onNext(List<IResult> o) {
                callback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                onErrorCallback.onError(e);

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
