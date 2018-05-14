package command;

import java.util.ArrayList;
import java.util.List;

import bll.ArticleBLL;
import bll.dto.ArticleDTO;
import dao.ArticleDao;
import model.Article;

public class ViewArticlesCommand implements Command {

	public Response execute() {

		ArticleDao adao = new ArticleDao();
		ArticleBLL abll = new ArticleBLL(adao);
		List<Article> articles = abll.getArticles();

		List<ArticleDTO> adto = new ArrayList<ArticleDTO>();
		for (Article a : articles) {
			adto.add(new ArticleDTO(a));
		}

		ViewArticlesResponse r = new ViewArticlesResponse(adto);

		return r;
	}

}
