package bean;

// Ripry�\��Bean
public class RipryBean extends Bean{
    private String managementId;
    private String comentId;
    private String ripryId;
    private String ripry;

    public String getManagementId(){
        return managementId;
    }
    public void setManagementId(String managementId){
        this.managementId = managementId;
    }
    public String getComentId(){
        return comentId;
    }
    public void setComentId(String comentId){
        this.comentId = comentId;
    }
    public String getRepryId(){
        return ripryId;
    }
    public void setRepryId(String ripryId){
        this.ripryId = ripryId;
    }
    public String getRipry(){
        return ripry;
    }
    public void set(String ripry){
        this.ripry = ripry;
    }
}