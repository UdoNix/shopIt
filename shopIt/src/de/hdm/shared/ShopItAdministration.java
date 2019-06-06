package de.hdm.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.shared.bo.*;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.shared.bo.Group;
/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("ShopItAdministration")

public interface ShopItAdministration extends RemoteService {
	
	Vector<Group> getAllMembersOf(Group g) throws IllegalArgumentException;
	
}
