package ui.controller;

import datamodel.sd.bll.GameBLL;
import datamodel.sd.dao.GameDao;
import datamodel.sd.dao.factory.DaoFactory;
import ui.view.ViewG;

public class ViewGController {

	public ViewGController(ViewG window) {
		GameDao gdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
		GameBLL g = new GameBLL(gdao);

		window.updateButtonHandler(e -> {
			//if (a == 0) {
				g.updateScore(window.getId(), window.getSide());
			/*} else {
				g.updateScoreByPlayer(Integer.parseInt(id.getText()), Integer.parseInt(side.getText()),
						Integer.parseInt(idp.getText()));
			}*/
			window.close();
		});
	}
}
