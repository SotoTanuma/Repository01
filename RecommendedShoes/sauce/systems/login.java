package systems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {

	public static boolean login(String name, String mail, String pass) throws SQLException {

		//結果を返す変数
		boolean result = false;

		//SQLの結果を取得するための変数
		ResultSet rs = null ;

		//画面から入力された値を受け取る
		String name1 = null;
		name1 = name;
		String mail1 = null;
		mail1 = mail;
		String pass1 = null;
		pass1 = pass;

		//DB接続
		try {
			// JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connectionの生成
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/recommendedshoes?serverTimezone=JST" // データベース
					,"s-tanuma" // ユーザ
					,"soto1107" // パスワード
					);

			String sql = "SELECT * FROM managers_table "
					+ "WHERE "
					+ "mail_address = ? "
					+ "AND "
					+ "password = ?";

			// Statementの生成
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, mail1);
			stmt.setString(2, pass1);

			// SQLの実行
			rs = stmt.executeQuery();

			// 一件でもヒットすればture,無ければfalce
			if(rs.next()) {
				result = true;
			} else {
				result = false;
			}

			// Statement、Connectionの切断
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//検索結果を返す
		return result;
	}
}
