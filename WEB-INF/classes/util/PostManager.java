/**
 * PostManager
 * 
 * @version  4.0
 * @author   Hideki Iizuka
 * @since    JDK5.0,
 * 
 * <p><b>BLOB使用によるファイルアップロード負荷を避けるためPart APIを使用</b></p>
 * @see <a href="https://www.oracle.com/technetwork/java/frontcontroller-135648.html">RFC Sun Microsystems, Inc</a>
 * 
 * <p><b>Attention: ServletRequest.getRealPath()はファイル読み込み時nullを返すバグを引き起こす危険性があるため非推奨
 * Solution: 独自getRealPath()を作成しgetResourcePaths()またはgetResourceAsStream()を実装</b></p>
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
// ファイルアップロードAPI
import javax.servlet.http.Part;

import context.RequestContext;
import context.ResponseContext;

public class PostManager extends HttpServlet
{
	private HttpServletRequest request;
	private HttpServletResponse response;
//	private HttpSession session;

	// アップロードファイル変数
	private Part file;
	
	private String contentsPath;

	public PostManager(RequestContext reqc)// throws ServletException, IOException
	{
		request = (HttpServletRequest)reqc.getRequest();
		
		try
		{
			System.out.println("request：" + request);	//nullチェック
			
			// 保存先のディレクターを取得
			//String contents = reqc.getParameter("contents")[0];

			// <input type="file" name="contents">から値を取得
			System.out.println("request.getPart()：" + request.getPart("contents"));	//nullチェック
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
			
			System.out.println("fileの中身は " + file);

			// アップロードしたファイル（バイナリデータ）を読み込むためのストリームを取得
			InputStream inputstream = file.getInputStream();

			// クライアントから示されたファイルの名前を取得
//			String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();

			String filename = file.getSubmittedFileName();
			
			System.out.println("filenameの中身は " + file);	//nullチェック

			//拡張子を取得
			String extension = filename.substring(filename.lastIndexOf("."));
			
			System.out.println("if文前のextensionの中身は " + extension);	//nullチェック
			
			//絶対パスに変換
//			String filepath = getRealPath("/GraduationWork/images");
			String filepath = "C:/webapps/impaku/images/";
				
			System.out.println("realpathの中身は " + filepath);

			//すべてのバイトを入力ストリームからファイルにコピーするファイル保管処理
//			Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			file.write(filepath+filename);
			
			System.out.println("if文images内のextensionの中身は " + extension);	//nullチェック

			//inputタグのaccept属性で制限しているMIMEは
			//image/gif、image/jpeg、image/png、video/mp4、video/webm、video/ogg の６種類
			//拡張子でimageとvideoのコピー先ファイルのふるい分けする
/*			if(extension == ".gif" || extension == ".jpg" || extension == ".jpeg" || extension == ".png")
			{
				System.out.println("if文内のextensionの中身は " + extension);	//nullチェック
				
				//絶対パスに変換
				String filepath = getRealPath("/GraduationWork/images");
				
				System.out.println("realpathの中身は " + filepath);

				//すべてのバイトを入力ストリームからファイルにコピーするファイル保管処理
				Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				System.out.println("if文images内のextensionの中身は " + extension);	//nullチェック
			}
			else if(extension == ".mp4" || extension == ".m4v" || extension == ".ogv" || extension == ".webm")
			{
				//アップロードしたファイルを取得して保存するためのストリームを得る
//				InputStream inputstream = file.getInputStream();

				//絶対パスに変換
				String filepath = getRealPath("/GraduationWork/videos");
				
				System.out.println("filepathの中身は " + filepath);	//nullチェック
				
				//アップロードしたファイルを取得して保存
//				InputStream fileContent = file.getInputStream();

				//すべてのバイトを入力ストリームからファイルにコピーするファイル保管処理
				Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				System.out.println("if文videos内のFiles.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING)の中身は " + Files.copy(inputstream, new File(filepath ,filename).toPath(), StandardCopyOption.REPLACE_EXISTING));	//nullチェック
			}*/
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("入出力エラー");
			System.out.println("以下URLを参照しExceptionの対処を行ってください");
			System.out.println("https://software.fujitsu.com/jp/manual/manualfiles/M050000/B1WN5031/03/msg45/msg09934.htm");
			Logger.getLogger(PostManager.class.getName()).log(Level.SEVERE, e.toString());
		}
		catch(IllegalStateException e)
		{
			//ファイルまたはリクエストが大きすぎた場合のcatch句
			//クライアントにファイルまたはリクエストが文法的に間違っていることを示すステータスコード(400)のエラーページを返す
			e.printStackTrace();
			System.out.println("ファイルまたはリクエストエラー");
			System.out.println("以下URLを参照しExceptionの対処を行ってください");
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
			System.out.println("サーブレット内部のエラー");
			System.out.println("以下URLを参照しExceptionの対処を行ってください");
			System.out.println("https://software.fujitsu.com/jp/manual/manualfiles/M050000/B1WN5031/03/msg45/msg09934.htm");
			Logger.getLogger(PostManager.class.getName()).log(Level.SEVERE, e.toString());
		}
	}

	//アプリケーションルートからの相対パスを絶対パスに変換する
	//path アプリケーションルートのリソースからの相対パス（ex. /resources/～）
    public String getRealPath(String path)
    {
//    	System.out.println("getServletConfig()は " + getServletConfig());
    	
//        ServletConfig servletconfig = getServletConfig();
    	
//    	System.out.println("servletconfigは " + servletconfig);

//        ServletContext servletcontext = servletconfig.getServletContext();
    	
//    	ServletContext servletcontext = getServletConfig().getServletContext();
    	
    	//System.out.println("getServletConfig().getServletContext()は " + getServletConfig().getServletContext());

//    	System.out.println("servletcontextは " + servletcontext);
    	
//      ServletContext realpath = servletcontext.getRealPath(path);
//        String realpath = servletcontext.getResourcePaths(path).toString();
    	
    	String realpath = getServletContext().getRealPath(path).toString();
    	
    	System.out.println("realpathの中身は " + realpath);	//nullチェック

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
