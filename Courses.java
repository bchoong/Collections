package cms;

public class Courses implements Comparable<Courses> {
	
	private int id;
	private String courseName;
	private String departmentName;
	
	public Courses(String course) {
		String[] parts = course.split("\t");
		this.id = Integer.parseInt(parts[0]);
		this.courseName = parts[1];
		this.departmentName = parts[2];
	}

	public int getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getDepartmentName() {
		return departmentName;
	}
	
	@Override
	public String toString() {
		return String.format("CourseName: %s \t DepartmentName: %s", courseName, departmentName);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!getClass().isInstance(o))
			return false;
		Courses c = (Courses)o;
		return 	id == c.id &&
				courseName.equals(c.courseName) &&
				departmentName.equals(c.departmentName);	
	}

	@Override
	public int compareTo(Courses o) {
		return courseName.compareTo(o.courseName);
	}

}
