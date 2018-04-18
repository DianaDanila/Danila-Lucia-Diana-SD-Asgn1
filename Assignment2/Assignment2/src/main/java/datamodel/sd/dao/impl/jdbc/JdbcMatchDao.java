package datamodel.sd.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import datamodel.sd.dao.MatchDao;
import datamodel.sd.model.Game;
import datamodel.sd.model.Match;
import datamodel.sd.model.Tournament;
import datamodel.sd.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JdbcMatchDao implements MatchDao {
	protected static final Logger LOGGER = Logger.getLogger(JdbcMatchDao.class.getName());

	private static final String getMatchesStatementString = "SELECT * FROM matches";
	private static final String findMatchbyGame = "SELECT * FROM matches where idmatch = ?";

	private final static String updateWinnerStatement = "UPDATE matches " + "SET idw = ? WHERE idmatch= ?";
	private static final String findWinnerbyNrofWins = "SELECT * FROM matches where idw = ?";

	private static final String findMatchbyPlayer1 = "SELECT * FROM matches where idp1 = ?";
	private static final String findMatchbyPlayer2 = "SELECT * FROM matches where idp2 = ?";

	public ObservableList<Match> getMatchs() {

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
				User p1 = new JdbcUserDao().findById(idp1);
				User p2 = new JdbcUserDao().findById(idp2);
				Tournament t = new JdbcTournamentDao().findById(idt);
				Set<Game> g = new HashSet<Game>();
				g.addAll(new JdbcGameDao().getGamesbyMatch(idmatch));
				int idw = rs.getInt("idw");

				Match m = new Match();
				m.setId(idmatch);
				m.setPlayer1(p1);
				m.setPlayer2(p2);
				m.setTournament(t);
				m.setIdw(idw);
				m.setGames(g);
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

	public void updateWinnerM(int idm, int idp) {

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

	public void findWinnerT(int idp) {
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
					new JdbcTournamentDao().updateWinnerT(t, idp);
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
	public int findMatchbyP(int idp) {

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

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Match objectToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Match objectToUpdate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Match objectToCreate) {
		// TODO Auto-generated method stub

	}

	@Override
	public Match findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerByGameSide(int idm, int side) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement getMatchStatement = null;
		ResultSet rs = null;
		int id = 0;
		try {
			getMatchStatement = dbConnection.prepareStatement(findMatchbyGame);
			getMatchStatement.setLong(1, idm);
			rs = getMatchStatement.executeQuery();
			rs.next();

			if (side == 1) {
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

}
