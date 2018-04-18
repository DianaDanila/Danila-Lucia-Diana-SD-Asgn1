package datamodel.sd.dao;

import java.util.List;

import datamodel.sd.model.Tournament;

public interface TournamentDao extends Dao<Tournament> {

	@Override
	Tournament findById(int id);

	List<Tournament> findByName(String s);

	List<Tournament> findListByType(String t);

	List<Tournament> findListByStatus(String s);
	
	public List<Tournament> getTournaments();

	@Override
	void delete(Tournament objectToDelete);

	@Override
	void update(Tournament objectToUpdate);

	@Override
	void insert(Tournament objectToCreate);

	void deleteByName(String s);

}