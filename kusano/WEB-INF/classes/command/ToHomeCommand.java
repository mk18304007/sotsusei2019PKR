/*----------Home��ʂֈړ����邽�߂̃R�}���h----------*/
package command;

import context.ResponseContext;

public class ToHomeCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//�]��������Z�b�g����
		resc.setTarget("home");
		return resc;
	}
}