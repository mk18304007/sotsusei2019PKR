package bean;

public class UserBean extends Bean{
	private String managementId;
	private String userId;
	private String name;
	private String mailAddress;
	private String password;
	private String profile;
	private String profilePicture;
	private String release;
	private String postCount;
	private String follows;
	private String followers;
	private String likesCount;
	private String registredDate;
	
	public String getManagementId(){
		return managementId;
	}
	public void setManagementId(String managementId){
		this.managementId=managementId;
	}
	
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public String getMailAddress(){
		return mailAddress;
	}
	public void setMailAddress(String mailAddress){
		this.mailAddress=mailAddress;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getProfile(){
		return profile;
	}
	public void setProfilet(String profile){
		this.profile=profile;
	}
	
	public String getProfilePicture(){
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture){
		this.profilePicture=profilePicture;
	}
	
	public String getRelease(){
		return release;
	}
	public void setRelease(String release){
		this.release=release;
	}
	
	public String getPostCount(){
		return postCount;
	}
	public void setPostCount(String postCount){
		this.postCount=postCount;
	}
	
	public String getFollows(){
		return follows;
	}
	public void setFollows(String follows){
		this.follows=follows;
	}
	
	public String getFollowers(){
		return followers;
	}
	public void setFollowers(String followers){
		this.followers=followers;
	}
	
	public String getLikesCount(){
		return likesCount;
	}
	public void setLikesCount(String likesCount){
		this.likesCount=likesCount;
	}
	
	public String getRegistredDate(){
		return registredDate;
	}
	public void setRegistredDate(String registredDate){
		this.registredDate=registredDate;
	}
}