/*----------�v���t�B�[���ҏW��ʂֈړ����邽�߂̃R�}���h----------*/
package command;

import context.ResponseContext;

public class ToProfileEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//�]��������Z�b�g����
		resc.setTarget("profileEdit");
		return resc;
	}
}