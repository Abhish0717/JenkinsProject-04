package in.co.rays.proj4.testmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.co.rays.proj4.util.JDBCDataSource;

public class TestJDBCDataSource {

	public static void main(String[] args) throws SQLException {

		for (int i = 1; i <= 50; i++) {

			System.out.println("Connection : " + i);

			testGet();
		}
	}

	private static void testGet() throws SQLException {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("select * from st_role where id = 1");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println("\t" + rs.getString(2));
				System.out.println("\t" + rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
}
