package bll.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.Article;
import model.User;

public class UserDTO {

	private String name;

	private String password;

	private boolean admin;

	private List<ArticleDTO> articles = new ArrayList<ArticleDTO>();

	public UserDTO(User u) {
		this.name = u.getName();
		this.password = u.getPassword();
		this.admin = u.isAdmin();
		for (Article a : u.getArticles()) {
			ArticleDTO now = new ArticleDTO(a);
			articles.add(now);
		}
	}

	@JsonCreator
	public UserDTO(@JsonProperty(value = "name") String n, @JsonProperty(value = "password") String p,
			@JsonProperty(value = "admin") boolean b, @JsonProperty(value = "articles") List<ArticleDTO> a) {
		this.name = n;
		this.password = p;
		this.admin = b;
		this.articles = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "name=" + name + ", \n articles=" + articles;
	}

}
