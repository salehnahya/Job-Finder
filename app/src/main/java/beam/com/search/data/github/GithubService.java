
package beam.com.search.data.github;

import java.util.List;

import beam.com.search.data.github.model.GithubResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {

    @GET("positions.json")
    Observable<List<GithubResponse>> searchResult(@Query("description") String jobName, @Query("location") String location);


}
