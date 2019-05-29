package domain;

import com.example.Address;
import com.example.Employee;
import com.example.Project;
import logics.HibernateUtil;
import org.hibernate.Session;

import java.util.Calendar;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Domain
{
    public static void main(String[] args)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address = new Address();
        address.setCountry("BY");
        address.setCity("Minsk");
        address.setStreet("N58");
        address.setPostCode("220028");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1997, Calendar.FEBRUARY, 24);

        Employee employee = new Employee();
        employee.setFirstName("Tihon");
        employee.setLastName("Kasko");
        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        Project project = new Project();
        project.setTitle("User");

        Set<Project> projects = new HashSet<Project>();
        projects.add(project);
        employee.setProjects(projects);

        session.save(address);
        session.save(employee);
        session.save(project);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
