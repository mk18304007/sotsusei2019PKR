package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{
	private FilterConfig config;
	
	public void init(FilterConfig config)throws ServletException{
		this.config=config;
	}
	public void destroy(){}
	
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException{
		HttpServletRequest hreq=(HttpServletRequest)req;
		HttpServletResponse hres=(HttpServletResponse)res;
		HttpSession session=hreq.getSession();
		System.out.println("do LoginCheckFilter");
		
		//ログイン判定用の変数
		boolean loggedin=false;
		
		//セッションが取得できている場合
		if(session!=null){
			//ユーザー情報がセッションに登録されているかどうか(ログインできているか)
			//をbooleanで格納する
			loggedin=session.getAttribute("user")!=null;
		}
		
		//ログインできていれば
		if(loggedin){
			//本来のページへ移動
			chain.doFilter(hreq,hres);
			
		//ログインできていない場合
		}else{
			//サーブレットパスを取得
			String servletPath=hreq.getServletPath();
			System.out.println("LoginCheckFilter.doFilter.else.servletPath:"+servletPath);
			
			//取得したサーブレットパスをセッションに追加
			hreq.setAttribute("target",servletPath);
			
			//ログインページへ戻す
			hreq.getRequestDispatcher("/login").forward(hreq,hres);
		}
	}
}