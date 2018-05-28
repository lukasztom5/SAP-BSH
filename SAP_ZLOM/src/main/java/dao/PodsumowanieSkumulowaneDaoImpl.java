package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Hibernate.HibernateUtil;
import encje.*;

public class PodsumowanieSkumulowaneDaoImpl implements PodsumowanieSkumulowaneDao{
	private static final Log log = LogFactory.getLog(PodsumowanieDaoImpl.class);

	private Session session;
	private Transaction tx;
	@Override
	public Integer NewIdPodsumowanieSkumulowane() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer result = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session
					.createSQLQuery("select max(idpodsumowanie_skum) FROM PODSUMOWANIE_SKUMULOWANE");
			Integer result2 = (Integer) query.uniqueResult();
			if (result2 == null) {
				result = 0;
			} else
				result = result2.intValue();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result + 1;
	}
	@Override
	public boolean PodsumowanieSkumulowaneExists(PodsumowanieSkumulowane podsumowaniesk){
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session
					.createQuery("from PODSUMOWANIE_SKUMULOWANE where idpodsumowanie_skum='"
							+ podsumowaniesk.getIdpodsumowanieSkum()+ "'");
			PodsumowanieSkumulowane p = (PodsumowanieSkumulowane) query.uniqueResult();
			tx.commit();
			if (p != null)
				result = true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return result;
	}
	@Override
	public void save(PodsumowanieSkumulowane podsumowaniesk) {
		log.debug("persisting PODSUMOWANIE instance");
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(podsumowaniesk);
			tx.commit();
			log.debug("persist successful");
		} catch (Exception e) {
			log.error("persist failed", e);
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PodsumowanieSkumulowane> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<PodsumowanieSkumulowane> List = new ArrayList<PodsumowanieSkumulowane>();
		try {
			tx = session.getTransaction();
			tx.begin();
			SQLQuery q = session.createSQLQuery(
					"select * from PODSUMOWANIE_SKUMULOWANE");
			q.addEntity(PodsumowanieSkumulowane.class);
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
	public List<PodsumowanieSkumulowane> findbytydzien(Integer tydzien, Integer rok) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<PodsumowanieSkumulowane> lista= new ArrayList<PodsumowanieSkumulowane>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from PODSUMOWANIE_SKUMULOWANE where"
					+ "tydzien="+tydzien+" and rok="+rok);
			q.addEntity(PodsumowanieSkumulowane.class);
			lista = q.list();
			t.commit();
		}catch(Exception e){
			if(t!=null)
				t.rollback();
		}finally {
			sess.close();
		}
		return lista;

}
	@SuppressWarnings("unchecked")
	@Override
	public List<PodsumowanieSkumulowane> findbytygodnie(Integer tydzien1, Integer rok1, Integer tydzien2, Integer rok2) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<PodsumowanieSkumulowane> lista= new ArrayList<PodsumowanieSkumulowane>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from PODSUMOWANIE_SKUMULOWANE where tydzien between (select tydzien from PODSUMOWANIE_SKUMULOWANE where tydzien="
					+tydzien1+" and rok="+rok1+") and (select tydzien from PODSUMOWANIE_SKUMULOWANE where tydzien="+tydzien2+" and rok="+rok2+")");
			q.addEntity(PodsumowanieSkumulowane.class);
			lista = q.list();
			t.commit();
		}catch(Exception e){
			if(t!=null)
				t.rollback();
		}finally {
			sess.close();
		}
		return lista;

}
	@SuppressWarnings("unchecked")
	@Override
	public List<PodsumowanieSkumulowane> findbytydzienobszar(Integer tydzien, Integer rok,String obszar) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<PodsumowanieSkumulowane> lista= new ArrayList<PodsumowanieSkumulowane>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from PODSUMOWANIE_SKUMULOWANE where tydzien="+tydzien+" and rok="+rok+"and idprodukcja in"
+"(select idprodukcja from PRODUKCJA where linia='"+obszar+"' and tydzien="+tydzien+" and rok="+rok+")");
			q.addEntity(PodsumowanieSkumulowane.class);
			lista = q.list();
			t.commit();
		}catch(Exception e){
			if(t!=null)
				t.rollback();
		}finally {
			sess.close();
		}
		return lista;

}
	@SuppressWarnings("unchecked")
	@Override
	public List<PodsumowanieSkumulowane> findbytygodnieobszar(Integer tydzien1, Integer rok1, Integer tydzien2, Integer rok2, String obszar) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<PodsumowanieSkumulowane> lista= new ArrayList<PodsumowanieSkumulowane>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from PODSUMOWANIE_SKUMULOWANE where tydzien between (select tydzien from PODSUMOWANIE_SKUMULOWANE where tydzien="+tydzien1+" and rok="+rok1+""
					+ ") and (select tydzien from PODSUMOWANIE_SKUMULOWANE where tydzien="+tydzien2+" and rok="+rok2+") "
							+ "and idprodukcja in (select idprodukcja from PRODUKCJA where linia='"+obszar+""
									+ "' and tydzien between (select tydzien from PRODUKCJA where tydzien="+tydzien1+" and rok="+rok1+") and ("
+"select tydzien from PRODUKCJA where tydzien="+tydzien2+" and rok="+rok2+"))");
			q.addEntity(PodsumowanieSkumulowane.class);
			lista = q.list();
			t.commit();
		}catch(Exception e){
			if(t!=null)
				t.rollback();
		}finally {
			sess.close();
		}
		return lista;

}
	@Override
	public PodsumowanieSkumulowane findById(int id) {
		log.debug("getting PODSUMOWANIE_SKUMULOWANE instance with idpodsumowanie_skum: " + id);
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			PodsumowanieSkumulowane result = (PodsumowanieSkumulowane) session.get(PodsumowanieSkumulowane.class, id);
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