package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String title;

	@Column
	private String abstracta;

	@OneToOne
	private User author;

	@Column
	private String body;

	@Column
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@ManyToMany
	private Set<Article> related = new HashSet<Article>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	// public File getImage() {
	// return image;
	// }
	//
	// public void setImage(File image) {
	// this.image = image;
	// }

	@Override
	public String toString() {
		return "id=" + id + ", title=" + title + ", abstracta=" + abstracta + ", author=" + author.getName() + ", body="
				+ body;
	}

	public void addRelated(Article r) {
		this.related.add(r);
	}

	public Set<Article> getRelated() {
		return related;
	}

	public void setRelated(Set<Article> related) {
		this.related = related;
	}

}
