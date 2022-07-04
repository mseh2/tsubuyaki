package model;

public class LoginLogic {
	public static boolean execute(User user) {
		//引数：ログイン画面で入力されたユーザー情報
		//戻り値：ログイン成功→true　ログイン失敗→false
		if(user.getPass().equals("1234")) {
			//もしもPassが1234だったらログイン成功
			return true;
		}
		//それ以外はログイン失敗
		return false;
	}
}
