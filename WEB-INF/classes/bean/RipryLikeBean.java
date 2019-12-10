package bean;

// Ripry_Like表のBean
public class RipryLikeBean extends Bean{
    private String managementId;
    private String ripryId;
    private String good;

    public String getManagementId(){
        return managementId;
    }
    public void setManagementId(String managementId){
        this.managementId = managementId;
    }
    public String getRipryId(){
        return ripryId;
    }
    public void setRipryId(String ripryId){
        this.ripryId = ripryId;
    }
    public String getGood(){
        return good;
    }
    public void setGood(String good){
        this.good = good;
    }
}