package systems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class read {

	public static boolean read(String id) throws SQLException {

		//結果を返す変数
		boolean result = false;

		//SQLの結果を取得するための変数
		ResultSet rs = null;

		//DB接続
		try {
			// JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connectionの生成
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/recommendedshoes?serverTimezone=JST" // データベース
					, "s-tanuma" // ユーザ
					, "soto1107" // パスワード
			);

			String sql = "SELECT * FROM shoes_table "
					+ "WHERE "
					+ "id_shoes = ? ";

			// Statementの生成
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, id);

			// SQLの実行
			rs = stmt.executeQuery();

			// 一件でもヒットすればture,無ければfalce
			if (rs.next()) {
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




	public static String[] readDo(String id) {

		int id1 = Integer.parseInt(id);

		try {

			// JDBCドライバの読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connectionの生成
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/recommendedshoes?serverTimezone=JST" // データベース
					, "s-tanuma" // ユーザ
					, "soto1107" // パスワード
			);

			String sql = "SELECT * FROM shoes_table WHERE id_shoes = ?";

			// Statementの生成
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id1);

			// SQLの実行
			ResultSet rs = stmt.executeQuery();

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

				//System.out.println(mf);

				String[] array;
				array = new String[13];

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

				return array;
			}

			stmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
