package ogloszenia.repository;

import org.hibernate.Session;

import ogloszenia.model.ConversationMessage;
import ogloszeniar.hibernate.util.HibernateUtil;

public class ConversationMessageRepository {
	
	public static Integer persist(ConversationMessage conversationMessage) {
		Session session = null;
		try {
			session = HibernateUtil.openSession();
			session.getTransaction().begin();
			session.persist(conversationMessage);
			session.getTransaction().commit();
			return conversationMessage.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return 0;
		} finally {
			session.close();
		}

	}

}
