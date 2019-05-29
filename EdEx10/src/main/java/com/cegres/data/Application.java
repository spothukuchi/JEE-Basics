package com.cegres.data;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.cegres.data.entities.Student;

public class Application {

	public static void main(String[] args) {
		Application app = new Application();
		
		app.insertBulk();
		app.update(5L);
		app.delete();
		app.display();
		System.out.println("Bright Students:");
		app.displayBright();
	}
	
	//insert records in bulk
	public void insertBulk() {
		Random random = new Random();
		String prefix = "student_";
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		for(int i=1; i<=10; i++) {
			Student student = new Student();
			student.setName(prefix+i);
			student.setMarks(random.nextInt(100));
			session.save(student);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	//update one record
	public void update(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Student student = session.get(Student.class, id);
		student.setName("Bright Student");
		session.saveOrUpdate(student);
		session.getTransaction().commit();
		session.close();
		
	}
	
	//delete records with a criteria
	public void delete() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		String hql = "DELETE FROM Student WHERE marks < 35";
		Query<Student> query = session.createQuery(hql);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		
	}
	
	//display specific records
	@SuppressWarnings("unchecked")
	public void displayBright() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		String hql = "FROM Student WHERE marks > 50";
		Query<Student> query = session.createQuery(hql);
		List<Student> students = query.getResultList();
		for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();){
			Student student = iterator.next(); 
			System.out.println(student.getId() + "\t" + student.getName() + "\t" + student.getMarks()); 
		}
		session.close();
	}
	
	
	//display all records
	public void display() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cr = cb.createQuery(Student.class);
		Root<Student> root = cr.from(Student.class);
		cr.select(root);

		Query<Student> query = session.createQuery(cr);
		List<Student> students = query.getResultList();
        
		for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();){
			Student student = iterator.next(); 
			System.out.println(student.getId() + "\t" + student.getName() + "\t" + student.getMarks()); 
		}
        session.close();
		}

}
