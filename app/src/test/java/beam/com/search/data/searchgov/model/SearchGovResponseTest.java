package beam.com.search.data.searchgov.model;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import edu.emory.mathcs.backport.java.util.Arrays;

public class SearchGovResponseTest {


    @Test
    public void testData() {
        String data = "{\n" +
                "    \"id\": \"usajobs:515658500\",\n" +
                "    \"position_title\": \"Registered Nurse INTENSIVE CARE UNIT\",\n" +
                "    \"organization_name\": \"Veterans Affairs, Veterans Health Administration\",\n" +
                "    \"rate_interval_code\": \"PA\",\n" +
                "    \"minimum\": 44276,\n" +
                "    \"maximum\": 99776,\n" +
                "    \"start_date\": \"2018-10-31\",\n" +
                "    \"end_date\": \"2019-09-30\",\n" +
                "    \"locations\": [\n" +
                "      \"Muskogee, OK\"\n" +
                "    ],\n" +
                "    \"url\": \"https://www.usajobs.gov/GetJob/ViewDetails/515658500\"\n" +
                "  }";
        SearchGovResponse response = new Gson().fromJson(data, SearchGovResponse.class);

        response.setEndDate("123");
        response.setId("");
        String[] strings = {"heloo"};
        response.setLocations(Arrays.asList(strings));
        response.setMaximum(1221);
        response.setMinimum(100);
        response.setOrganizationName("");
        response.setPositionTitle("");
        response.setRateIntervalCode("");
        response.setStartDate("");
        response.setEndDate("");
        response.setUrl("");

        Assert.assertNotNull(response.getEndDate());
        Assert.assertNotNull(response.getId());
        Assert.assertNotNull(response.getLocations());
        Assert.assertNotNull(response.getMaximum());
        Assert.assertNotNull(response.getMinimum());
        Assert.assertNotNull(response.getOrganizationName());
        Assert.assertNotNull(response.getPositionTitle());
        Assert.assertNotNull(response.getRateIntervalCode());
        Assert.assertNotNull(response.getEndDate());
        Assert.assertNotNull(response.getStartDate());
        Assert.assertNotNull(response.getUrl());

    }
}