package datamodel.sd.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import datamodel.sd.dao.UserDao;
import datamodel.sd.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JdbcUserDao implements UserDao {

	protected static final Logger LOGGER = Logger.getLogger(JdbcUserDao.class.getName());
	private final static String findStatementString = "SELECT * FROM users where idusers = ?";
	private final static String findPassString = "SELECT * FROM users where mail = ?";
	private static final String insertAccountString = "INSERT INTO users (user,mail,password,admin)"
			+ " VALUES (?,?,?,?)";
	private final static String updatePassStatement = "UPDATE users " + "SET password = ? WHERE user= ?";
	private final static String deleteUserString = "DELETE from users where idusers = ?";
	private final static String getUsersStatementString = "SELECT * FROM users";

	public User findById(int idu) {
		User user = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idu);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("user");
			// String mail = rs.getString("mail");
			String pass = rs.getString("password");

			user = new User();
			user.setId(idu);
			user.setName(name);
			user.setPassword(pass);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UsersDao:findById" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

		return user;
	}

	public int findByMail(String mailu, String p) {
		User user = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatementM = null;
		ResultSet rs = null;
		int id = 0;
		try {
			findStatementM = dbConnection.prepareStatement(findPassString);
			findStatementM.setString(1, mailu);
			rs = findStatementM.executeQuery();

			if (!rs.next()) {
				System.out.println("Invalid user mail");

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText(null);
				alert.setContentText("Invalid User Mail!");
				alert.showAndWait();
				return 0;
			} else {
				id = rs.getInt("idusers");
				String name = rs.getString("user");
				String pass = rs.getString("password");
				boolean a = rs.getBoolean(1);
				if (p.equals(pass)) {
					user = new User();
					user.setName(name);
					user.setPassword(pass);
					user.setAdmin(a);
				} else {
					System.out.println("invalid password");

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("Invalid Password!");
					alert.showAndWait();
					return 0;
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UsersDao:findByMail" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatementM);
			ConnectionFactory.close(dbConnection);
		}

		return id;
	}

	public boolean isAdmin(String mailu) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatementM = null;
		ResultSet rs = null;
		try {
			findStatementM = dbConnection.prepareStatement(findPassString);
			findStatementM.setString(1, mailu);
			rs = findStatementM.executeQuery();
			rs.next();

			boolean a = rs.getBoolean("admin");
			if (a) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UsersDao:isAdmin" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatementM);
			ConnectionFactory.close(dbConnection);
		}

		return true;
	}

	public void insertU(User u) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertAccount = null;

		try {
			insertAccount = dbConnection.prepareStatement(insertAccountString);
			insertAccount.setString(1, u.getName());
			insertAccount.setString(3, u.getPassword());
			insertAccount.setInt(4, 0);
			insertAccount.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertAccount);
			ConnectionFactory.close(dbConnection);
		}
	}

	public void updatePass(String user, String pass) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updatePass = null;
		ResultSet rs = null;
		try {
			updatePass = dbConnection.prepareStatement(updatePassStatement);
			updatePass.setString(1, pass);
			updatePass.setString(2, user);
			updatePass.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UsersDao:updatePass " + e.getMessage());

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updatePass);
			ConnectionFactory.close(dbConnection);
		}
	}

	public void deleteById(int idu) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		ResultSet rs = null;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteUserString);
			deleteStatement.setLong(1, idu);
			deleteStatement.execute();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:deleteById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public ObservableList<User> getUsers() {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement getUsersStatement = null;
		ResultSet rs = null;
		ObservableList<User> users = FXCollections.observableArrayList();

		try {
			getUsersStatement = dbConnection.prepareStatement(getUsersStatementString);
			rs = getUsersStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("idusers");
				String u = rs.getString("user");
				String p = rs.getString("password");
				boolean a = rs.getBoolean("admin");
				User user = new User();
				user.setId(id);
				user.setName(u);
				user.setPassword(p);
				user.setAdmin(a);

				users.add(user);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:getUsers " + e.getMessage());
		} finally {
			ConnectionFactory.close(getUsersStatement);
			ConnectionFactory.close(dbConnection);
		}
		return users;
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User objectToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User objectToUpdate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(User objectToCreate) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
