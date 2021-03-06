package bean;

// Likes表のbean
public class LikesBean extends Bean{
    // Likes表を管理するID
    private String likeId;
    // 管理用のユーザーID
    private String managementId;
    // 投稿のID
    private String postId;
    // リプライのID
    private String replyId;
    // 投稿に対する(0)/リプライに対する(1)
    private String state;
	//いいねされていることを表す変数
	//このBeanを参照するときは必ずいいねされているので
	//static finalで書き換え不可にする
	private static final String likeFlag="checked";


    public String getLikeId(){
        return likeId;
    }
    public void setLikeId(String likeId){
        this.likeId = likeId;
    }
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
    public String getReplyId(){
        return replyId;
    }
    public void setReplyId(String replyId){
        this.replyId = replyId;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
	public String getLikeFlag(){
		return likeFlag;
	}
}
