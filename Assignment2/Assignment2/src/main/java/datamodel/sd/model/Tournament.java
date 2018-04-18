package datamodel.sd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tournaments")
public class Tournament {

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String name;

	@Column
	private String status;

	@Column
	private int idwinner = 0;

	@Column
	private String type;

	@Column
	private int fee;

	@Column
	private int winmoney;

	@OneToMany(mappedBy = "tournament")
	private Set<Match> matches;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "enrolments", joinColumns = @JoinColumn(name = "idtournament", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "iduser", referencedColumnName = "id"))
	private List<User> users = new ArrayList<User>();
//	
//	@OneToMany(mappedBy = "enrolment.user")
//	private List<User> users;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdwinner() {
		return idwinner;
	}

	public void setIdwinner(int idwinner) {
		this.idwinner = idwinner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Match> getMatches() {
		return matches;
	}

	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}

	/*
	 * public List<User> getUsers() { return users; }
	 * 
	 * public void setUsers(List<User> users) { this.users = users; }
	 */

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getWinmoney() {
		return winmoney;
	}

	public void setWinmoney(int winmoney) {
		this.winmoney = winmoney;
	}

	@Override
	public String toString() {
		return "Tournament [id=" + id + ", name=" + name + ", status=" + status + ", idwinner=" + idwinner + ", type="
				+ type;
	}
	
	public void addUser(User user) {
		this.users.add(user);
		this.setWinmoney(this.getWinmoney()+this.getFee());
	}

}
