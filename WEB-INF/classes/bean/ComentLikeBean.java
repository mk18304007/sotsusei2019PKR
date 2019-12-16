package bean;

// Coment_Like•\‚ÌBean
public class ComentLikeBean extends Bean{
    private String managementId;
    private String comentId;
    private String good;

    public String getManagementId(){
        return managementId;
    }
    public void setManagementId(final String managementId){
        this.managementId = managementId;
    }
    public String getComentId(){
        return comentId;
    }
    public void setComentId(final String comentId){
        this.comentId = comentId;
    }
    public String getGood(){
        return good;
    }
    public void setGood(final String good){
        this.good = good;
    }
}