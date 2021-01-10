package systems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class delete {

	public static void delete(String id) throws SQLException {



		int id1 = Integer.parseInt(id);



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

			//更新できるかチェック(IDがあるかどうか)
			String sql = "DELETE FROM shoes_table WHERE id_shoes = ?";

			// Statementの生成
			PreparedStatement stmt = con.prepareStatement(sql);

			//価をセット
			stmt.setInt(1, id1);

			// SQLの実行
			int num = stmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}