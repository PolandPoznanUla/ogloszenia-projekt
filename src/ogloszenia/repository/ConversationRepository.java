package ogloszenia.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Session;

import ogloszenia.model.Advertisement;
import ogloszenia.model.CATEGORY;
import ogloszenia.model.Conversation;
import ogloszeniar.hibernate.util.HibernateUtil;

public class ConversationRepository {
	
	
	public static Optional<Conversation> findById(Integer id) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			String hql = "SELECT  e FROM Conversation e WHERE e.id=:id";
			Query query = session.createQuery(hql);
			query.setParameter("id",id);
			return Optional.ofNullable((Conversation) query.getSingleResult());
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return Optional.empty();
		} finally {
			session.close();
		}
	}
	
	
	public static Integer persist(Conversation conversation) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.getTransaction().begin();
			session.persist(conversation);
			session.getTransaction().commit();
			return conversation.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return 0;
		} finally {
			session.close();
		}

	}
	
	public static List<Conversation> findByUserId(Integer id) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			String hql = "SELECT  e FROM Conversation e WHERE e.conversationSender.id=:id OR e.conversationReceiver.id=:id";
			Query query = session.createQuery(hql);
			query.setParameter("id",id);
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
