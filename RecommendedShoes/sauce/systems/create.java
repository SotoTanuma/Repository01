package systems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class create {

	public static void create(
			String id_shoes,
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

		//値の受け取り
		int id1;
		int price1;
		try {
			id1 = Integer.parseInt(id_shoes);
			price1 = Integer.parseInt(price);
		}catch(Exception e) {
			id1 = 1;
			price1 = 0;
		}
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

			//SQLの作成
			String sql = "INSERT INTO shoes_table VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// Statementの生成
			PreparedStatement stmt = con.prepareStatement(sql);

			//SQLに代入
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

			// SQLの実行
			int num = stmt.executeUpdate();

			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}

