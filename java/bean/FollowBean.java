package bean;

// Follow•\‚ÌBean
public class FollowBean extends Bean{
    private String followId;
    private String followerManagementId;
    private String followersManagementId;

    public String getFollowId(){
        return followId;
    }
    public void setFollowId(String followId){
        this.followId = followId;
    }
    public String getFollowerManagementId(){
        return followerManagementId;
    }
    public void setFollowerManagementId(String followerManagementId){
        this.followerManagementId = followerManagementId;
    }
    public String getFollowersManagementId(){
        return followersManagementId;
    }
    public void setFollowersManagementId(String followersManagementId){
        this.followersManagementId = followersManagementId;
    }
}