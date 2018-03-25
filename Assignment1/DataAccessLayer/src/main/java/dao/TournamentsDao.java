package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.DataAccessLayer.ConnectionFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pingpong.Tournament;

public class TournamentsDao {
	protected static final Logger LOGGER = Logger.getLogger(TournamentsDao.class.getName());
	private final static String findStatementString = "SELECT * FROM tournaments where idtournament = ?";
	private final static String getTournamentsStatementString = "SELECT * FROM tournaments";
	private final static String updateWinnerStatement = "UPDATE tournaments " + "SET winner = ? WHERE idtournament= ?";
	private final static String updateStatusStatement = "UPDATE tournaments " + "SET status = ? WHERE idtournament= ?";
	private final static String updateNameStatement = "UPDATE tournaments " + "SET name = ? WHERE idtournament= ?";
	private static final String insertTournamentString = "INSERT INTO tournaments (name,status,winner)"
			+ " VALUES (?,?,?)";
	private final static String deleteTString = "DELETE from tournaments where idtournament = ?";

	public static Tournament findById(int idt) {
		Tournament toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idt);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String status = rs.getString("status");
			int idw = rs.getInt("winner");
			toReturn = new Tournament(idt, name, status, idw);
			System.out.println("tournament found with id: " + idt + " " + name + " " + status);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TournamentsDao:findById " + e.getMessage());

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static ObservableList<Tournament> getTournamentss() {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement getTournamentsStatement = null;
		ResultSet rs = null;
		ObservableList<Tournament> tournaments = FXCollections.observableArrayList();

		try {
			getTournamentsStatement = dbConnection.prepareStatement(getTournamentsStatementString);
			rs = getTournamentsStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("idtournament");
				String name = rs.getString("name");
				String status = rs.getString("status");
				int idw = rs.getInt("winner");

				Tournament tournament = new Tournament(id, name, status, idw);

				tournaments.add(tournament);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TournamentDAO:getTournaments " + e.getMessage());
		} finally {
			ConnectionFactory.close(getTournamentsStatement);
			ConnectionFactory.close(dbConnection);
		}
		return tournaments;
	}

	public static void updateWinnerT(int idt, int idp) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateWinner = null;
		PreparedStatement updateStatus = null;
		ResultSet rs = null;
		try {
			updateWinner = dbConnection.prepareStatement(updateWinnerStatement);
			updateWinner.setLong(1, idp);
			updateWinner.setLong(2, idt);
			updateWinner.executeUpdate();

			updateStatus = dbConnection.prepareStatement(updateStatusStatement);
			updateStatus.setString(1, "ended");
			updateStatus.setLong(2, idt);
			updateStatus.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TournamnetsDao:updateWinner " + e.getMessage());

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updateWinner);
			ConnectionFactory.close(updateStatus);
			ConnectionFactory.close(dbConnection);
		}
	}

	public static void updateName(int idt, String name) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateName = null;
		try {
			updateName = dbConnection.prepareStatement(updateNameStatement);
			updateName.setString(1, name);
			updateName.setLong(2, idt);
			updateName.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TournamnetsDao:updateName " + e.getMessage());

		} finally {
			ConnectionFactory.close(updateName);
			ConnectionFactory.close(dbConnection);
		}
	}

	public static void insert(Tournament t) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertTournament = null;

		try {
			insertTournament = dbConnection.prepareStatement(insertTournamentString);
			insertTournament.setString(1, t.getName());
			insertTournament.setString(2, t.getStatus());
			insertTournament.setInt(3, t.getIdwinner());
			insertTournament.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TournamentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertTournament);
			ConnectionFactory.close(dbConnection);
		}
	}

	public static void deleteById(int idt) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		ResultSet rs = null;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteTString);
			deleteStatement.setLong(1, idt);
			deleteStatement.execute();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TournamentDAO:deleteById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
