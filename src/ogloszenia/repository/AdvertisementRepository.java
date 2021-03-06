package ogloszenia.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Session;

import ogloszenia.model.Advertisement;
import ogloszenia.model.CATEGORY;
import ogloszenia.model.User;
import ogloszeniar.hibernate.util.HibernateUtil;

public class AdvertisementRepository {
	
	public static Optional<Advertisement> findById(Integer id) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			String hql = "SELECT  e FROM Advertisement e WHERE e.id=:id";
			Query query = session.createQuery(hql);
			query.setParameter("id",id);
			return Optional.ofNullable((Advertisement) query.getSingleResult());
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return Optional.empty();
		} finally {
			session.close();
		}
	}
	
	public static List<Advertisement> findByCategory(CATEGORY category){
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			String hql = "SELECT e FROM Advertisement e WHERE e.category=:category";
			Query query = session.createQuery(hql);
			query.setParameter("category", category);
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return Collections.emptyList();
		} finally {
			session.close();
		}
	}

	public static Integer persist(Advertisement advertisement) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.getTransaction().begin();
			session.persist(advertisement);
			session.getTransaction().commit();
			return advertisement.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return 0;
		} finally {
			session.close();
		}

	}
	
	public static boolean merge(Advertisement advertisement) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.getTransaction().begin();
			session.merge(advertisement);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}

	}
	
	public static boolean delete(Integer id) {
		Session session = null;
		try {
		
			session = HibernateUtil.openSession();
		
			Optional<Advertisement> advertisement = findById(id);
			if (advertisement.isPresent()) {
				session.getTransaction().begin();
				advertisement.get().setIsActive(false);
				session.merge(advertisement.get());
				session.getTransaction().commit();
				return true;
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}

	}

	public static List<Advertisement> findByPhrase(String phrase) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			String hql = "SELECT e FROM Advertisement e WHERE UPPER(e.title) "
					+ "LIKE :phrase OR UPPER(e.text) LIKE :phrase ORDER BY e.id DESC";
			Query query = session.createQuery(hql);
			query.setParameter("phrase", "%"+phrase.toUpperCase()+"%");
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return Collections.emptyList();
		} finally {
			session.close();
		}
	}
	
	public static List<Advertisement> findByPhraseAndLocation(String phrase,String location) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			String hql = "SELECT e FROM Advertisement e WHERE"
					+ " (UPPER(e.title) LIKE :phrase AND UPPER(e.cityName) LIKE :location)"
					+ " OR (UPPER(e.text) LIKE :phrase AND UPPER(e.cityName) LIKE :location) ORDER BY e.id DESC";
			Query query = session.createQuery(hql,Advertisement.class);
			query.setParameter("phrase", "%"+phrase.toUpperCase()+"%");
			query.setParameter("location", "%"+location.toUpperCase()+"%");
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return Collections.emptyList();
		} finally {
			session.close();
		}
	}

}
