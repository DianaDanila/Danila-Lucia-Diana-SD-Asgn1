package ui.controller;

import ui.view.CreateTWindow;
import ui.view.LoggedInAWindow;
import ui.view.ViewG;
import ui.view.ViewM;
import ui.view.ViewT;
import ui.view.ViewU;

public class LoggedInAController {

	public LoggedInAController(LoggedInAWindow window) {
	
	window.addViewTButtonHandler(e -> {
			new ViewTController(new ViewT(1));
	});
		window.addCreateTButtonHandler(e -> {
		new CreateTController(new CreateTWindow());
	});

		window.addViewGButtonHandler(e -> {
		new ViewGController(new ViewG());
	});
		window.addViewUButtonHandler(e -> {
		new ViewUController(new ViewU());
	});
		window.addViewMButtonHandler(e -> {
		new ViewM();
	});
	}
}
