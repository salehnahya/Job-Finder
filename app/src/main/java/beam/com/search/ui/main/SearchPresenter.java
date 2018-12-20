
package beam.com.search.ui.main;


import beam.com.search.data.github.model.GithubResponse;
import beam.com.search.ui.Presenter;

public interface SearchPresenter extends Presenter {

    void onSearch(String jobName,String location);


}
