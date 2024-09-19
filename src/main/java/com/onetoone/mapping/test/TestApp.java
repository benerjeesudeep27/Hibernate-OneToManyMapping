package com.onetoone.mapping.test;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.onetoone.mapping.entity.Address;
import com.onetoone.mapping.entity.Student;

public class TestApp {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		Properties props = new Properties();
		props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		props.put(Environment.URL, "jdbc:mysql://localhost:3306/One_To_One");
		props.put(Environment.USER, "root");
		props.put(Environment.PASS, "Shiva@12345");
		props.put(Environment.HBM2DDL_AUTO, "update");
		props.put(Environment.SHOW_SQL, "true");
		props.put(Environment.FORMAT_SQL, "true");
		cfg.setProperties(props);
		cfg.addAnnotatedClass(Student.class);
		cfg.addAnnotatedClass(Address.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		StandardServiceRegistry registry = builder.applySettings(cfg.getProperties()).build();
		SessionFactory factory = cfg.buildSessionFactory(registry);
		Session session = factory.openSession();
		Address adrs = new Address();
		adrs.setHouseNo(101);
		adrs.setStreet("Rajeev Chawk");
		adrs.setState("Delhi");
		adrs.setCity("Lagpat Nagar");
		Student student = new Student();
		student.setName("Kuber Dev");
		student.setCls("10th");
		student.setAddress(adrs);
		session.save(student);
		session.save(adrs);
		Transaction tx =  session.beginTransaction();
		tx.commit();
		System.out.println("Data Inserted");
	}
		
}
