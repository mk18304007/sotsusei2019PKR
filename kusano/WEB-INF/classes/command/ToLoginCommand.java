/*-----------���O�C����ʂֈړ����邽�߂̃R�}���h-----------*/
package command;

import context.ResponseContext;

public class ToLoginCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//�]��������Z�b�g����
		resc.setTarget("login");
		return resc;
	}
}