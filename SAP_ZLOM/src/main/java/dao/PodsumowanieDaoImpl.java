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

public class PodsumowanieDaoImpl implements PodsumowanieDao {
	private static final Log log = LogFactory.getLog(PodsumowanieDaoImpl.class);

	private Session session;
	private Transaction tx;
	@Override
	public Integer NewIdPodsumowanie() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Integer result = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session
					.createSQLQuery("select max(idpodsumowanie) FROM PODSUMOWANIE");
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
	public boolean PodsumowanieExists(Podsumowanie podsumowanie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean result = false;
		tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session
					.createQuery("from PODSUMOWANIE where IDPODSUMOWANIe='"
							+ podsumowanie.getIdpodsumowanie()+ "'");
			Podsumowanie p = (Podsumowanie) query.uniqueResult();
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
	public void save(Podsumowanie podsumowanie) {
		log.debug("persisting PODSUMOWANIE instance");
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(podsumowanie);
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
	public List<Podsumowanie> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<Podsumowanie> List = new ArrayList<Podsumowanie>();
		try {
			tx = session.getTransaction();
			tx.begin();
			SQLQuery q = session.createSQLQuery(
					"select * from PODSUMOWANIE");
			q.addEntity(Podsumowanie.class);
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
	public List<Podsumowanie> findbytydzien(Integer tydzien, Integer rok) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<Podsumowanie> lista= new ArrayList<Podsumowanie>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from Podsumowanie where"
					+ "tydzien="+tydzien+" and rok="+rok);
			q.addEntity(Podsumowanie.class);
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
	public List<Podsumowanie> findbytygodnie(Integer tydzien1, Integer rok1, Integer tydzien2, Integer rok2) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<Podsumowanie> lista= new ArrayList<Podsumowanie>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from PODSUMOWANIE where tydzien between (select tydzien from PODSUMOWANIE where tydzien="
					+tydzien1+" and rok="+rok1+") and (select tydzien from PODSUMOWANIE where tydzien="+tydzien2+" and rok="+rok2+")");
			q.addEntity(Podsumowanie.class);
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
	public List<Podsumowanie> findbytydzienobszar(Integer tydzien, Integer rok,String obszar) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<Podsumowanie> lista= new ArrayList<Podsumowanie>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from PODSUMOWANIE where idprodukcja in (select idprodukcja from PRODUKCJA where idtydzien in (select idtydzien from TYDZIEN where tydzien="+tydzien+""
					+ ") and idrok in (select idrok from ROK where rok="+rok+") and idlinia in (select idobszar from OBSZAR where obszar='"+obszar+"'))");
			q.addEntity(Podsumowanie.class);
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
	public List<Podsumowanie> findbytygodnieobszar(Integer tydzien1, Integer rok1, Integer tydzien2, Integer rok2, String obszar) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<Podsumowanie> lista= new ArrayList<Podsumowanie>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from PODSUMOWANIE where idprodukcja in (select idprodukcja from PRODUKCJA where idtydzien in (select idtydzien from TYDZIEN where tydzien between "+tydzien1+" and "+tydzien2+""
					+ ") and idrok in (select idrok from ROK where rok between "+rok1+" and "+rok2+") and idlinia in (select idobszar from OBSZAR where obszar='"+obszar+"'))");
			q.addEntity(Podsumowanie.class);
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
	public List<Podsumowanie> findbydaty(String data1, String data2, String obszar) {
		Session sess=HibernateUtil.getSessionFactory().openSession();
		Transaction t=null;
		List<Podsumowanie> lista= new ArrayList<Podsumowanie>();
		try{
			t=sess.getTransaction();
			t.begin();
			SQLQuery q = sess.createSQLQuery("select * from PODSUMOWANIE where idprodukcja in (select idprodukcja from PRODUKCJA where idtydzien in (select idtydzien from TYDZIEN where tydzien between (SELECT DATENAME(ww, '"+data1+"')) and (SELECT DATENAME(ww, '"+data2+"')))"
+"and idrok in (select idrok from ROK where rok between (SELECT DATENAME(yy, '"+data1+"')) and (SELECT DATENAME(yy, '"+data2+"'))) and idlinia in (select idobszar from OBSZAR where obszar='"+obszar+"'))   ");
			q.addEntity(Podsumowanie.class);
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
}