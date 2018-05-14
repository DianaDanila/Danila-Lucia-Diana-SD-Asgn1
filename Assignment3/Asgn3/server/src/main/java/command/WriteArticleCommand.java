package command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.ArticleBLL;
import bll.dto.ArticleDTO;
import dao.ArticleDao;

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

	public Response execute() {
		ArticleDao adao = new ArticleDao();
		ArticleBLL abll = new ArticleBLL(adao);
		abll.insert(article);
		
		WriteArticleResponse r = new WriteArticleResponse();
		
		return r;
	}
}
