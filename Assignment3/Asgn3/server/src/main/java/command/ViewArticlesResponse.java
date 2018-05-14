package command;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.dto.ArticleDTO;

public class ViewArticlesResponse implements Response{
	private List<ArticleDTO> articles;

	@JsonCreator
	public ViewArticlesResponse(@JsonProperty(value = "articles") List<ArticleDTO> articles) {
		this.articles = articles;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}
}
