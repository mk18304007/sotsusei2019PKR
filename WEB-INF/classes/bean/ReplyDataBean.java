
/*エクシーズ召喚*/
package bean;

import java.util.List;

public class ReplyDataBean extends Bean{
	private UsersBean ub;
	private ReplyBean rb;
	private List replyList;
	public void setUsersBean(UsersBean ub){
		this.ub=ub;
	}
	public UsersBean getUsersBean(){
		return ub;
	}
	public void setReplyBean(ReplyBean rb){
		this.rb=rb;
	}
	public ReplyBean getReplyBean(){
		return rb;
	}
	public void setReplyList(List replyList){
		this.replyList=replyList;
	}
	public List getReplyList(){
		return replyList;
	}
}