package bean;

// Post_Like•\‚ÌBean
public class PostLikeBean extends Bean{
    private String managementId;
    private String postId;
    private String good;

    public String getManagementId(){
        return managementId;
    }
    public void setManagementId(String managementId){
        this.managementId = managementId;
    }
    public String getPostId(){
        return postId;
    }
    public void setPostId(String postId){
        this.postId = postId;
    }
    public String getGood(){
        return good;
    }
    public void set(String good){
        this.good = good;
    }

}