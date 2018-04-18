package pingpong;

public class Tournament {
	private int idtournament;
	private String name;
	private String status;
	private int idwinner;

	public int getIdwinner() {
		return idwinner;
	}

	public void setIdwinner(int idwinner) {
		this.idwinner = idwinner;
	}

	public Tournament() {
	}

	public Tournament(int idtournament, String name, String status, int idwinner) {
		this.idtournament = idtournament;
		this.name = name;
		this.status = status;
		this.idwinner = idwinner;
	}

	public Tournament(String name, String status, int idwinner) {
		super();
		this.name = name;
		this.status = status;
		this.idwinner = idwinner;
	}

	public int getIdtournament() {
		return idtournament;
	}

	@Override
	public String toString() {
		return "Tournament [name=" + name + "]";
	}

	public void setIdtournament(int idtournament) {
		this.idtournament = idtournament;
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

}
