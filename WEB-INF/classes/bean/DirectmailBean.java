package bean;

// Drirectmail•\‚Ìbean
public class DirectmailBean extends Bean{
    private String sendManagementId;
    private String sentManagementId;
    private String talk;
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