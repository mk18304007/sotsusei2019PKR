package bean;

// DrirectMail表のBean
public class DirectMailBean extends Bean{
    // DMを送るユーザーの管理用ID
    private String sendManagementId;
    // DMを送られるユーザーの管理用ID
    private String sentManagementId;
    // DMで送られる文章
    private String talk;
    // DMで送られる写真/動画のパス
    private String contents;

    public String getSendManagementId(){
        return sendManagementId;
    }
    public void setSendManagementId(String sendManagementId){
        this.sendManagementId = sendManagementId;
    }
    public String getSentManagementId(){
        return sentManagementId;
    }
    public void setSentManagementId(String sentManagementId){
        this.sentManagementId = sentManagementId;
    }
    public String getTalk(){
        return talk;
    }
    public void setTalk(String talk){
        this.talk = talk;
    }
    public String getContents(){
        return contents;
    }
    public void setContents(String contents){
        this.contents = contents;
    }
}
