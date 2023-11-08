package com.qsp.gms.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.gms.model.Product;

public class ShopController {
	
	public int addProduct(int id, String name, int price, int quantity, boolean availability) {
		Connection connection = null;
		int rowAffected = 0;
		try {

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);

			String sql = "INSERT INTO product VALUES(?,?,?,?,?);";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quantity);
			prepareStatement.setBoolean(5, availability);
			rowAffected = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rowAffected;
	}

	public void addMultipleProduct(ArrayList<Product> products) {
		Connection connection = null;
		try {

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);

			String sql = "INSERT INTO product VALUES(?,?,?,?,?);";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);

			for (Product product : products) {
				prepareStatement.setInt(1, product.getP_id());
				prepareStatement.setString(2, product.getP_name());
				prepareStatement.setInt(3, product.getP_price());
				prepareStatement.setInt(4, product.getP_quantity());
				prepareStatement.setBoolean(5, product.isP_availabilityl());
				prepareStatement.addBatch();
			}
			prepareStatement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ResultSet fetchProduct(int id) {
		Connection connection = null;
		ResultSet resultSet = null;
		try {

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);

			String sql = "SELECT * FROM product WHERE p_id=?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}
	
	public int removeProduct(int id) {
		Connection connection = null;
		int idDeleted =0;
		try {

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);

			String sql = "DELETE FROM product WHERE p_id=?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			idDeleted = prepareStatement.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return idDeleted;
	}
	
	public int updateProductName(int id, String name) {
		Connection connection = null;
		int idUpdate =0;
		try {

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);

			String sql = "UPDATE product SET p_name=? WHERE p_id=?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setInt(2, id);
			
			idUpdate = prepareStatement.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return idUpdate;
		
	}

}
