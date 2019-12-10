package bean;

// Coment表のBean
public class ComentBean extends Bean{
    // コメントをしたユーザーの管理用ID
    private String managementId;
    // コメントをされた投稿の管理用ID
    private String postId;
    // コメントの管理用ID
    private String comentId;
    // 投稿に対するコメント
    private String coment;
    // コメントの状態を管理
	private String state;

    public String getManagementId(){
        return managementId;
    }
    public void setManagementId(final String managementId){
        this.managementId = managementId;
    }
    public String getPostId(){
        return postId;
    }
    public void setPostId(final String postId){
        this.postId = postId;
    }
    public String getComentId(){
        return comentId;
    }
    public void setComandId(final String comentId){
        this.comentId = comentId;
    }
    public String getComent(){
        return coment;
    }
    public void setComent(final String coment){
        this.coment = coment;
    }
    public String getState(){
		return state;
	}
	public void setState(final String state){
		this.state=state;
	}
}