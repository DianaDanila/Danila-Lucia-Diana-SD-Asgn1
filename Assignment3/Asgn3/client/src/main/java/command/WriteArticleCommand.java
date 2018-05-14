package command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dto.ArticleDTO;

public class WriteArticleCommand implements Command {

	private ArticleDTO article;

	@JsonCreator
	public WriteArticleCommand(@JsonProperty(value = "article") ArticleDTO a) {
		this.article = a;
	}

	public ArticleDTO getArticle() {
		return article;
	}

	public void setArticle(ArticleDTO article) {
		this.article = article;
	}


}
