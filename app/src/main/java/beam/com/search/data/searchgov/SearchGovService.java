
package beam.com.search.data.searchgov;

import java.util.List;

import beam.com.search.data.searchgov.model.SearchGovResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchGovService {

    @GET("search.json")
    Observable<List<SearchGovResponse>> searchResult(@Query("query") String jobName, @Query("location") String location);


}
