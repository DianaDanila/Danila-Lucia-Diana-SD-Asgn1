package datamodel.sd.bll;

import java.util.List;

import datamodel.sd.dao.TournamentDao;
import datamodel.sd.model.Tournament;

public class TournamentBLL {
	private TournamentDao tdao;

	public TournamentBLL(TournamentDao tdao) {
		this.tdao = tdao;
	}

	public Tournament findById(int id) {
		return tdao.findById(id);
	}

	public void delete(Tournament t) {
		tdao.delete(t);
	}

	public void deleteByName(String name) {
		tdao.deleteByName(name);
	}

	public void update(Tournament t) {
		tdao.update(t);
	}

	public void insert(Tournament t) {
		tdao.insert(t);
	}

	public List<Tournament> findListByStatus(String s) {
		return tdao.findListByStatus(s);
	}
	
	public List<Tournament> findListByType(String t) {
		return tdao.findListByType(t);
	}
	
	public List<Tournament> findByName(String s) {
		return tdao.findByName(s);
	}
	
	public List<Tournament> getTournaments() {
		return tdao.getTournaments();
	}
}
