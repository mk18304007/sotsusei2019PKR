package bean;

// Users�\��Bean
public class UserBean extends Bean{
	// �Ǘ��p�̃��[�U�[ID
	private String managementId;
	// ���[�U�[ID
	private String userId;
	// ���[�U�[�l�[��
	private String userName;
	// ���[�U�[�̃��[���A�h���X
	private String mailAddress;
	// ���[�U�[�̃p�X���[�h
	private String password;
	// ���[�U�[�̃v���t�B�[����
	private String profile;
	// ���[�U�̃v���t�B�[���̎ʐ^
	private String profilePicture;
	// ���J(1)/����J(0)�̐ݒ�
	private String release;
	// �����̃t�H���[��
	private String follows;
	// �������t�H���[���Ă鐔
	private String followers;
	// �����̓��e��
	private String postCount;
	// �����̂����˂�����
	private String likesCount;
	// ���[�U�[�̏�Ԃ��Ǘ�
	private String state;
	// �o�^�������t
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
	public String getPostCount(){
		return postCount;
	}
	public void setPostCount(String postCount){
		this.postCount=postCount;
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