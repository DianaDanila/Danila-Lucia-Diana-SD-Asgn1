package bll;

import java.util.List;

import bll.dto.ArticleDTO;
import dao.ArticleDao;
import model.Article;

public class ArticleBLL {

	private ArticleDao adao;
	
	public ArticleBLL(ArticleDao adao) {
		this.adao = adao;
	}
	
	public List<Article> getArticles(){
		return adao.getArticles();
	}
	
	public void insert(ArticleDTO article) {
		Article a = article.convert(article);
		adao.insert(a);
	}
}
