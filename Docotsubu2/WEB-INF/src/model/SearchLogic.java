package model;

import java.util.List;

public class SearchLogic {
	
	public List<Mutter> excute(String search) {
		//検索したい文字列を渡して、検索結果をリストに格納し、リターンする
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findContainText(search);
		return mutterList;
	}

}
