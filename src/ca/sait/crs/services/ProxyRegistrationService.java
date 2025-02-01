package ca.sait.crs.services;

import ca.sait.crs.contracts.Course;
import ca.sait.crs.contracts.Student;
import ca.sait.crs.contracts.Registration;
import ca.sait.crs.contracts.RegistrationService;
import ca.sait.crs.exceptions.CannotCreateRegistrationException;
// TODO: Define the ProxyRegistrationService
// TODO: Implement the RegistrationService interface
// TODO: Check student can be registered before passing to RealRegistrationService
// TODO: Make this class immutable.


public final class ProxyRegistrationService implements RegistrationService {
    private final RealRegistrationService realService;

    public ProxyRegistrationService() {
        this.realService = new RealRegistrationService();
    }

    @Override
    public Registration register(Student student, Course course) throws CannotCreateRegistrationException {
        if (student.getGpa() <= 2.0) {
            throw new CannotCreateRegistrationException("Registration failed: GPA must be above 2.0.");
        }
        return realService.register(student, course);
    }

    @Override
    public java.util.ArrayList<Registration> getRegistrations() {
        return realService.getRegistrations();
    }
}
