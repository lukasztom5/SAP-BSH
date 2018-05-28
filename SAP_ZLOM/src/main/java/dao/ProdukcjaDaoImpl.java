package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateUtil;
import encje.*;

public class ProdukcjaDaoImpl implements ProdukcjaDao{
	private static final Log log = LogFactory.getLog(CzescDaoImpl.class);

	private Session session;
	private Transaction tx;
	@SuppressWarnings("unchecked")
	@Override
	public List<Produkcja> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Produkcja> List = new ArrayList<Produkcja>();
		try {
			tx = session.getTransaction();
			tx.begin();
			SQLQuery q = session.createSQLQuery(
					"select * from PRODUKCJA");
			q.addEntity(Produkcja.class);
			List = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return List;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Produkcja> findtydzien() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Produkcja> List = new ArrayList<Produkcja>();
		try {
			tx = session.getTransaction();
			tx.begin();
			SQLQuery q = session.createSQLQuery(
					"select min(tydzien) from PRODUKCJA group by tydzien");
			q.addEntity(Produkcja.class);
			List = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return List;
	}	
	@SuppressWarnings("unchecked")
	@Override
	public List<Produkcja> findrok() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Produkcja> List = new ArrayList<Produkcja>();
		try {
			tx = session.getTransaction();
			tx.begin();
			SQLQuery q = session.createSQLQuery(
					"select min(rok) from PRODUKCJA group by rok");
			q.addEntity(Produkcja.class);
			List = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return List;
	}	
	@SuppressWarnings("unchecked")
	@Override
	public List<Produkcja> findlinia() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Produkcja> List = new ArrayList<Produkcja>();
		try {
			tx = session.getTransaction();
			tx.begin();
			SQLQuery q = session.createSQLQuery(
					"select min(linia) from PRODUKCJA group by linia");
			q.addEntity(Produkcja.class);
			List = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return List;
	}	
	@Override
	public Produkcja findById(int id) {
		log.debug("getting PRODUKCJA instance with idprodukcja: " + id);
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			Produkcja result = (Produkcja) session.get(Produkcja.class, id);
			tx.commit();
			log.debug("get successful");
			return result;
		} catch (RuntimeException e) {
			log.error("get failed", e);
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}		
}
