package bean;

// Postの表Bean
public class PostBean extends Bean{
    // 投稿の管理用ID
    private String postId;
    // 投稿したユーザーの管理用ID
    private String managementId;
    // 写真/動画のパスを入れる
    private String contents;
    // 投稿の文書
    private String text;
    // 投稿に対する通報数
    private String report;
    // いいねされた数
    private String likesCount;

    public String getPostId(){
        return postId;
    }
    public void setPostId(String postId){
        this.postId = postId;
    }
    public String getManagementId(){
        return managementId;
    }
    public void setManagementId(String managementId){
        this.managementId = managementId;
    }
    public String getContents(){
        return contents;
    }
    public void setContents(String contents){
        this.contents = contents;
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getReport(){
        return report;
    }
    public void setReport(String report){
        this.report = report;
    }
    public String getLikesCount(){
		return likesCount;
	}
	public void setLikesCount(String likesCount){
		this.likesCount=likesCount;
	}
}
