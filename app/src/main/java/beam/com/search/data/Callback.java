
package beam.com.search.data;

public interface Callback<T> {

    void onSuccess(T result);

    void onError(Throwable error);
}
