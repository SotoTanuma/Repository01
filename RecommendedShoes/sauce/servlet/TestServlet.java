package servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import systems.login;
import systems.read;
import systems.uSearch;
import systems.update;

public class TestServlet extends HttpServlet {
	protected void service(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException {
		req.setCharacterEncoding("UTF-8");

		// 1/10 Gitテスト

		//実行する機能と次に表示させるページを指定する



		//jspで入力された値を受け取る

		//管理者情報

		String id = null;
		id = req.getParameter("id");

		String name = null;
		name = req.getParameter("name");

		String mail = null;
		mail = req.getParameter("mail");

		String pass = null;
		pass = req.getParameter("pass");

		//シューズ情報
		String id_shoes = null;
		id_shoes = req.getParameter("id_shoes");

		String nm_shoes = null;
		nm_shoes = req.getParameter("nm_shoes");

		String manufacture = null;
		manufacture = req.getParameter("manufacture");

		String color = null;
		color = req.getParameter("color");

		String cut = null;
		cut = req.getParameter("cut");

		String fabric = null;
		fabric = req.getParameter("fabric");

		String style = null;
		style = req.getParameter("style");

		String category = null;
		category = req.getParameter("category");

		String gender = null;
		gender = req.getParameter("gender");

		String fitting = null;
		fitting = req.getParameter("fitting");

		String rarity = null;
		rarity = req.getParameter("rarity");

		String country = null;
		country = req.getParameter("country");

		String price = null;
		price = req.getParameter("price");


		//実行機能の判定(login,create(新規登録),read(検索),update(更新),delete(削除))
		String check = null;
		check = req.getParameter("check");


		//次に表示するページの指定
		String next = null;


		//ログイン
		//管理者DBを検索し、mail,passが一致するデータがあればtrue

		if(check.equals("login")) {
			try {
				boolean result = false;
				result = login.login(name,mail,pass);

				if(result == true) {
					next = "menu";
				}
				if(result == false) {
					next =  "login";
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			req.getRequestDispatcher(next + ".jsp").forward(req, res);
		}


		//CREATE
		//createメソッドに値を渡し、データ登録

		if(check.equals("create")) {

			try {
				int id1 = Integer.parseInt(id_shoes);
				int price1 = Integer.parseInt(price);

				systems.create.create(
						id_shoes,
						nm_shoes,
						manufacture,
						color,
						cut,
						fabric,
						style,
						category,
						gender,
						fitting,
						rarity,
						country,
						price
						);

				next = "createResult";

			}catch(Exception e){
				next = "create";
			}

			req.getRequestDispatcher(next + ".jsp").forward(req, res);
		}



		//READ
		//IDで検索

		if(check.equals("read")) {
			try {
				boolean result = false;
				result = read.read(id);

				if (result == true) {
					String[] array = read.readDo(id);
					req.setAttribute("array",array);
					next = "readResult";
				}
				if (result == false) {
					next = "read";
				}

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			req.getRequestDispatcher(next + ".jsp").forward(req, res);
		}



		//UPDATE

		//①IDを検索し、あればupdateInput 無ければupdateへ
		//②updateInputで入力された値を受け取り、更新

		//①
		if(check.equals("update1")) {
			try {
				int id1 = Integer.parseInt(id);
				boolean result = false;
				result = update.updateCheck(id);

				if(result == true) {
					next = "updateInput";
				}
				if(result == false) {
					next =  "update";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (NumberFormatException e) {
				next =  "update";
			}
			req.getRequestDispatcher(next + ".jsp").forward(req, res);
		}

		//②
		if(check.equals("update2")) {
			try {
				update.updateDo(
						id_shoes,
						nm_shoes,
						manufacture,
						color,
						cut,
						fabric,
						style,
						category,
						gender,
						fitting,
						rarity,
						country,
						price
						);

				next = "updateResult";

			}catch(Exception e) {
				next = "updateInput";
			}

			req.getRequestDispatcher(next + ".jsp").forward(req, res);
		}



		//DELETE
		if(check.equals("delete")) {

			try {
				int id1 = Integer.parseInt(id_shoes);
				systems.delete.delete(id_shoes);
				next = "deleteResult";
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(NumberFormatException e){
				next = "delete";
			}

			req.getRequestDispatcher(next + ".jsp").forward(req, res);

		}

		//ユーザの検索機能

		if(check.equals("search")) {

			//選択された値を受け取る

			String s_color = req.getParameter("color");
			String s_manufacture = req.getParameter("manufacture");
			String s_category = req.getParameter("category");
			String s_gender = req.getParameter("gender");

			//確認
			//System.out.println(s_color + s_manufacture + s_category + s_gender);

			//検索結果を受け取る
			String[] array = uSearch.uSearch(s_color,s_manufacture,s_category,s_gender);

			String s_id = array[0];

			if (s_id == null) {
				s_id = "noResult";
			}

			next = "id_" + s_id;
			//System.out.println(next);

			req.getRequestDispatcher(next + ".jsp").forward(req, res);

		}
	}
}

