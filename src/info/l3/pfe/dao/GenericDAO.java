package info.l3.pfe.dao;

import info.l3.pfe.dto.Client;
import info.l3.pfe.dto.Utilisateur;
import info.l3.pfe.utils.DBConnectionFactory;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

public class GenericDAO {

	/**
	 * 
	 * @param obj
	 *            : Un objet de tous types mais qui soit mappé à une table
	 * @return L'entier retourné est l'identifiant de l'objet inséré en base.
	 */
	public int insert(Object obj) {

		Session session = DBConnectionFactory.openSession();
		int id = Integer.MIN_VALUE;
		try {

			session.beginTransaction();
			Serializable ser = session.save(obj);
			
			try {
				id = (Integer) ser;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			session.getTransaction().commit();

		} catch (Throwable th) {

			th.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();

		} finally {
			session.close();
		}

		return id;
	}
	
	/**
	 * 
	 * @param obj
	 *            : Un objet de tous types mais qui soit mappé à une table
	 * @return Le booléen retourné est vrai si la suppression est avec succès.
	 */
	public boolean delete(Object obj) {

		Session session = DBConnectionFactory.openSession();
		
		try {

			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
			return true;
			
		} catch (Throwable th) {

			th.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return false;

	}
	
	/**
	 * 
	 * Noter que si l'objet n'existe pas il sera inséré.
	 * @param obj
	 *            : Un objet de tous types mais qui soit mappé à une table
	 * @return Le booléen retourné est vrai si la suppression est avec succès.
	 */
	public boolean update(Object obj) {

		Session session = DBConnectionFactory.openSession();
		
		try {

			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			return true;
			
		} catch (Throwable th) {

			th.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return false;

	}
	@SuppressWarnings("rawtypes")
	 public Object select( Class clazz,Serializable obj) {

		Session session = DBConnectionFactory.openSession();
		
		try {

			session.beginTransaction();
			Object ob=session.get(clazz, obj);
			session.getTransaction().commit();
			return ob;
			
		} catch (Throwable th) {

			th.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return null;

	}
	
	public Utilisateur select(Utilisateur obj , int id) {

		Session session = DBConnectionFactory.openSession();
		
		try {

			session.beginTransaction();
			//session.load(obj,id);
			obj=(Utilisateur) session.get(Utilisateur.class, id);
			session.getTransaction().commit();
			return obj;
			
		} catch (Throwable th) {

			th.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return null;

	}
	
	public Client select(Client obj , int id) {

		Session session = DBConnectionFactory.openSession();
		
		try {

			session.beginTransaction();
			//session.load(obj,id);
			obj=(Client) session.get(Client.class, id);
			session.getTransaction().commit();
			return obj;
			
		} catch (Throwable th) {

			th.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return null;

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> selectAll( String requete) {

		Session session = DBConnectionFactory.openSession();
		
		try {

			Query query = session.createQuery(requete);
			ArrayList<Object> result = (ArrayList<Object>) query.list();
			return result;
			
		} catch (Throwable th) {

			th.printStackTrace();
			if (session != null && session.getTransaction() != null)
				session.getTransaction().rollback();

		} finally {
			session.close();
		}
		return null;

	}
	
}
