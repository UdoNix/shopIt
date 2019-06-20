package de.hdm.shared;

import java.util.Vector;

import de.hdm.server.ShopITAdministrationImpl;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;

public class Test {

	public static void main(String[] args) {
		ShopITAdministrationImpl admin = new ShopITAdministrationImpl();

		admin.init();
		admin.createPerson("Huren", "Sohn", "Huren@Sohn.de");
		System.out.println("Create Done");
		Person p1 = admin.getPersonByEmail("Huren@Sohn.de");
		int personId = p1.getId() ;
		p1 = admin.getPersonById(personId);
		System.out.println("getByMail: " +p1.getEmail());
		System.out.println("getByName: "+admin.getPersonByName(p1));
		
		Person p2 = new Person();
		p2.setId(personId);
		p2.setFirstName("Hubertus");
		p2.setLastName("Huberti");
		p2.setEmail("whatever");
		admin.save(p2);
		
		System.out.println(admin.getPersonById(personId));
		
		Team t = admin.getTeamById(1);
		admin.createMembership(p2, t);
		
//		Shop s = admin.getShopById(1);
//		Item i = admin.getItemById(1);
//		admin.createResponsibility(p2, s, i);
//		
//		admin.delete(p2.getId());
//		
//		Vector<Person>allPersons = admin.getAllPersons();
//		for (Person p : allPersons) {
//			System.out.println(p.getFirstName());
//		}
	}

}
