package beam.com.search.data.github;

import java.util.List;

import beam.com.search.DataSource;
import beam.com.search.data.github.model.GithubResponse;
import io.reactivex.Observable;

public interface GithubDataSource extends DataSource<List<GithubResponse>> {

    @Override
    Observable<List<GithubResponse>> searchByJobNameOrLocation(String jobName, String location);
}
