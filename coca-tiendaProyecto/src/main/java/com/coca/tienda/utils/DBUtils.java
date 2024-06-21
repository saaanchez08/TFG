package com.coca.tienda.utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtils {

	private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

	public static Connection conectaBBDD() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String urlDB = "jdbc:mysql://localhost:3306/rentit_db?serverTimezone=UTC";
		String user = "root";
		String pass = "root";
		Connection connection = DriverManager.getConnection(urlDB, user, pass);
		logger.info("Establecida la conexión a la BBDD");
		return connection;
	}
}
