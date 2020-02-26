package bean;

// Postの表Bean
public class PostBean extends Bean{
    // 投稿の管理用ID
    private String postId;
    // 投稿したユーザーの管理用ID
    private String managementId;
    // 写真/動画のパスを入れる
    private String contents1;
    private String contents2;
    private String contents3;
    private String contents4;
    private String contents5;
    private String contents6;
    private String contents7;
    private String contents8;
    private String contents9;
    private String contents10;
    // 投稿の文書
    private String text;
    // 投稿に対する通報数
    private String report;
    // いいねされた数
    private String likesCount;
	private String timeStamp;

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
    public void setContents1(String contents1){
        this.contents1 = contents1;
    }
    public String getContents1(){
        return contents1;
    }
    public void setContents2(String contents2){
        this.contents2 = contents2;
    }
    public String getContents2(){
        return contents2;
    }
    public void setContents3(String contents3){
        this.contents3 = contents3;
    }
    public String getContents3(){
        return contents3;
    }
    public void setContents4(String contents4){
        this.contents4 = contents4;
    }
    public String getContents4(){
        return contents4;
    }
    public void setContents5(String contents5){
        this.contents5 = contents5;
    }
    public String getContents5(){
        return contents5;
    }
    public void setContents6(String contents6){
        this.contents6 = contents6;
    }
    public String getContents6(){
        return contents6;
    }
    public void setContents7(String contents7){
        this.contents7 = contents7;
    }
    public String getContents7(){
        return contents7;
    }
    public void setContents8(String contents8){
        this.contents8 = contents8;
    }
    public String getContents8(){
        return contents8;
    }
    public void setContents9(String contents9){
        this.contents9 = contents9;
    }
    public String getContents9(){
        return contents9;
    }
    public void setContents10(String contents10){
        this.contents10 = contents10;
    }
    public String getContents10(){
        return contents10;
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
	public String getTimeStamp(){
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp){
		this.timeStamp=timeStamp;
	}
}
