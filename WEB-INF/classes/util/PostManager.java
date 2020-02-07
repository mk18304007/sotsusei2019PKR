/**
 * PostManager
 * 
 * @version  4.0
 * @author   Hideki Iizuka
 * @since    JDK5.0,
 * 
 * <p><b>BLOB�g�p�ɂ��t�@�C���A�b�v���[�h���ׂ�����邽��Part API���g�p</b></p>
 * @see <a href="https://www.oracle.com/technetwork/java/frontcontroller-135648.html">RFC Sun Microsystems, Inc</a>
 * 
 * <p><b>Attention: ServletRequest.getRealPath()�̓t�@�C���ǂݍ��ݎ�null��Ԃ��o�O�������N�����댯�������邽�ߔ񐄏�
 * Solution: �Ǝ�getRealPath()���쐬��getResourcePaths()�܂���getResourceAsStream()������</b></p>
 * @see <a href="">RFC , Inc</a>
 * 
 * @return the collection of path, not null
 */

package util;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.faces.component.UIViewRoot;
//import javax.faces.context.FacesContext;

//import javax.inject.Named;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// �t�@�C���A�b�v���[�hAPI
import javax.servlet.http.Part;

import context.RequestContext;
import context.ResponseContext;

public class PostManager extends HttpServlet
{
	private HttpServletRequest request;
	private HttpServletResponse response;
//	private HttpSession session;

	// �A�b�v���[�h�t�@�C���ϐ�
	private Part file;
	
	private String contentsPath;

