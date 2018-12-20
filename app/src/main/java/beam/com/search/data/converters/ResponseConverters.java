package beam.com.search.data.converters;

import java.util.Arrays;

import beam.com.search.data.IResult;
import beam.com.search.data.github.model.GithubResponse;
import beam.com.search.data.searchgov.model.SearchGovResponse;

public class ResponseConverters {


    public static IResult convertGitHubResponseToSearchResult(GithubResponse response) {


        return new IResult() {
            @Override
            public String getCompanyName() {
                return response.getCompany();
            }

            @Override
            public String getJobTitle() {
                return response.getTitle();
            }

            @Override
            public String getLogo() {
                return response.getCompanyLogo();
            }

            @Override
            public String getLocation() {
                return response.getLocation();
            }

            @Override
            public String getPostDate() {
                return response.getCreatedAt();
            }

            @Override
            public String getProvider() {
                return "GitHub";
            }
        };
    }


    public static IResult convertSearchGovResponseToSearchResult(SearchGovResponse response) {


        return new IResult() {
            @Override
            public String getCompanyName() {
                return response.getOrganizationName();
            }

            @Override
            public String getJobTitle() {
                return response.getPositionTitle();
            }

            @Override
            public String getLogo() {
                return null;
            }

            @Override
            public String getLocation() {
                return Arrays.toString(response.getLocations().toArray());
            }

            @Override
            public String getPostDate() {
                return response.getStartDate();
            }

            @Override
            public String getProvider() {
                return "Search Gov";
            }
        };
    }


}
