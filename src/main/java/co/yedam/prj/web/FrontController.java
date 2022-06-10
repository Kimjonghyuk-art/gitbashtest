package co.yedam.prj.web;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prj.common.Command;
import co.yedam.prj.home.command.HomeCommand;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Command> map = new HashMap<String,Command>();

	@Override
	public void init(ServletConfig config) throws ServletException {
	
	map.put("/home.do", new HomeCommand());
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String page = uri.substring(contextPath.length());
		Command command = map.get(page);
		System.out.println("페이지"+page);
		String viewPage = command.exec(req, resp);
		System.out.println("부페이지"+viewPage);
		//if(!viewPage.endsWith(".do")) {
		//	viewPage = "/WEB-INF/views/" + viewPage + ".jsp" ;
			viewPage = viewPage + ".tiles";
		//}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, resp);
		
	}
	
	
	
}
