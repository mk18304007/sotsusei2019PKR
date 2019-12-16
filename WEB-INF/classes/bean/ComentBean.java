package bean;

// Coment•\‚ÌBean
public class ComentBean extends Bean{
    private String managementId;
    private String postId;
    private String comentId;
    private String coment;

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
    public String getComentId(){
        return comentId;
    }
    public void setComandId(String comentId){
        this.comentId = comentId;
    }
    public String getComent(){
        return coment;
    }
    public void setComent(String coment){
        this.coment = coment;
    }
}