
package beam.com.search.data.searchgov;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import beam.com.search.BuildConfig;
import beam.com.search.data.searchgov.model.SearchGovResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SearchGovDataSourceImpl implements SearchGovDataSource {


    private static final long CONNECT_TIMEOUT_SECONDS = 40;

    private static final String BASE_URL = " https://jobs.search.gov/jobs/";

    private static volatile SearchGovDataSourceImpl instance;

    private SearchGovService searchGovService;

    private SearchGovDataSourceImpl(Context context) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG)
            okHttpClient.addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        searchGovService = retrofit.create(SearchGovService.class);
    }

    public static synchronized SearchGovDataSourceImpl getInstance(Context context) {
        if (instance == null) {
            instance = new SearchGovDataSourceImpl(context.getApplicationContext());
        }

        return instance;
    }


    @Override
    public Observable<List<SearchGovResponse>> searchByJobNameOrLocation(String jobName, String location) {
        if (location != null)
            jobName += "  jobs in " + location;
        return searchGovService.searchResult(jobName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
