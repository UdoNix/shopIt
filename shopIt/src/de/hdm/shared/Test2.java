package de.hdm.shared;

//import java.util.Vector;

import de.hdm.server.ShopITAdministrationImpl;
import de.hdm.server.report.ReportGeneratorImpl;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;

public class Test2 {

	
	public static void main(String[] args) {
		
		
		ShopITAdministrationImpl admin = new ShopITAdministrationImpl();
		ReportGeneratorImpl radmin = new ReportGeneratorImpl();
		
		admin.init();
		radmin.init();
		Person p1 = admin.getPersonByEmail("ulrike@mustermann.or");
		//int id = p1.getId() ;
		//System.out.println(p1.getLastName() + ", " + p1.getFirstName() + ", " + p1.getEmail() + p1.getId());
		
		/**
		 * Tests für Team
		 */
		
		//Team t1 = admin.getTeamById(1);
		//System.out.println(t1.getName());
		
		//admin.createTeam("Bärengruppe", p1);
		//Team t2 = admin.getTeamById(4);
		//System.out.println("Team erstellt: " + t2.getName() );
		 
		//System.out.println(admin.getAllTeams());
		//Vector<Team> allTeams = admin.getAllTeams();
		
		//for (Team t : allTeams) {
		//	System.out.println(t.getName());
		//}
		
		
		//t2.setName("Bärenstark");
		//admin.save(t2);
		
		//System.out.println(admin.getAllPersonsOf(t2));
		//>>Problem: Beim Erstellen der Gruppe wird keine Membership erstellt,
		//somit werden auch keine Mitglieder ausgegeben
		
		//admin.createTeam("Igelgruppe", p1);
		Team t3 = admin.getTeamById(3);
		//System.out.println("Team erstellt: " + t3.getName() + ", ID : " + t3.getId());
		//admin.createListFor(t3, "Wocheneinkauf");
		
		//admin.delete(t3);
		
		//System.out.println(admin.getListById(1).getName());
		
		Shop s1 = new Shop();
		s1 = admin.getShopById(1);
		System.out.println(radmin.createAllArticlesOfShopReport(s1));
		
	}

}
