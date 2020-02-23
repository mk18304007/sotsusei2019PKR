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
import java.util.Enumeration;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import context.RequestContext;
import context.ResponseContext;

public class PostManager extends HttpServlet{
	private HttpServletRequest request;
	private HttpServletResponse response;
//private Part file;
	private Collection<Part> files;
	//クラス構造のハッシュ値を定義することで型同一性チェック
	private static final long serialVersionUID = 1L;
	
	private ArrayList contentsPath = new ArrayList((Arrays.asList(null,null,null,null,null,null,null,null,null,null)));
	public PostManager(RequestContext reqc){
		request = (HttpServletRequest)reqc.getRequest();
		try{
			files = request.getParts();
			int i = 0;
			for(Part file : files){
				InputStream inputstream = file.getInputStream();
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa"+file);
				String filename = getFileName(file);
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaab"+filename);
				if(filename != null){
					//拡張子を取得
					String extension = filename.substring(filename.lastIndexOf("."));

					contentsPath.remove(i);
					contentsPath.add(i,filename);

					//絶対パスに変換
					String filepath = "C:/webapps/impaku/images/";
					file.write(filepath + filename);
				}
				i++;
			}
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("入出力エラー");
			System.out.println("以下URLを参照しExceptionの対処を行ってください");
			System.out.println("https://software.fujitsu.com/jp/manual/manualfiles/M050000/B1WN5031/03/msg45/msg09934.htm");
			Logger.getLogger(PostManager.class.getName()).log(Level.SEVERE, e.toString());
		}catch(IllegalStateException e){
			//ファイルまたはリクエストが大きすぎた場合のcatch句
			//クライアントにファイルまたはリクエストが文法的に間違っていることを示すステータスコード(400)のエラーページを返す
			e.printStackTrace();
			System.out.println("ファイルまたはリクエストエラー");
			System.out.println("以下URLを参照しExceptionの対処を行ってください");
			System.out.println("https://software.fujitsu.com/jp/manual/manualfiles/M050000/B1WN5031/03/index.htm");
			
			try{
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}catch(IOException ee){
				System.out.println(ee.getMessage());
			}
			return;
		}catch(ServletException e){
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
    	String realpath = getServletContext().getRealPath(path).toString();
    	
    	System.out.println("realpathの中身は " + realpath);	//nullチェック

        return realpath;
    }    
    

	//getter
    public ArrayList getContentsPath()
    {
    	System.out.println("contentsPathの中身は " + contentsPath);	//nullチェック
        return contentsPath;
    }
	
	private String getFileName(Part files)
	{
		for(String cd : files.getHeader("Content-Disposition").split(";")) 
		{
			
				if (cd.trim().startsWith("filename")) 
				{
					//contentsPath = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
					String filePath = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				 
					System.out.println("contentsPathの中身は " + filePath );
					
					File file = new File(filePath);
					//ファイルパスからファイル名を取得
					return file.getName();
				}
			
		}
		return null;
	}
}