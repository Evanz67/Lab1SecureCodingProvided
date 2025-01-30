package ca.sait.crs.services;

import ca.sait.crs.contracts.*;
import ca.sait.crs.exceptions.CannotCreateRegistrationException;
import ca.sait.crs.factories.RegistrationFactory;

import java.util.ArrayList;

// TODO: Make this class immutable.

/**
 * Registers student with course.
 * @author Nick Hamnett <nick.hamnett@sait.ca>
 * @since June 1, 2023
 */
public class RealRegistrationService implements RegistrationService {
    private ArrayList<Registration> registrations;

    public RealRegistrationService() {
        this.registrations = new ArrayList<>();
    }

    /**
     * Registers student with course
     * @param student Student Student instance
     * @param course Course Course instance
     * @return Registration instance.
     */
    @Override
    public Registration register(Student student, Course course) throws CannotCreateRegistrationException {

        Registration registration = null;
        RegistrationFactory registrationFactory = new RegistrationFactory();

        try {
            registration = registrationFactory.build(course, student);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please try again.");
        }

        this.registrations.add(registration);

        return registration;
    }

    /**
     * Gets registrations.
     * @return All registrations
     */
    @Override
    public ArrayList<Registration> getRegistrations() {
        return this.registrations;
    }
}
