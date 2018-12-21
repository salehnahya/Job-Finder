
package beam.com.search.domain;


import beam.com.search.data.onErrorCallback;
import beam.com.search.data.onSuccessCallback;

public interface UseCase<Params, Params2, Success> {

    void execute(Params params, Params2 params2, onSuccessCallback<Success> callback, onErrorCallback onErrorCallback);
}
