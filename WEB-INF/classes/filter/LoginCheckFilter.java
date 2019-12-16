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
		
		//���O�C������p�̕ϐ�
		boolean loggedin=false;
		
		//�Z�b�V�������擾�ł��Ă���ꍇ
		if(session!=null){
			//���[�U�[��񂪃Z�b�V�����ɓo�^����Ă��邩�ǂ���(���O�C���ł��Ă��邩)
			//��boolean�Ŋi�[����
			loggedin=session.getAttribute("user")!=null;
		}
		
		//���O�C���ł��Ă����
		if(loggedin){
			//�{���̃y�[�W�ֈړ�
			chain.doFilter(hreq,hres);
			
		//���O�C���ł��Ă��Ȃ��ꍇ
		}else{
			//�T�[�u���b�g�p�X���擾
			String servletPath=hreq.getServletPath();
			System.out.println("LoginCheckFilter.doFilter.else.servletPath:"+servletPath);
			
			//�擾�����T�[�u���b�g�p�X���Z�b�V�����ɒǉ�
			hreq.setAttribute("target",servletPath);
			
			//���O�C���y�[�W�֖߂�
			hreq.getRequestDispatcher("/login").forward(hreq,hres);
		}
	}
}