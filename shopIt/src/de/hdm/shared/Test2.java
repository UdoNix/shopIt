package de.hdm.shared;

import java.util.Vector;

//import java.util.Vector;

import de.hdm.server.ShopITAdministrationImpl;
import de.hdm.server.report.ReportGeneratorImpl;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Responsibility;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.UnitOfMeasure;

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
		
		Team t1 = admin.getTeamById(1);
		System.out.println(t1.getName());
		
		//admin.createTeam("Bärengruppe", p1);
		Team t2 = admin.getTeamById(4);
		System.out.println("Team erstellt: " + t2.getName() );
		 
		System.out.println(admin.getAllTeams());
		Vector<Team> allTeams = admin.getAllTeams();
		
		for (Team t : allTeams) {
			System.out.println(t.getName());
		}
		
		
		t2.setName("Bärenstark");
		admin.save(t2);
		
		System.out.println(admin.getAllPersonsOf(t2));
		//>>Problem: Beim Erstellen der Gruppe wird keine Membership erstellt,
		//somit werden auch keine Mitglieder ausgegeben
		
		//admin.createTeam("Igelgruppe", p1);
		Team t3 = admin.getTeamById(3);
		System.out.println("Team erstellt: " + t3.getName() + ", ID : " + t3.getId());
		admin.createListFor(t3, "Wocheneinkauf");
		
		admin.delete(t3);
		
		System.out.println(admin.getListById(1).getName());
		
		Shop s1 = new Shop();
		s1 = admin.getShopById(1);
		//System.out.println(radmin.createAllArticlesOfShopReport(s1));
		
		
		/**
		 * Test für List
		 */
		
		ShoppingList l1 = new ShoppingList();
		//admin.createListFor(t3, "Wocheneinkauf");
		
		System.out.println(admin.getListById(1));
		
		admin.getAllItemsOfList(l1);
		
		admin.delete(l1);
		
		/**
		 * Test für Artikel
		 */
		
		Article a = new Article();
		//admin.createArticle("Banane");
		
		System.out.println(admin.getAllArticles());
		
		System.out.println(admin.getArticleById(1));
		
		System.out.println(admin.getArticleByName(a));
		
		a.setName("Bananen");
		admin.save(a);
		
		
		/**
		 * Test für Eintrag
		 */
		
		Item i = new Item();
		//admin.createItem(l1, a);
		
		admin.getItemById(1);
		System.out.println("Item gefunden: " );
		admin.getAllItems();
		
		admin.getAllItems();
		i.setListId(3);
		
		admin.update(i);
		System.out.println(i.getListId());
		
		i.setFavorit(true);
		admin.update(i);
		
		admin.delete(i);
		admin.update(i);
		
		/**
		 * Test für Shops
		 */
		
		Shop s = new Shop();
		//admin.createShop("Aldi", "Bauerstraße", 98298, "Stuttgart");
		
		System.out.println(admin.getShopById(1));
		System.out.println(admin.getAllArticles());
		
		s.setCity("Frankfurt");
		admin.save(s);
		
		System.out.println(s.getCity());
		
		admin.delete(s);
		
		/**
		 * Test für Maßeinheit
		 */
		
		UnitOfMeasure m = new UnitOfMeasure();
		//admin.createUnitOfMeasure(200, "kg");
		
		m.setQuantity(300);
		admin.save(m);
		
		System.out.println(m.getQuantity());
		
		/**
		 * Test für Zuständigkeit
		 */
		
		Responsibility r = new Responsibility();
		//admin.createResponsibility(p1, s);
		
		System.out.println(admin.getResponsibilityById(1));
		System.out.println(admin.getAllResponsibilityOfPerson(p1));
		
		r.setPersonId(1);
		admin.update(r);
		
		System.out.println(r.getPersonId());
		
		/**
		 * Test für Gruppenmitglieder
		 */
		
		Membership ms = new Membership();
		//admin.createMembership(1, 2);
		
		System.out.println(admin.getMembershipById(1));
		
		System.out.println(admin.getAllMembership(p1));
		
		admin.delete(ms);
		
		
		
	}

}
