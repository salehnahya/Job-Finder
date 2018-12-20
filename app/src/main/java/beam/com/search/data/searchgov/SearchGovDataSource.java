package beam.com.search.data.searchgov;

import java.util.List;

import beam.com.search.DataSource;
import beam.com.search.data.searchgov.model.SearchGovResponse;
import io.reactivex.Observable;

public interface SearchGovDataSource extends DataSource<List<SearchGovResponse>> {

    @Override
    Observable<List<SearchGovResponse>> searchByJobNameOrLocation(String jobName, String location);
}
