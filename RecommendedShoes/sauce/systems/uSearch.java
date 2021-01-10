package systems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class uSearch {

	public static String[] uSearch(String s_color, String s_manufacture, String s_category, String s_gender) {


		//System.out.println(s_color + s_manufacture + s_category + s_gender);


		//結果を返す配列
		String[] array;
		array = new String[13];

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
			String sql = "SELECT * FROM shoes_table WHERE color = ? AND manufacture = ? AND category = ? AND gender = ?";

			// Statementの生成
			PreparedStatement stmt = con.prepareStatement(sql);

			//SQLに代入
			stmt.setString(1, s_color);
			stmt.setString(2, s_manufacture);
			stmt.setString(3, s_category);
			stmt.setString(4, s_gender);

			// SQLの実行
			ResultSet rs = stmt.executeQuery();

			//カラムに合わせたフィールド作成
			String shoesId;
			String shoesName;
			String mf;
			String cl;
			String ct;
			String fb;
			String st;
			String ca;
			String gn;
			String ft;
			String ra;
			String cn;
			String pr;

			while (rs.next()) {

				shoesId = rs.getString("id_shoes");
				shoesName = rs.getString("nm_shoes");
				mf = rs.getString("manufacture");
				cl = rs.getString("color");
				ct = rs.getString("cut");
				fb = rs.getString("fabric");
				st = rs.getString("style");
				ca = rs.getString("category");
				gn = rs.getString("gender");
				ft = rs.getString("fitting");
				ra = rs.getString("rarity");
				cn = rs.getString("country");
				pr = rs.getString("price");

				//arrayに格納
				array[0] = shoesId;
				array[1] = shoesName;
				array[2] = mf;
				array[3] = cl;
				array[4] = ct;
				array[5] = fb;
				array[6] = st;
				array[7] = ca;
				array[8] = gn;
				array[9] = ft;
				array[10] = ra;
				array[11] = cn;
				array[12] = pr;

			}



			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}
}



