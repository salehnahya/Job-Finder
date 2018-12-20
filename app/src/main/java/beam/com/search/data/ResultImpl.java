package beam.com.search.data;

public class ResultImpl implements IResult {

    String companyName;
    String jobTitle;
    String logo;
    String location;
    String postDate;
    String provider;

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
}
