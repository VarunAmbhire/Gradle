package Model;

public class Contacts {
	private String id;
	private String name;
	private String email;
	private String state;
	private String gender;
	
	public Contacts(String id, String name, String email, String state, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.state = state;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getState() {
		return state;
	}

	public String getGender() {
		return gender;
	}
	
	
}
