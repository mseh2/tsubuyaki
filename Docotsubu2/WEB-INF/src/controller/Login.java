package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//ログイン画面からのリクエスト方法がPOSTのため、doPost()
	//getだとURLにパラメータが表示されてしまうため、セキュリティ面からPostを利用する
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		//パラメータを取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		//インスタンス生成
		User user = new User(name,pass);
		//ログイン可否の判定
		boolean isLogin = LoginLogic.execute(user);
		//ログイン可ならセッションスコープに保存
		if(isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
		//ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
		
	}

}
