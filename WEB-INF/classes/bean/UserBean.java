package bean;

// Users表のBean
public class UserBean extends Bean{
	// 管理用のユーザーID
	private String managementId;
	// ユーザーID
	private String userId;
	// ユーザーネーム
	private String userName;
	// ユーザーのメールアドレス
	private String mailAddress;
	// ユーザーのパスワード
	private String password;
	// ユーザーのプロフィール文
	private String profile;
	// ユーザのプロフィールの写真
	private String profilePicture;
	// 公開(1)/非公開(0)の設定
	private String release;
	// 自分の投稿数
	private String postCount;
	// 自分のフォロー数
	private String follows;
	// 自分をフォローしてる数
	private String followers;
	// 自分のいいねした数
	private String likesCount;
	// ユーザーの状態を管理
	private String state;
	// 登録した日付
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
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName=userName;
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
	public void setProfile(String profile){
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

	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state=state;
	}
	
	public String getRegistredDate(){
		return registredDate;
	}
	public void setRegistredDate(String registredDate){
		this.registredDate=registredDate;
	}
}