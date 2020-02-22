package cms;

import java.time.LocalDate;
import java.util.Date;

public class StudentCourses {
	private int studentId;
	private int courseId;
	private int progress;
	private Date startdate;

	public StudentCourses(String studentcourses) {
		String[] parts = studentcourses.split("\t");
		this.studentId = Integer.parseInt(parts[0]);
		this.courseId = Integer.parseInt(parts[1]);
		this.progress = Integer.parseInt(parts[2]);
		String[] date = parts[3].split("-");
		LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
	}

	public int getStudentId() {
		return studentId;
	}

	public int getCourseId() {
		return courseId;
	}
	
	public int getProgress() {
		return progress;
	}

	public Date getStartdate() {
		return startdate;
	}

	@Override
	public boolean equals(Object o) {
		if (!getClass().isInstance(o))
			return false;
		StudentCourses sc = (StudentCourses) o;
		return 	studentId == sc.studentId &&
				courseId == sc.courseId &&
				progress == sc.progress &&
				startdate.equals(sc.startdate); 
	}
}
