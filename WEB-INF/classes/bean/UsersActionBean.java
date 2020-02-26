/*シンクロ召喚*/
package bean;

public class UsersActionBean extends Bean{
	private UsersBean ub;
	//フォローされてる(1)/ふぉろーされてない(0)/ブロックされてる(2)
	private String state;
	
	public UsersBean getUsersBean(){
		return ub;
	}
	public void setUsersBean(UsersBean ub){
		this.ub=ub;
	}
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state=state;
	}
}