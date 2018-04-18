package datamodel.sd.bll;

import java.util.List;

import datamodel.sd.dao.MatchDao;
import datamodel.sd.model.Match;

public class MatchBLL {
	private MatchDao mdao;

	public MatchBLL(MatchDao mdao) {
		this.mdao = mdao;
	}
	
	public Match findById(int id) {
		return mdao.findById(id);
	}
	
	public void delete(Match m) {
		mdao.delete(m);
	}
	
	public void update(Match m) {
		mdao.update(m);
	}
	
	public void insert(Match m) {
		mdao.insert(m);
	}
	
	public int getPlayerByGameSide(int idm, int side) {
		return mdao.getPlayerByGameSide(idm, side);
	}
	
	public List<Match> getMatchs(){
		return mdao.getMatchs();
	}
}
