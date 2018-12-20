package beam.com.search.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.List;

import beam.com.search.R;
import beam.com.search.data.IResult;
import beam.com.search.data.github.GithubDataSourceImpl;
import beam.com.search.data.searchgov.SearchGovDataSourceImpl;
import beam.com.search.domain.SearchUseCase;
import beam.com.search.logging.LoggingHelperImpl;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_SHORT;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private static final String TAG = SearchActivity.class.getName();

    private Toolbar toolbar;

    private android.support.v7.widget.SearchView searchView;

    private RecyclerView recyclerView;

    private LinearLayout noResultsContainer;

    private ProgressBar loadingIndicator;

    private SearchPresenter presenter;

    private ResultAdapter resultAdapter;
    private List<IResult> itemList = new ArrayList<>();
    private PlaceAutocompleteFragment autocompleteFragment;
    private String location;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);

        presenter = new SearchPresenterImpl(
                this, new SearchUseCase(GithubDataSourceImpl.getInstance(this), SearchGovDataSourceImpl.getInstance(this)),
                LoggingHelperImpl.getInstance());


        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        noResultsContainer = findViewById(R.id.noResultsContainer);
        loadingIndicator = findViewById(R.id.loadingIndicator);

        resultAdapter = new ResultAdapter(this, itemList);
        recyclerView.setAdapter(resultAdapter);

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);


        presenter.onViewReady();

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                location = place.getName().toString();
                setQuery(query, location);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                query = s;
                setQuery(s, location);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }


    @Override
    public void setQuery(String jobName, String location) {
        presenter.onSearch(jobName, location);
    }

    @Override
    public void showResultItems(List<IResult> resultItems) {
        recyclerView.setVisibility(VISIBLE);
        itemList.clear();
        itemList.addAll(resultItems);
        resultAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideResultItems() {
        recyclerView.setVisibility(GONE);
    }


    @Override
    public void openUserDetailsActivity(String username) {

    }


    @Override
    public void showNoResultsContainer() {
        noResultsContainer.setVisibility(VISIBLE);
    }

    @Override
    public void hideNoResultsContainer() {
        noResultsContainer.setVisibility(GONE);
    }


    @Override
    public void showToast(int resId) {
        Toast.makeText(this, resId, LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingIndicator() {
        loadingIndicator.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        loadingIndicator.setVisibility(GONE);

    }
}
