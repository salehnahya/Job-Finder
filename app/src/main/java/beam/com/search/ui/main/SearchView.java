
package beam.com.search.ui.main;

import android.support.annotation.StringRes;

import java.util.List;

import beam.com.search.data.IResult;
import beam.com.search.ui.View;

public interface SearchView extends View {

    void setQuery(String jobName, String location);

    void showResultItems(List<IResult> resultItems);

    void hideResultItems();

    void showNoResultsContainer();

    void hideNoResultsContainer();


    void showToast(@StringRes int resId);
}
