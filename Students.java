package cms;

public class Students implements Comparable<Students> {
	private int id;
	private String firstname;
	private String lastname;
	private String street;
	private String streetDetail;
	private String city;
	private String state;
	private int postalCode;
	private int majorId;

	public Students(String students) {
		String[] parts = students.split("\t");
		this.id = Integer.parseInt(parts[0]);
		this.firstname = parts[1];
		this.lastname = parts[2];
		this.street = parts[3];
		this.streetDetail = parts[4];
		this.city = parts[5];
		this.state = parts[6];
		this.postalCode = Integer.parseInt(parts[7]);
		this.majorId = Integer.parseInt(parts[8]);
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getStreet() {
		return street;
	}

	public String getStreetDetail() {
		return streetDetail;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public int getMajorId() {
		return majorId;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s", firstname, lastname, street, streetDetail, city, state);
	}

	@Override
	public boolean equals(Object o) {
		if (!getClass().isInstance(o))
			return false;
		Students s = (Students) o;
		return id == s.id && firstname.equals(s.firstname) && lastname.equals(s.lastname) && street.equals(s.street)
				&& streetDetail.equals(s.streetDetail) && city.contentEquals(s.city) && state.equals(s.state)
				&& postalCode == (s.postalCode) && majorId == (s.majorId);
	}

	@Override
	public int compareTo(Students o) {
		if (lastname.compareTo(o.lastname) == 0)
			return firstname.compareTo(o.firstname);
		else {
			return lastname.compareTo(o.lastname);
		}
	}
}
