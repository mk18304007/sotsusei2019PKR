/*融合召喚*/

package bean;

public class UsersPostLikesBean extends Bean{
	private UsersBean ub;
	private PostBean pb;
	private LikesBean lb;
	
	public void setUsersBean(UsersBean ub){
		this.ub=ub;
	}
	public UsersBean getUsersBean(){
		return ub;
	}
	public void setPostBean(PostBean pb){
		this.pb=pb;
	}
	public PostBean getPostBean(){
		return pb;
	}
	public void setLikesBean(LikesBean lb){
		this.lb=lb;
	}
	public LikesBean getLikesBean(){
		return lb;
	}
}