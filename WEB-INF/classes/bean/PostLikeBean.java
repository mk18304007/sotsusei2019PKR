package bean;

// PostLike表のBean
public class PostLikeBean extends Bean{
    // いいねしたユーザの管理用ID
    private String managementId;
    // いいねされた投稿の管理用ID
    private String postId;
    // いいね
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