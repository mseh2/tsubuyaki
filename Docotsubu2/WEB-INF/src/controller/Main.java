package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.SearchLogic;
import model.User;

@WebServlet("/Main")

public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Mutter> mutterList;
		// すべてのつぶやきを取得
		GetMutterListLogic gml = new GetMutterListLogic();
		mutterList = gml.execute();
		
		//リクエストスコープに検索結果を格納
		req.setAttribute("mutterList", mutterList);

		// ログインしているか確認する
		// ログインしていたら、セッションスコープからUserインスタンスが取得でき、
		// していなかったらNull
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			// リダイレクト
			resp.sendRedirect("/DocoTsubu2/");
		}
		else {
			// 検索機能
			String search = req.getParameter("search");
			req.setAttribute("search", search);
			if (search != null && search.length() != 0) {
				SearchLogic searchLogic = new SearchLogic();
				mutterList = searchLogic.excute(search);
				req.setAttribute("mutterList", mutterList);
				
				if(mutterList.isEmpty()) {
					req.setAttribute("errMsg3", search + "についてのつぶやきはありません");
				}
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String text = req.getParameter("text");

		if (text != null && text.length() != 0) {
			// テキストがNullではなく、空文字ではなかったら

			// 誰がつぶやいたのか
			HttpSession session = req.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			// つぶやきをつぶやきリストに追加
			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic pml = new PostMutterLogic();
			pml.execute(mutter);
		} else {
			// リクエストパラメータにテキストが入っていなかったら(null,空文字)
			// リクエストスコープにエラーメッセージを保存
			req.setAttribute("errMsg", "つぶやきが入力されていません");
		}

		GetMutterListLogic gml = new GetMutterListLogic();

		List<Mutter> mutterList = gml.execute();
		req.setAttribute("mutterList", mutterList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(req, resp);
	}

}
