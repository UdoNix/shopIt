package de.hdm.shared;

import de.hdm.server.ShopITAdministrationImpl;
import de.hdm.shared.bo.Person;

public class Test {

	public static void main(String[] args) {
		ShopITAdministrationImpl admin = new ShopITAdministrationImpl();
		
		admin.init();
		//Person p = admin.getPersonById(1);
		
<<<<<<< HEAD
		//admin.createPerson("Hans", "Wurst", "abcdef.de");
		
		admin.getPersonById(1);
		
		
=======
//		admin.createPerson("Hans", "Wurst", "abcdef.de");
		
		
		System.out.println(admin.getPersonById(1).getFirstName());
>>>>>>> refs/heads/master
	
	}

}
