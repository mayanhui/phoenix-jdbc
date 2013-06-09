package com.adintellig.phoenix.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.adintellig.phoenix.jdbc.util.ConfigFactory;
import com.adintellig.phoenix.jdbc.util.ConfigProperties;
import com.adintellig.phoenix.jdbc.util.Const;

public class HBaseSQLDriver {

	static ConfigProperties config = ConfigFactory.getInstance()
			.getConfigProperties(ConfigFactory.INDEX_CONFIG_PATH);

	public static Connection getConnection() {
		Connection con;
		try {
			Class.forName(config.getProperty(Const.CONNECTION_DRIVER));
			con = DriverManager.getConnection(config
					.getProperty(Const.CONNECTION_URL));
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void query(String sql) throws Exception {
		Connection con = getConnection();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		ResultSetMetaData rsmd = rs.getMetaData();

		int columnCount = rsmd.getColumnCount();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsmd.getColumnLabel(i);
			sb.append(columnName + "\t");
		}

		if (sb.length() > 0)
			sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());

		while (rs.next()) {
			sb = new StringBuilder();
			for (int i = 1; i <= columnCount; i++) {
				sb.append(rs.getString(i) + "\t");
			}
			if (sb.length() > 0)
				sb.setLength(sb.length() - 1);

			System.out.println(sb.toString());

			// System.out.println(rs.getString("HOST"));
			// System.out.println(rs.getString("DOMAIN"));
			// System.out.println(rs.getString("FEATURE"));
			// System.out.println(rs.getString("DATE"));
			// System.out.println(rs.getString("CORE"));
			// System.out.println(rs.getString("DB"));
			// System.out.println(rs.getString("ACTIVE_VISITOR"));
		}
		con.close();
	}

	public void update(String sql) {
		
	}

	public static void main(String[] args) throws Exception {
		HBaseSQLDriver test = new HBaseSQLDriver();
		test.query("SELECT * FROM WEB_STAT WHERE ACTIVE_VISITOR > 200");
	}
}
