package de.hdm.shared;

import de.hdm.server.ShopITAdministrationImpl;
import de.hdm.shared.bo.Person;

public class Test {

	public static void main(String[] args) {
		ShopITAdministrationImpl admin = new ShopITAdministrationImpl();

		admin.init();
		admin.createPerson("Huren", "Sohn", "Huren@Sohn.de");
		System.out.println("Create Done");
		Person p1 = admin.getPersonByEmail("Huren@Sohn.de");
		int id = p1.getId() ;
		p1 = admin.getPersonById(id);
		System.out.println(admin.getPersonByName(p1));
		Person p2 = new Person();
		p2.setId(id);
		p2.setFirstName("Hubertus");
		p2.setLastName("Huberti");
		p2.setEmail("whatever");
		admin.save(p2);
		//System.out.println(admin.getPersonById(1).getFirstName());
	}

}
