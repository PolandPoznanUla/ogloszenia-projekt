package ogloszenia.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import ogloszenia.model.Image;
import ogloszeniar.hibernate.util.HibernateUtil;

public class ImageRepository {
	
	public static List<Image> findByAdvertisementId(Integer id) {
		Session session = null;
		
		try {
			session = HibernateUtil.openSession().getSession();
			String hql = "SELECT e FROM Image e WHERE e.advertisementId.id=:id";
			Query query = session.createQuery(hql, Image.class);
			query.setParameter("id", id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			session.close();
		}
	}
	
	
	
	

}
