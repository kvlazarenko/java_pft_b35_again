package ru.stqa.pft.adressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupDate;
import ru.stqa.pft.adressbook.model.Groups;

import java.util.List;

public class DbHelper {
	private SessionFactory sessionFactory;

	public DbHelper() {
		final StandardServiceRegistry registry =
						new StandardServiceRegistryBuilder()
										.configure()
										.build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public Groups groups() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<GroupDate> result = session.createQuery("from GroupDate").list();
		session.getTransaction().commit();
		session.close();
		return new Groups(result);
	}
	public Contacts contacts() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ContactDate> result = session.createQuery("from ContactDate").list();
		session.getTransaction().commit();
		session.close();
		return new Contacts(result);
	}
	public Contacts getContactById(int contactId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ContactDate> result = session.createQuery("from ContactDate where id =" + contactId).list();
		session.getTransaction().commit();
		session.close();
		return new Contacts(result);
	}
	public Groups getGroupById(int groupId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<GroupDate> result = session.createQuery("from GroupDate where id =" + groupId).list();
		session.getTransaction().commit();
		session.close();
		return new Groups(result);
	}
}


