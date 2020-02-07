package bean;

// Reply表のBean
public class ReplyBean extends Bean{
    // リプライをしたユーザーの管理用ID
    private String managementId;
    // リプライをされた投稿の管理用ID
    private String postId;
    // リプライの管理用ID
    private String replyId;
    // 投稿に対するリプライ
    private String reply;
    // リプライに対するコメントのID
    private String commentId;
    //いいねされた数
    private String likesCount;
    // リプライの状態を管理
    private String state;


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
		this.replyId=replyId;
    }
    public String getReply(){
        return reply;
    }
    public void setReply(String reply){
        this.reply = reply;
    }
    public String getCommentId(){
        return commentId;
    }
    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    public String getLikesCount(){
		return likesCount;
	}
	public void setLikesCount(String likesCount){
		this.likesCount=likesCount;
	}
    public String getState(){
		return state;
	}
	public void setState(String state){
		this.state=state;
    }
}
