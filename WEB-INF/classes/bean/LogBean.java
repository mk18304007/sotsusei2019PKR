package bean;

// Log表のbean
public class LogBean extends Bean{
    private String logId;
    private String activeManagementId;
    private String passiveManagementId;
    private String action;
    private String time;

    public String getLogId(){
        return logId;
    }
    public void setLogId(String logId){
        this.logId = logId;
    }
    public String getActiveManagementId(){
        return activeManagementId;
    }
    public void setaActiveManagementId(String activeManagementId){
        this.activeManagementId = activeManagementId;
    }
    public String getPassiveManagementId(){
        return passiveManagementId;
    }
    public void setPassiveManagementId(String passiveManagementId){
        this.passiveManagementId = passiveManagementId;
    }
    public String getAction(){
        return action;
    }
    public void setAction(String action){
        this.action = action;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
    
}