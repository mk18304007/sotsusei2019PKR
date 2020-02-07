package bean;

// Likes表のbean
public class ActionBean extends Bean{

    // Action表の主キー
    private String actionId;
    // アクションしたユーザーの管理用ID
    private String activeManagementId;
    // アクションされたユーザーの管理用ID
    private String passiveManagementId;
    // フォロー(0)/ブロック(1)/いいね(2)/リプライ(3)/投稿(4)/DM(5)
    private String state;
    // アクション発生日時
    private String timeStamp;

    public String getActionId(){
        return actionId;
    }
    public void setActionId(String actionId){
        this.actionId = actionId;
    }
    public String getActiveManagementId(){
        return activeManagementId;
    }
    public void setActiveManagementId(String activeManagementId){
        this.activeManagementId = activeManagementId;
    }
    public String getPassiveManagementId(){
        return passiveManagementId;
    }
    public void setPassiveManagementId(String passiveManagementId){
        this.passiveManagementId = passiveManagementId;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getTimeStamp(){
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp){
        this.timeStamp = timeStamp;
    }
}
