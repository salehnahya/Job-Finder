package beam.com.search;

import io.reactivex.Observable;

public interface DataSource<T> {

    Observable<T> searchByJobNameOrLocation(String jobName, String location);

}
