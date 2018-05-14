package bll.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.UserBLL;
import dao.UserDao;
import model.Article;
import model.User;

public class ArticleDTO {

	private String title;

	private String abstracta;

	private String author;

	private String body;

	private String image;

	private Set<String> related = new HashSet<String>();

	public ArticleDTO(Article a) {
		this.title = a.getTitle();
		this.abstracta = a.getAbstracta();
		this.author = a.getAuthor().toString();
		this.body = a.getBody();
		this.image = a.getImage();
		
		for (Article article : a.getRelated()) {
			this.related.add(article.getTitle());
		}
	}

	@JsonCreator
	public ArticleDTO(@JsonProperty(value = "title") String t, @JsonProperty(value = "abstract") String a,
			@JsonProperty(value = "author") String author, @JsonProperty(value = "body") String b, @JsonProperty(value = "related") Set<String> r, @JsonProperty(value = "image") String i) {
		this.title = t;
		this.abstracta = a;
		this.author = author;
		this.body = b;
		this.image = i;
		this.related = r;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstracta() {
		return abstracta;
	}

	public void setAbstracta(String abstracta) {
		this.abstracta = abstracta;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public Article convert(ArticleDTO adto) {
		Article a = new Article();
		a.setAbstracta(adto.getAbstracta());
		UserDao udao = new UserDao();
		UserBLL ubll = new UserBLL(udao);
		User u = ubll.findByUserName(adto.getAuthor());
		a.setAuthor(u);
		a.setBody(adto.getBody());
		a.setTitle(adto.getTitle());
		return a;
	}

	public Set<String> getRelated() {
		return related;
	}

	public void setRelated(Set<String> related) {
		this.related = related;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

//	public File getImage() {
//		return image;
//	}
//
//	public void setImage(File image) {
//		this.image = image;
//	}


}
