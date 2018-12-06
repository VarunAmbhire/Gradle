package service;

import java.util.ArrayList;
import java.util.List;

import Model.Contacts;
import Repository.ContactRepository;

public class ContactService {
	List<Contacts> listOfContacts=new ArrayList<>();
	ContactRepository contactRepository=new ContactRepository();
	
	public List<Contacts> get() throws Exception{
		listOfContacts=contactRepository.getContacts();
		return listOfContacts;
	}
	
	public void add(String id, String name, String email,String state, String gender) throws Exception {
		contactRepository.addContacts(id, name, email, state, gender);
	}	
	
	public void remove(String id) throws Exception {
		contactRepository.removeContact(id);
	}
	
	public Contacts select(String id)throws Exception{
		return contactRepository.selectedContact(id);
	}
	
	public void update(String id, String name, String email,String state, String gender) throws Exception {
		contactRepository.update(id, name, email, state, gender);
	}
	
	public List<Contacts>search(String name)throws Exception{
		return contactRepository.searchResult(name);
	}
}
