package beam.com.search.data.github.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GithubResponseTest {

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testData() {
        GithubResponse response = new GithubResponse();
        response.setCompany("");
        response.setCompanyLogo("");
        response.setCompanyUrl("");
        response.setCreatedAt("");
        response.setId("");
        response.setUrl("");
        response.setDescription("");
        response.setType("");
        response.setLocation("");
        response.setTitle("");
        response.setUrl("");
        response.setHowToApply("!!!");

        Assert.assertNotNull(response.getCompany());
        Assert.assertNotNull(response.getCompanyLogo());
        Assert.assertNotNull(response.getCompanyUrl());
        Assert.assertNotNull(response.getCreatedAt());
        Assert.assertNotNull(response.getId());
        Assert.assertNotNull(response.getCompany());
        Assert.assertNotNull(response.getDescription());
        Assert.assertNotNull(response.getType());
        Assert.assertNotNull(response.getTitle());
        Assert.assertNotNull(response.getLocation());
        Assert.assertNotNull(response.getUrl());
        Assert.assertNotNull(response.getHowToApply());

    }
}