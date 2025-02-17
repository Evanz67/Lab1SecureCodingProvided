package ca.sait.crs.services;

import ca.sait.crs.contracts.Course;
import ca.sait.crs.factories.CourseFactory;
import ca.sait.crs.models.OptionalCourse;
import ca.sait.crs.models.RequiredCourse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// TODO: Make this class immutable.

/**
 * Manages courses
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public final class CourseService {
    /**
     * Path to courses.csv file.
     */
    public static final String COURSES_CSV = "res/courses.csv";

    /**
     * Holds Course instances.
     */
    private final ArrayList<Course> courses;

    /**
     * Initializes CourseService instance
     * @throws FileNotFoundException Thrown if COURSES_CSV file can't be found.
     */
    public CourseService() throws FileNotFoundException {
        this.courses = new ArrayList<>();
        this.load();
    }

    /**
     * Finds course with code
     * @param code Course code
     * @return Course instance or null if not found.
     */
    public Course find(String code) {
        return this.courses.stream()
                .filter(course -> course.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets copy of courses array list.
     * @return A new ArrayList containing all courses.
     */
    public ArrayList<Course> getCourses() {
        return new ArrayList<>(this.courses); // Corrected
    }

    /**
     * Loads courses from CSV file.
     * @throws FileNotFoundException Thrown if file can't be found.
     */
    private void load() throws FileNotFoundException {
        File file = new File(COURSES_CSV);
        Scanner scanner = new Scanner(file);

        CourseFactory courseFactory = new CourseFactory();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            if (parts.length != 3) {
                continue;
            }

            String code = parts[0];
            String name = parts[1];
            int credits = Integer.parseInt(parts[2]);

            try {
                Course course = courseFactory.build(code, name, credits);
                if (course != null) {
                    this.courses.add(course);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