	public PostManager(RequestContext reqc)// throws ServletException, IOException
	{
		request = (HttpServletRequest)reqc.getRequest();
		
		try
		{
			System.out.println("request�F" + request);	//null�`�F�b�N
			
			// �ۑ���̃f�B���N�^�[���擾
			//String contents = reqc.getParameter("contents")[0];

			// <input type="file" name="contents">����l���擾
			System.out.println("request.getPart()�F" + request.getPart("contents"));	//null�`�F�b�N
			file = request.getPart("contents");
			
			//String contents = null;
			
			for (String cd : file.getHeader("Content-Disposition").split(";")) 
			{
				if (cd.trim().startsWith("filename")) 
				{
					//contents = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
					contentsPath = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				}
			}
			
			System.out.println("file�̒��g�� " + file);

			// �A�b�v���[�h�����t�@�C���i�o�C�i���f�[�^�j��ǂݍ��ނ��߂̃X�g���[�����擾
			InputStream inputstream = file.getInputStream();

			// �N���C�A���g���玦���ꂽ�t�@�C���̖��O���擾
//			String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();

			String filename = file.getSubmittedFileName();
			
			System.out.println("filename�̒��g�� " + file);	//null�`�F�b�N

			//�g���q���擾
			String extension = filename.substring(filename.lastIndexOf("."));
			
			System.out.println("if���O��extension�̒��g�� " + extension);	//null�`�F�b�N
			
			//��΃p�X�ɕϊ�
//			String filepath = getRealPath("/GraduationWork/images");
			String filepath = "C:/webapps/impaku/images/";
				
			System.out.println("realpath�̒��g�� " + filepath);

			//���ׂẴo�C�g����̓X�g���[������t�@�C���ɃR�s�[����t�@�C���ۊǏ���
//			Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			file.write(filepath+filename);
			
			System.out.println("if��images����extension�̒��g�� " + extension);	//null�`�F�b�N

			//input�^�O��accept�����Ő������Ă���MIME��
			//image/gif�Aimage/jpeg�Aimage/png�Avideo/mp4�Avideo/webm�Avideo/ogg �̂U���
			//�g���q��image��video�̃R�s�[��t�@�C���̂ӂ邢��������
/*			if(extension == ".gif" || extension == ".jpg" || extension == ".jpeg" || extension == ".png")
			{
				System.out.println("if������extension�̒��g�� " + extension);	//null�`�F�b�N
				
				//��΃p�X�ɕϊ�
				String filepath = getRealPath("/GraduationWork/images");
				
				System.out.println("realpath�̒��g�� " + filepath);

				//���ׂẴo�C�g����̓X�g���[������t�@�C���ɃR�s�[����t�@�C���ۊǏ���
				Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				System.out.println("if��images����extension�̒��g�� " + extension);	//null�`�F�b�N
			}
			else if(extension == ".mp4" || extension == ".m4v" || extension == ".ogv" || extension == ".webm")
			{
				//�A�b�v���[�h�����t�@�C�����擾���ĕۑ����邽�߂̃X�g���[���𓾂�
//				InputStream inputstream = file.getInputStream();

				//��΃p�X�ɕϊ�
				String filepath = getRealPath("/GraduationWork/videos");
				
				System.out.println("filepath�̒��g�� " + filepath);	//null�`�F�b�N
				
				//�A�b�v���[�h�����t�@�C�����擾���ĕۑ�
//				InputStream fileContent = file.getInputStream();

				//���ׂẴo�C�g����̓X�g���[������t�@�C���ɃR�s�[����t�@�C���ۊǏ���
				Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				System.out.println("if��videos����Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING)�̒��g�� " + Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING));	//null�`�F�b�N
			}*/
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("���o�̓G���[");
			System.out.println("�ȉ�URL���Q�Ƃ�Exception�̑Ώ����s���Ă�������");
			System.out.println("https://software.fujitsu.com/jp/manual/manualfiles/M050000/B1WN5031/03/msg45/msg09934.htm");
			Logger.getLogger(PostManager.class.getName()).log(Level.SEVERE, e.toString());
		}
		catch(IllegalStateException e)
		{
			//�t�@�C���܂��̓��N�G�X�g���傫�������ꍇ��catch��
			//�N���C�A���g�Ƀt�@�C���܂��̓��N�G�X�g�����@�I�ɊԈ���Ă��邱�Ƃ������X�e�[�^�X�R�[�h(400)�̃G���[�y�[�W��Ԃ�
			e.printStackTrace();
			System.out.println("�t�@�C���܂��̓��N�G�X�g�G���[");
			System.out.println("�ȉ�URL���Q�Ƃ�Exception�̑Ώ����s���Ă�������");
			System.out.println("https://software.fujitsu.com/jp/manual/manualfiles/M050000/B1WN5031/03/index.htm");
			
			try
			{
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			catch(IOException ee)
			{
				System.out.println(ee.getMessage());
			}

        return;
		}
		catch(ServletException e)
		{
			e.printStackTrace();
			System.out.println("�T�[�u���b�g�����̃G���[");
			System.out.println("�ȉ�URL���Q�Ƃ�Exception�̑Ώ����s���Ă�������");
			System.out.println("https://software.fujitsu.com/jp/manual/manualfiles/M050000/B1WN5031/03/msg45/msg09934.htm");
			Logger.getLogger(PostManager.class.getName()).log(Level.SEVERE, e.toString());
		}
	}

	//�A�v���P�[�V�������[�g����̑��΃p�X���΃p�X�ɕϊ�����
	//path �A�v���P�[�V�������[�g�̃��\�[�X����̑��΃p�X�iex. /resources/�`�j
    public String getRealPath(String path)
    {
//    	System.out.println("getServletConfig()�� " + getServletConfig());
    	
//        ServletConfig servletconfig = getServletConfig();
    	
//    	System.out.println("servletconfig�� " + servletconfig);

//        ServletContext servletcontext = servletconfig.getServletContext();
    	
//    	ServletContext servletcontext = getServletConfig().getServletContext();
    	
    	//System.out.println("getServletConfig().getServletContext()�� " + getServletConfig().getServletContext());

//    	System.out.println("servletcontext�� " + servletcontext);
    	
//      ServletContext realpath = servletcontext.getRealPath(path);
//        String realpath = servletcontext.getResourcePaths(path).toString();
    	
    	String realpath = getServletContext().getRealPath(path).toString();
    	
    	System.out.println("realpath�̒��g�� " + realpath);	//null�`�F�b�N

        return realpath;
    }    
    
    //getter
    public Part getFile()
    {
        return file;
    }

    //setter
    public void setFile(Part file)
    {
        this.file = file;
    }
	//getter
    public String getContentsPath()
    {
        return contentsPath;
    }

    //setter
    public void setContentsPath(String contentsPath)
    {
        this.contentsPath = contentsPath;
    }
}
