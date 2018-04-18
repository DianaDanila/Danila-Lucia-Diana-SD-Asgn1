package datamodel.sd.main;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.controller.LogInController;
import ui.view.LogInWindow;

public class Main extends Application {
	
	public void start(Stage primaryStage) {
		try {
			new LogInController(new LogInWindow(primaryStage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// Cart cart = new Cart();
		// cart.setName("MyCart1");
		//
		// Items item1 = new Items("I10", 10, 1, cart);
		// Items item2 = new Items("I20", 20, 2, cart);
		// Set<Items> itemsSet = new HashSet<Items>();
		// itemsSet.add(item1);
		// itemsSet.add(item2);
		//
		// cart.setItems(itemsSet);
		// cart.setTotal(10 * 1 + 20 * 2);
		//
		// CartDao cartDao =
		// DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getCartDao();
		//
		// cartDao.insert(cart);
		// cartDao.closeConnection();
		// System.out.println("Done");

		/*
		 * User user = new User(); user.setName("Radu");
		 * 
		 * UserDao dao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
		 * 
		 * dao.insert(user);
		 * 
		 * dao.closeConnection();
		 */

		// Tournament t = new Tournament();
		// t.setName("World Cup");

		/*
		 * TournamentDao tdao =
		 * DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
		 * 
		 * Tournament t1 = new Tournament(); Tournament t2 = new Tournament();
		 * t1.setName("Cup"); t1.setStatus("Upcoming");
		 * 
		 * t2.setName("International"); t2.setStatus("Upcoming");
		 * 
		 * tdao.insert(t1); tdao.insert(t2);
		 * 
		 * List<Tournament> tournaments = tdao.findListByString("Upcoming");
		 * 
		 * System.out.println(tournaments);
		 * 
		 * tdao.closeConnection();
		 */

		/*
		 * User user = new User(); user.setName("Diana"); user.setAccountbalance(1000);
		 * UserDao dao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
		 * 
		 * dao.insert(user);
		 * 
		 * System.out.println(user.getAccountbalance());
		 * 
		 * //User n = new User(); //n = user; //n.setAccountbalance(10);
		 * dao.addMoney(user, 100); System.out.println(user.getAccountbalance());
		 * 
		 * dao.withdrawMoney(user, 2000); System.out.println(user.getAccountbalance());
		 * 
		 * 
		 * 
		 * dao.closeConnection();
		 */

		/*
		 * User user = new User(); user.setName("Diana"); user.setAccountbalance(1000);
		 * UserDao dao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
		 * 
		 * dao.insert(user);
		 * 
		 * TournamentDao tdao =
		 * DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
		 * 
		 * Tournament t1 = new Tournament(); t1.setName("Cup");
		 * t1.setStatus("Upcoming"); t1.setType("paid"); t1.setFee(100);
		 * 
		 * 
		 * tdao.insert(t1);
		 * 
		 * dao.enrolment(user, t1);
		 * 
		 * List<Tournament> tournaments = tdao.findListByType("paid");
		 * System.out.println(tournaments);
		 * 
		 * 
		 * tdao.closeConnection();
		 */
		/*Game g = new Game();

		Match m = new Match();

		MatchDao mdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
		m = mdao.findById(1);
		g.setMatch(m);

		GameDao gdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
		g = gdao.findById(1);
		gdao.updateScore(g.getId(), 1);
		System.out.println(g.getScoreP1() + " " + g.getWinner());*/
		
		/*User user = new User();
		user.setName("Diana");
		user.setAccountbalance(10000);
		UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
		UserBLL ubb = new UserBLL(udao);
		
		ubb.insert(user);
		
		Tournament t = new Tournament();
		t.setName("World Cup");
		t.setType("paid");
		t.setFee(100);
		
		TournamentDao tdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
		
		tdao.insert(t);
		
		ubb.enrol(user, t);*/
		
		launch(args);
		
	}

}
