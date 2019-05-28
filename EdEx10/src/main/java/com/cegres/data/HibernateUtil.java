package com.cegres.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(ssr).buildMetadata().buildSessionFactory();	
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
