package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbconnectSample02 {
	
	public static void main(String[] args) {
		//　データベース接続と結果取得のための変数
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		
		// ~ この中、省略
			
			// 1. ドライバのクラスをJava上で読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. DBと接続する		
			 con = DriverManager.getConnection(
					"jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
					"root",
					"MySQLRIck2331"
				);
		
			// 3. DBとやり取りする窓口　(Statementオブジェクト)の作成
				stmt = con.createStatement();
			
			// 4, 5. Select文の実行と結果を格納/代入
			String sql = "Select * FROM country LIMIT 50";
			rs = stmt.executeQuery(sql);
	// ~これより上を省略
			// 6. 結果を表示する
			while( rs.next() ) {
				// ループ内は、省略
			}
			// 6-1. データの更新を行う
			sql = "update country set Population = 105000 where Code = `ABW`";
			int count = stmt.executeUpdate(sql);
			System.out.println(count);
			
			
		
			} catch (ClassNotFoundException e) {
			System.err.println("JDBCドライバーのロードに失敗しました。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("データベースに異常が発生しました。");
			e.printStackTrace();
		} finally {
		// 7.接続を閉じる
		if( rs!= null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.err.println("ResultSetを閉じるときにエラーが発生しました。");
				e.printStackTrace();
			}
		}
		}
		if( stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Statementを閉じるときにエラーが発生しました。");
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("データベース切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}
}

	
