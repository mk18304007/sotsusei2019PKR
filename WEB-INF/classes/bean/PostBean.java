package bean;

// Post•\‚ÌBean
public class PostBean extends Bean{
    private String postId;
    private String managementId;
    private String contents;
    private String text;
    private String report;

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
}