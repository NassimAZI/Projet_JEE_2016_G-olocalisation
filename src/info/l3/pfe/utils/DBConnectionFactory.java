package info.l3.pfe.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnectionFactory {

	private static SessionFactory sessionFactory;

	public static Session openSession() {

		if (sessionFactory == null)
			sessionFactory = new Configuration().configure().buildSessionFactory();

		return sessionFactory.openSession();
	}
}

