package ogloszenia;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ogloszenia.model.Advertisement;
import ogloszenia.model.User;
import ogloszenia.repository.AdvertisementRepository;

public class AddNewAdServlet extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title;
		BigDecimal price = BigDecimal.ZERO;
		String description;
		String location;
		
		title = req.getParameter("title");
		try {
			price = new BigDecimal(req.getParameter("price"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		description = req.getParameter("description");
		location = req.getParameter("location");
		
		if(isNotValid(title, price, description)) {
			PrintWriter pw = resp.getWriter();
			pw.write("error");
		}
		
		User owner = new User();
		owner.setCityName("Poznañ");
		owner.setEmail("test@home");
		owner.setNick("test");
		owner.setPassword("admin");
		
		Advertisement ad = new Advertisement(title, price, description, location, owner);
		AdvertisementRepository.persist(ad);
		PrintWriter pw = resp.getWriter();
		pw.write("ok");
	}

	private boolean isNotValid(String title, BigDecimal price, String description) {
		return title.isEmpty() || description.isEmpty() || price.compareTo(BigDecimal.ZERO) == -1;
	}

	
	
}
