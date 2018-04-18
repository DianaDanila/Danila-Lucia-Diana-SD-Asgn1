package datamodel.sd.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import datamodel.sd.dao.GameDao;
import datamodel.sd.model.Game;
import datamodel.sd.model.Match;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JdbcGameDao implements GameDao {
	protected final static Logger LOGGER = Logger.getLogger(JdbcGameDao.class.getName());
	private final static String findGamebyId = "SELECT * FROM games where idgames = ?";
	private final static String findGamesbyMatch = "SELECT * FROM games where idm = ?";
	private final static String updateScoreP1Statement = "UPDATE games " + "SET scorep1 = ? WHERE idgames = ?";
	private final static String updateScoreP2Statement = "UPDATE games " + "SET scorep2 = ? WHERE idgames = ?";
	private final static String updateWinnerStatement = "UPDATE games " + "SET idw = ? WHERE idgames = ?";
	private static final String findWinnerbyPlayer = "SELECT * FROM games where idw = ?";
	private final static String getGamesStatementString = "SELECT * FROM games";

	public Game findById(int idg) {
		Game toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findGamebyId);
			findStatement.setLong(1, idg);
			rs = findStatement.executeQuery();
			rs.next();

			int p1 = rs.getInt("scorep1");
			int p2 = rs.getInt("scorep2");
			int t = rs.getInt("idm");
			int idw = rs.getInt("idw");
			toReturn = new Game();
			toReturn.setId(idg);
			toReturn.setScoreP1(p1);
			toReturn.setScoreP2(p2);
			Match x = new Match();
			x.setId(t);
			toReturn.setMatch(x);
			toReturn.setWinner(idw);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GmaessDao:findById " + e.getMessage());

		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public void findWinnerM(int idp, int idm) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findWinner = null;
		ResultSet rs = null;
		try {
			findWinner = dbConnection.prepareStatement(findWinnerbyPlayer);
			findWinner.setLong(1, idp);
			rs = findWinner.executeQuery();
			int c = 0;

			while (rs.next()) {

				c++;
				if (c == 3) {
					new JdbcMatchDao().updateWinnerM(idm, idp);
					new JdbcMatchDao().findWinnerT(idp);
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GamesDao:findWinner" + e.getMessage());
		} finally {
			ConnectionFactory.close(findWinner);
			ConnectionFactory.close(dbConnection);
		}
	}

	public List<Game> getGamesbyMatch(int idm) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement getGamesStatement = null;
		ResultSet rs = null;
		List<Game> games = new ArrayList<Game>();

		try {
			getGamesStatement = dbConnection.prepareStatement(findGamesbyMatch);
			getGamesStatement.setLong(1, idm);
			rs = getGamesStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("idgames");
				int p1 = rs.getInt("scorep1");
				int p2 = rs.getInt("scorep2");
				int idw = rs.getInt("idw");

				Game g = new Game();
				g = new Game();
				g.setId(id);
				g.setScoreP1(p1);
				g.setScoreP2(p2);
				Match match = new Match();
				match.setId(idm);
				g.setMatch(match);
				g.setWinner(idw);

				games.add(g);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GamesDao:getGamesbyMatch " + e.getMessage());
		} finally {
			ConnectionFactory.close(getGamesStatement);
			ConnectionFactory.close(dbConnection);
		}
		return games;
	}

	public void updateScore(int idg, int side) {// =1 p1, =2 p2

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		Game g = new JdbcGameDao().findById(idg);
		int idw = g.getWinner();

		if (idw == 0) {

			try {
				int s = 0;
				int os = 0;
				int idm;

				if (side == 1) {

					updateStatement = dbConnection.prepareStatement(updateScoreP1Statement);
					s = g.getScoreP1();// score on side
					os = g.getScoreP2();// score on other side
					s++;
					g.setScoreP1(s);
					updateStatement.setLong(1, s);
					updateStatement.setLong(2, idg);
					updateStatement.executeUpdate();

				} else if (side == 2) {

					updateStatement = dbConnection.prepareStatement(updateScoreP2Statement);
					s = g.getScoreP2();
					os = g.getScoreP1();
					s++;
					g.setScoreP2(s);
					updateStatement.setLong(1, s);
					updateStatement.setLong(2, idg);
					updateStatement.executeUpdate();

				} else {
					System.out.println("invalid side to increment");
				}
				if (s == 11) {
					if (os < 10) {
						idm = g.getMatch().getId();
						PreparedStatement updateWinner = null;
						updateWinner = dbConnection.prepareStatement(updateWinnerStatement);
						idw = new JdbcMatchDao().getPlayerByGameSide(idm, side);
						g.setWinner(idw);
						;
						updateWinner.setLong(1, idw);
						updateWinner.setLong(2, idg);
						updateWinner.executeUpdate();
						new JdbcGameDao().findWinnerM(idw, idm);
					}
				} else {
					if (s - os == 2) {
						idm = g.getMatch().getId();
						PreparedStatement updateWinner = null;
						updateWinner = dbConnection.prepareStatement(updateWinnerStatement);
						idw = new JdbcMatchDao().getPlayerByGameSide(idm, side);
						g.setWinner(idw);
						;
						updateWinner.setLong(1, idw);
						updateWinner.setLong(2, idg);
						updateWinner.executeUpdate();
						new JdbcGameDao().findWinnerM(idw, idm);
					}
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "GamesDao:updateScore " + e.getMessage());
			} finally {
				ConnectionFactory.close(updateStatement);
				ConnectionFactory.close(dbConnection);
			}
		} else {
			System.out.println("Invalid update of Score");
		}

	}

	public void updateScoreByPlayer(int idg, int side, int l) {// =1 p1, =2 p2

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		Game g = new JdbcGameDao().findById(idg);
		int idw = g.getWinner();

		if (l == new JdbcMatchDao().getPlayerByGameSide(g.getMatch().getId(), 1)
				|| l == new JdbcMatchDao().getPlayerByGameSide(g.getMatch().getId(), 2)) {
			if (idw == 0) {

				try {
					int s = 0;
					int os = 0;
					int idm;

					if (side == 1) {

						updateStatement = dbConnection.prepareStatement(updateScoreP1Statement);
						s = g.getScoreP1();// score on side
						os = g.getScoreP2();// score on other side
						s++;
						g.setScoreP1(s);
						updateStatement.setLong(1, s);
						updateStatement.setLong(2, idg);
						updateStatement.executeUpdate();

					} else if (side == 2) {

						updateStatement = dbConnection.prepareStatement(updateScoreP2Statement);
						s = g.getScoreP2();
						os = g.getScoreP1();
						s++;
						g.setScoreP2(s);
						updateStatement.setLong(1, s);
						updateStatement.setLong(2, idg);
						updateStatement.executeUpdate();

					} else {
						System.out.println("invalid side to increment");
					}
					if (s == 11) {
						if (os < 10) {
							idm = g.getMatch().getId();
							PreparedStatement updateWinner = null;
							updateWinner = dbConnection.prepareStatement(updateWinnerStatement);
							idw = new JdbcMatchDao().getPlayerByGameSide(idm, side);
							g.setWinner(idw);
							updateWinner.setLong(1, idw);
							updateWinner.setLong(2, idg);
							updateWinner.executeUpdate();
							new JdbcGameDao().findWinnerM(idw, idm);
						}
					} else {
						if (s - os == 2) {
							idm = g.getMatch().getId();
							PreparedStatement updateWinner = null;
							updateWinner = dbConnection.prepareStatement(updateWinnerStatement);
							idw = new JdbcMatchDao().getPlayerByGameSide(idm, side);
							g.setWinner(idw);
							updateWinner.setLong(1, idw);
							updateWinner.setLong(2, idg);
							updateWinner.executeUpdate();
							new JdbcGameDao().findWinnerM(idw, idm);
						}
					}
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING, "GamesDao:updateScore " + e.getMessage());
				} finally {
					ConnectionFactory.close(updateStatement);
					ConnectionFactory.close(dbConnection);
				}
			}
		} else {
			System.out.println("Invalid update of Score");
		}

	}

	public ObservableList<Game> getGames() {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement getGamesStatement = null;
		ResultSet rs = null;
		ObservableList<Game> games = FXCollections.observableArrayList();

		try {
			getGamesStatement = dbConnection.prepareStatement(getGamesStatementString);
			rs = getGamesStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("idgames");
				int p1 = rs.getInt("scorep1");
				int p2 = rs.getInt("scorep2");
				int idm = rs.getInt("idm");
				int idw = rs.getInt("idw");

				Game g = new Game();
				g = new Game();
				g.setId(id);
				g.setScoreP1(p1);
				g.setScoreP2(p2);
				Match match = new Match();
				match.setId(idm);
				g.setMatch(match);
				g.setWinner(idw);

				games.add(g);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GameDAO:getGames " + e.getMessage());
		} finally {
			ConnectionFactory.close(getGamesStatement);
			ConnectionFactory.close(dbConnection);
		}
		return games;
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Game objectToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Game objectToUpdate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Game objectToCreate) {
		// TODO Auto-generated method stub

	}

}
