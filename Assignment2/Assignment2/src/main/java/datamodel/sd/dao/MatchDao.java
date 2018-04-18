package datamodel.sd.dao;

import java.util.List;

import datamodel.sd.model.Match;

public interface MatchDao extends Dao<Match> {

	@Override
	Match findById(int id);

	@Override
	void delete(Match objectToDelete);

	@Override
	void update(Match objectToUpdate);

	@Override
	void insert(Match objectToCreate);
	
	public int getPlayerByGameSide(int idm, int side);
	
	public List<Match> getMatchs();

}