package systems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class update {

	public static boolean updateCheck(String id) throws SQLException {

		//結果を返すためのresult
		boolean result = false;

		//SQLの結果を取得するための変数
		ResultSet rs = null ;

		//画面から入力された値をint型で受け取る

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
			String sql = "SELECT * FROM shoes_table "
					+ "WHERE "
					+ "id_shoes = ?";

			// Statementの生成
			PreparedStatement stmt = con.prepareStatement(sql);

			//価をセット
			stmt.setInt(1, id1);

			// SQLの実行
			rs = stmt.executeQuery();

			//IDがあるか判定
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
		return result;
	}


	//実行メソッド

	public static void updateDo(
			String ID_shoes,
			String nm_shoes,
			String manufacture,
			String color,
			String cut,
			String fabric,
			String style,
			String category,
			String gender,
			String fitting,
			String rarity,
			String country,
			String price
			) {


		//もらった値を入れなおす

		int id1 = Integer.parseInt(ID_shoes);
		int price1 = Integer.parseInt(price);

		String nm_shoes1 = nm_shoes;
		String manufacture1 = manufacture;
		String color1 = color;
		String cut1 = cut;
		String fabric1 = fabric;
		String style1 = style;
		String category1 = category;
		String gender1 = gender;
		String fitting1 = fitting;
		String rarity1 = rarity;
		String country1 = country;

		try {
			// JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connectionの生成
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/recommendedshoes?serverTimezone=JST" // データベース
					,"resh" // ユーザ
					,"Wt2_.Cn!" // パスワード
					);

			//updateのSQL文
			String sql = "UPDATE shoes_table SET id_shoes = ?,nm_shoes = ?,manufacture = ?,color = ?,cut = ?,fabric = ?,style = ?,category = ?,gender = ?,fitting = ?,rarity = ?,country = ?,price = ? WHERE id_shoes = ?";

			// Statementの生成
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id1);
			stmt.setString(2, nm_shoes1);
			stmt.setString(3, manufacture1);
			stmt.setString(4, color1);
			stmt.setString(5, cut1);
			stmt.setString(6, fabric1);
			stmt.setString(7, style1);
			stmt.setString(8, category1);
			stmt.setString(9, gender1);
			stmt.setString(10, fitting1);
			stmt.setString(11, rarity1);
			stmt.setString(12, country1);
			stmt.setInt(13, price1);
			stmt.setInt(14, id1);

			// SQLの実行
			int num = stmt.executeUpdate();

			// Statement、Connectionの切断
			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
