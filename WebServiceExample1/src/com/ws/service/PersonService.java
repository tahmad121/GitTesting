package com.ws.service;

import com.ws.beans.Person;

public interface PersonService {
public boolean addPerson(Person p);
	
	public boolean deletePerson(int id);
	
	public String getPerson(int id);
	
	public Person[] getAllPersons();
}
