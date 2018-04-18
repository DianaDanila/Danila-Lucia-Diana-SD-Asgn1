package ui.controller;

import ui.view.EnrolView;
import ui.view.LoggedInPWindow;
import ui.view.UpdatePlayerWindow;
import ui.view.ViewG;
import ui.view.ViewM;
import ui.view.ViewT;

public class LoggedInPController {
	
	
	public LoggedInPController(LoggedInPWindow window) {
		
		window.addViewTButtonHandler(e -> {
				new ViewTController(new ViewT(0));
		});

			window.addViewGButtonHandler(e -> {
			new ViewGController(new ViewG());
		});
			window.addViewMButtonHandler(e -> {
			new ViewM();
		});
			window.updateButtonHandler(e -> {
				new UpdatePlayerController(new UpdatePlayerWindow());
			});
			window.enrolButtonHandler(e -> {
				new EnrolController(new EnrolView());
			});
		}
}
