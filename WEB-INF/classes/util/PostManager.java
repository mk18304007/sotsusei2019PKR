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

public class PostManager extends HttpServlet{
	private HttpServletRequest request;
	private HttpServletResponse response;
	//アップロードファイル変数
	private Part file;
	private String contentsPath;

	public PostManager(RequestContext reqc){
		request = (HttpServletRequest)reqc.getRequest();
		try{
			//<input type="file" name="contents">から値を取得
			file = request.getPart("contents");
			
			for (String cd : file.getHeader("Content-Disposition").split(";")){
				if (cd.trim().startsWith("filename")){
					contentsPath = "/images/" + cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				}
			}
			//アップロードしたファイル（バイナリデータ）を読み込むためのストリームを取得
			InputStream inputstream = file.getInputStream();
			
			String filename = file.getSubmittedFileName();
			if(filename.equals("")){
				filename+=".jpg";
			}
			//拡張子を取得
			String extension = filename.substring(filename.lastIndexOf("."));
			
			//絶対パスに変換
			String filepath = "C:/webapps/impaku/images/";
			
			file.write(filepath+filename);
		}catch(IOException e){
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
			}catch(IOException ex){
				ex.printStackTrace();
			}
			return;
		}catch(ServletException e){
			e.printStackTrace();
			System.out.println("サーブレット内部のエラー");
			System.out.println("以下URLを参照しExceptionの対処を行ってください");
			System.out.println("https://software.fujitsu.com/jp/manual/manualfiles/M050000/B1WN5031/03/msg45/msg09934.htm");
			Logger.getLogger(PostManager.class.getName()).log(Level.SEVERE, e.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//アプリケーションルートからの相対パスを絶対パスに変換する
	//path アプリケーションルートのリソースからの相対パス（ex. /resources/～）
	public String getRealPath(String path){
		String realpath = getServletContext().getRealPath(path).toString();
		
		return realpath;
	}
	//getter
	public Part getFile(){
		return file;
	}
	public String getContentsPath(){
		return contentsPath;
	}
	//setter
	public void setFile(Part file){
		this.file = file;
	}
	public void setContentsPath(String contentsPath){
		this.contentsPath = contentsPath;
	}
}
