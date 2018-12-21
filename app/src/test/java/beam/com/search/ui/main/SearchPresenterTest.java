package beam.com.search.ui.main;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import beam.com.search.R;
import beam.com.search.data.IResult;
import beam.com.search.data.ResultImpl;
import beam.com.search.domain.SearchUseCase;
import beam.com.search.domain.UseCase;
import beam.com.search.logging.LoggingHelperImpl;

import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class SearchPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private SearchView view;

    @Mock

    private LoggingHelperImpl loggingHelper;

    private SearchPresenter presenter;

    @Mock
    private SearchUseCase searchUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new SearchPresenterImpl(view, searchUseCase, loggingHelper);
    }

    @Test
    public void givenValidUseCaseThenOnSuccessCalledWithEmptyList() {
        UseCase<String, String, List<IResult>> successUseCase = (s, s2, callback, onErrorCallback) ->
                callback.onSuccess(null);
        presenter = new SearchPresenterImpl(view, successUseCase, loggingHelper);

        presenter.onViewReady();
        verify(view).showLoadingIndicator();
        verify(view).hideResultItems();
        verify(view).hideNoResultsContainer();

        verify(view).showNoResultsContainer();


    }


    @Test
    public void givenValidUseCaseThenOnSuccessCalledWithValidList() {
        List<IResult> list = new ArrayList<>();
        list.add(new ResultImpl());


        UseCase<String, String, List<IResult>> successUseCase =
                (s, s2, callback, onErrorCallback) -> callback.onSuccess(list);


        presenter = new SearchPresenterImpl(view, successUseCase, loggingHelper);

        presenter.onViewReady();
        verify(view).showLoadingIndicator();
        verify(view).hideResultItems();
        verify(view).hideNoResultsContainer();

        verify(view).showResultItems(list);
    }


    @Test
    public void givenValidUseCaseThenFailure() {


        UseCase<String, String, List<IResult>> onErrorCallBack =
                (s, s2, callback, onErrorCallback) ->
                        onErrorCallback.onError(null);


        presenter = new SearchPresenterImpl(view, onErrorCallBack, loggingHelper);
        presenter.onViewReady();
        verify(view).showLoadingIndicator();
        verify(view).hideResultItems();


        verify(view).hideLoadingIndicator();
        verify(view).showToast(R.string.an_error_occurred);

    }


    @Test
    public void givenValidSearchThenOnSuccessCalledWithValidList() {
        List<IResult> list = new ArrayList<>();
        list.add(new ResultImpl());


        UseCase<String, String, List<IResult>> successUseCase =
                (s, s2, callback, onErrorCallback) -> callback.onSuccess(list);


        presenter = new SearchPresenterImpl(view, successUseCase, loggingHelper);

        presenter.onSearch("Nurse", "New York");
        verify(view).showLoadingIndicator();
        verify(view).hideResultItems();
        verify(view).hideNoResultsContainer();

        verify(view).showResultItems(list);
    }


}