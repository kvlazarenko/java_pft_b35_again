package ru.stqa.pft.adressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.util.List;

public class HbConnectionTest {
	private SessionFactory sessionFactory;

	@BeforeClass
	protected void setUp() {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry =
						new StandardServiceRegistryBuilder()
										.configure()
										.build();
		try {
			sessionFactory =
							new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			// The registry would be destroyed by the SessionFactory, but we
			// had trouble building the SessionFactory so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	@Test
	public void testHbConnection() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<GroupDate> result = session.createQuery("from GroupDate").list();
		for (GroupDate group : result) {
			System.out.println(group);
		}
		session.getTransaction().commit();
		session.close();
	}
}
