package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;
import ru.stqa.pft.adressbook.model.Groups;

import java.sql.*;

public class DbConectionTest {

	@Test
	public void testDbConnection() {
		Connection conn = null;
		try {
			conn =
							DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
											"user=root&password=");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select group_id, group_name,group_header,group_footer from group_list");
			Groups groups = new Groups();
			while (rs.next()) {
				groups.add(new GroupDate()
								.withId(rs.getInt("group_id"))
								.withName(rs.getString("group_name"))
								.withHeader(rs.getString("group_header"))
								.withFooter(rs.getString("group_footer")));
			}
			rs.close();
			st.close();
			conn.close();
			System.out.println(groups);


			// Do something with the Connection

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
