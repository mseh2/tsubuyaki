package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MutterDAO {

	// 接続先のDB情報を設定
	private final String JDBC_URL = "jdbc:postgresql:dokotsubu";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "postgres";

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driverが配置されていません");
		}
	}

	// すべてのつぶやきを取得
	public List<Mutter> findAll() {
		List<Mutter> mutterList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {

			String sql = "SELECT * FROM mutter ORDER BY id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("name");
				String text = rs.getString("text");
				Mutter mutter = new Mutter(id, userName, text);
				mutterList.add(mutter);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}

	// つぶやきをデータベースに格納する
	public boolean create(Mutter mutter) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
		) {
			String sql = "INSERT INTO mutter(name,text) VALUES(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, mutter.getUserName());
			pstmt.setString(2, mutter.getText());

			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 検索テキストボックスにいれた文字がふくまれるつぶやきのみ表示
	public List<Mutter> findContainText(String search) {
		List<Mutter> mutterList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);) {

			String sql = "SELECT * FROM mutter WHERE text LIKE ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "%" + search + "%");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("name");
				String text = rs.getString("text");
				Mutter mutter = new Mutter(id, userName, text);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return mutterList;
	}

}
