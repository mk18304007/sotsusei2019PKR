/*----------���[�U�[�o�^��ʂɈړ����邽�߂̃R�}���h----------*/
package command;

import context.ResponseContext;

public class ToSignUpCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//�]��������Z�b�g����
		resc.setTarget("signup");
		return resc;
	}
}