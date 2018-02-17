package com.ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebMethod;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.ws.beans.Person;

public class PersonServiceImpl implements PersonService{
	
	private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

	WebServiceContext wsctx;
	@WebMethod
	public boolean addPerson(Person p) {
		if (persons.get(p.getId()) != null)
			return false;
		persons.put(p.getId(), p);
		return true;
	}
	@WebMethod
	public boolean deletePerson(int id) {
		if (persons.get(id) == null)
			return false;
		persons.remove(id);
		return true;
	}
	@WebMethod
	public String getPerson(int id) {
		
		MessageContext mctx = wsctx.getMessageContext();

		// get detail from request headers
		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
		List userList = (List) http_headers.get("Username");
		List passList = (List) http_headers.get("Password");

		String username = "";
		String password = "";

		if (userList != null) {
			// get username
			username = userList.get(0).toString();
		}

		if (passList != null) {
			// get password
			password = passList.get(0).toString();
		}

		// Should validate username and password with database
		if (username.equals("tauheed") && password.equals("ahmad")) {
			return "Hello World JAX-WS - Valid User!";
		} else {
			return "Unknown User!";
		}
	}
	@WebMethod
	public Person[] getAllPersons() {
		Set<Integer> ids = persons.keySet();
		Person[] p = new Person[ids.size()];
		int i = 0;
		for (Integer id : ids) {
			p[i] = persons.get(id);
			i++;
		}
		return p;
	}
}
