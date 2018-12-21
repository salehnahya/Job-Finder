package beam.com.search.data;

public class ResultImpl implements IResult {

    private String companyName;
    private String jobTitle;
    private String logo;
    private String location;
    private String postDate;
    private String provider;
    private String url;

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getPostDate() {
        return postDate;
    }

    @Override
    public String getProvider() {
        return provider;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
