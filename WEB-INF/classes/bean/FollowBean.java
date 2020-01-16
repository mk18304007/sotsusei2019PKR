package bean;

// Follow表のBean
public class FollowBean extends Bean{
    // フォローした、されたを管理するID
    private String followId;
    // フォローされたユーザーの管理用ID
    private String followerManagementId;
    // フォローしたユーザーの管理用ID
    private String followersManagementId;
    // フォロー状態管理
    private String state;

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
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
}