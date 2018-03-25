package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.DataAccessLayer.ConnectionFactory;
import dao.TournamentsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pingpong.Game;
import pingpong.Match;
import pingpong.Tournament;
import pingpong.User;

public class MatchesDao {

	protected static final Logger LOGGER = Logger.getLogger(MatchesDao.class.getName());
	private static final String getMatchesStatementString = "SELECT * FROM matches";
	private static final String findMatchbyGame = "SELECT * FROM matches where idmatch = ?";

	private final static String updateWinnerStatement = "UPDATE matches " + "SET idw = ? WHERE idmatch= ?";
	private static final String findWinnerbyNrofWins = "SELECT * FROM matches where idw = ?";

	private static final String findMatchbyPlayer1 = "SELECT * FROM matches where idp1 = ?";
	private static final String findMatchbyPlayer2 = "SELECT * FROM matches where idp2 = ?";

	public static ObservableList<Match> getMatches() {

		ObservableList<Match> matches = FXCollections.observableArrayList();
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement getMatches = null;
		ResultSet rs = null;
		try {
			getMatches = dbConnection.prepareStatement(getMatchesStatementString);
			rs = getMatches.executeQuery();
			while (rs.next()) {
				int idmatch = rs.getInt("idmatch");
				int idp1 = rs.getInt("idp1");
				int idp2 = rs.getInt("idp2");
				int idt = rs.getInt("idt");
				User p1 = UsersDao.findById(idp1);
				User p2 = UsersDao.findById(idp2);
				Tournament t = TournamentsDao.findById(idt);
				List<Game> g = GamesDao.getGamesbyMatch(idmatch);
				int idw = rs.getInt("ïdw");

				Match m = new Match(idmatch, p1, p2, t, idw, g);
				matches.add(m);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "MatchesDao:getMatches" + e.getMessage());
		} finally {
			ConnectionFactory.close(getMatches);
			ConnectionFactory.close(dbConnection);
		}

		return matches;
	}

	public static int getPlayerbyGameSide(int idm, int s) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement getMatchStatement = null;
		ResultSet rs = null;
		int id = 0;
		try {
			getMatchStatement = dbConnection.prepareStatement(findMatchbyGame);
			getMatchStatement.setLong(1, idm);
			rs = getMatchStatement.executeQuery();
			rs.next();

			if (s == 1) {
				id = rs.getInt("idp1");
			} else {
				id = rs.getInt("idp2");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "MacthesDao:findById " + e.getMessage());

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(getMatchStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}

	public static void updateWinnerM(int idm, int idp) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateWinner = null;
		ResultSet rs = null;
		try {
			updateWinner = dbConnection.prepareStatement(updateWinnerStatement);
			updateWinner.setLong(1, idp);
			updateWinner.setLong(2, idm);
			updateWinner.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "MacthesDao:updateWinner " + e.getMessage());

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updateWinner);
			ConnectionFactory.close(dbConnection);
		}
	}

	public static void findWinnerT(int idp) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findWinner = null;
		ResultSet rs = null;
		try {
			findWinner = dbConnection.prepareStatement(findWinnerbyNrofWins);
			findWinner.setLong(1, idp);
			rs = findWinner.executeQuery();

			int c = 0;

			while (rs.next()) {
				int t = rs.getInt("idt");
				c++;
				if (c == 3) {// if wins 3 matches- wins the tournamnet
					TournamentsDao.updateWinnerT(t, idp);
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "MatchesDao:findWinner" + e.getMessage());
		} finally {
			ConnectionFactory.close(findWinner);
			ConnectionFactory.close(dbConnection);
		}
	}

	@SuppressWarnings("resource")
	public static int findMatchbyP(int idp) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement getMatchStatement = null;
		ResultSet rs = null;
		int id = 0;
		try {
			getMatchStatement = dbConnection.prepareStatement(findMatchbyPlayer1);
			getMatchStatement.setLong(1, idp);
			rs = getMatchStatement.executeQuery();
			int idw = 0;
			if (!rs.next()) {
				getMatchStatement = dbConnection.prepareStatement(findMatchbyPlayer2);
				getMatchStatement.setLong(1, idp);
				rs = getMatchStatement.executeQuery();
				if (idw == 0) {
					id = rs.getInt("idmatch");
				}
			} else {
				if (idw == 0) {
					id = rs.getInt("idmatch");
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "MacthesDao:findById " + e.getMessage());

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(getMatchStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}
}
