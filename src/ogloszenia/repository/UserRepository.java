package ogloszenia.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Session;

import ogloszenia.model.Image;
import ogloszenia.model.User;
import ogloszeniar.hibernate.util.HibernateUtil;

public class UserRepository {

	public static Optional <User> findByEmail(String email) {
		Session session = null;
		try {
			session = HibernateUtil.openSession().getSession();
			String hql = "SELECT e FROM USER e WHERE e.email =:email";
					Query query = session.createQuery(hql);
					query.setParameter("email", email);
					return Optional.ofNullable((User) query.getSingleResult());
			}catch(Exception ex) {
				ex.printStackTrace();
				session.getTransaction().rollback();
				return Optional.empty();
			}finally {
				session.close();
			}
	}
	
	public static void persist(User user) {
		Session session = null;
		try {
		session = HibernateUtil.openSession().getSession();
		session.getTransaction().begin();
		session.persist(user);
		session.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}

	}
	
}
