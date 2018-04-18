package pingpong;

public class User {
	private int iduser;
	private String user;// as in name
	private String mail;
	private String password;
	private boolean admin;

	public User() {
	}

	public User(int iduser, String user, String mail, String password, boolean admin) {
		this.iduser = iduser;
		this.setUser(user);
		this.mail = mail;
		this.password = password;
		this.setAdmin(admin);
	}

	public User(String user, String mail, String password) {
		this.user = user;
		this.mail = mail;
		this.password = password;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	@Override
	public String toString() {
		return "User [user=" + user + "]";
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
