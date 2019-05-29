import com.example.Address;
import com.example.Employee;
import com.example.Project;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GreeterTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
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


        entityManagerFactory = Persistence.createEntityManagerFactory("ru.easyjava.data.jpa.hibernate");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(address);
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGreeter() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from Employee", Employee.class)
            .getResultList()
            .forEach(g -> System.out.println(String.format("%s %s!", g.getFirstName(), g.getLastName())));
        em.getTransaction().commit();
        em.close();
    }
}