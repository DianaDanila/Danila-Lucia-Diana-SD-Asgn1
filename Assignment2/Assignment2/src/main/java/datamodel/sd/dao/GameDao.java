package datamodel.sd.dao;

import java.util.List;

import datamodel.sd.model.Game;

public interface GameDao extends Dao<Game> {

	@Override
	Game findById(int id);

	@Override
	void delete(Game objectToDelete);

	@Override
	void update(Game objectToUpdate);

	@Override
	void insert(Game objectToCreate);
	
	public List<Game> getGames();
}
