/*----------���O�C�����邽�߂̃R�}���h---------*/
package command;

import context.ResponseContext;

public class ToProfileCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//�]��������Z�b�g����
		resc.setTarget("profile");
		return resc;
	}
}