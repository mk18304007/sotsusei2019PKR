/*----------���[�U�[�o�^��ʂɈړ����邽�߂̃R�}���h----------*/
package command;

import java.io.IOException;
import context.ResponseContext;

public class ToSignupCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//�]��������Z�b�g����
		resc.setTarget("signup");
		return resc;
	}
}