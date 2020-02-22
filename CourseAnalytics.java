package cms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseAnalytics {

	public static void main(String[] args) throws IOException {
		generateCourseCounts();
		// loadCourses();
		// loadSStudents();
		// loadStudentCourses();
	}

	private static void generateCourseCounts() throws IOException {
		Map<Integer, Courses> c = loadCourses();
		ArrayList<StudentCourses> sc = loadStudentCourses();

		HashMap<Integer, Integer> enrollmentCounts = new HashMap<Integer, Integer>();
		for (StudentCourses s : sc) {
			Integer cID = s.getCourseId();
			if (enrollmentCounts.containsKey(cID)) {
				enrollmentCounts.put(cID, enrollmentCounts.get(cID) + 1);
			} else {
				enrollmentCounts.put(cID, 1);
			}
		}

		BufferedWriter w = Files.newBufferedWriter(Paths.get("CourseCounts.csv"), StandardOpenOption.CREATE,
				StandardOpenOption.WRITE);
		String header = ("Course Name\t# Students");
		w.write(header);
		w.newLine();

		enrollmentCounts.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
				.forEachOrdered(m -> {
					Courses cc = c.get(m.getKey());
					System.out.printf("%s\t%d\r\n", cc.getCourseName(), m.getValue());
					try {
						w.write(cc.getCourseName());
					} catch (IOException e) {

						e.printStackTrace();
					}
					try {
						w.write("\t");
					} catch (IOException e) {

					}
					try {
						w.write(m.getValue().toString());
					} catch (IOException e) {

						e.printStackTrace();
					}
					try {
						w.newLine();
					} catch (IOException e) {

						e.printStackTrace();
					}
				});
		w.flush();
		w.close();
	}

	private static Map<Integer, Courses> loadCourses() throws IOException {
		Map<Integer, Courses> courses = new HashMap<Integer, Courses>();
		for (String line : readAllLinesExceptFirst("courses.csv")) {
			Courses c = new Courses(line);
			if (courses.containsKey(c.getId())) {
			} else {
				courses.put(c.getId(), c);
			}
		}
		// System.out.println(courses);
		return courses;
	}

	private static Map<Integer, Students> loadStudents() throws IOException {
		Map<Integer, Students> students = new HashMap<Integer, Students>();
		for (String line : readAllLinesExceptFirst("students.csv")) {
			Students s = new Students(line);
			if (students.containsKey(s.getId())) {
			} else {
				students.put(s.getId(), s);
			}
		}
		// System.out.println(students);
		return students;
	}

	private static ArrayList<StudentCourses> loadStudentCourses() throws IOException {
		ArrayList<StudentCourses> studentCourses = new ArrayList<StudentCourses>();
		for (String line : readAllLinesExceptFirst("studentcourses.csv")) {
			StudentCourses sc = new StudentCourses(line);
			studentCourses.add(sc);
		}
		// System.out.println(studentCourses);
		return studentCourses;
	}

	private static ArrayList<String> readAllLinesExceptFirst(String fpath) throws IOException {
		FileReader fileReader = new FileReader(fpath);
		BufferedReader bReader = new BufferedReader(fileReader);
		try {
			ArrayList<String> values = new ArrayList<String>();
			bReader.readLine();
			while (true) {
				String line = bReader.readLine();
				if (line == null || line.length() == 0)
					break;
				values.add(line);
			}
			return values;
		} finally {
			bReader.close();
		}
	}

}
