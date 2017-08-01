package ogloszenia;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ogloszenia.model.Advertisement;
import ogloszenia.repository.AdvertisementRepository;

public class AddToArchiveServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = null;
		
		try {
			Integer idInteger=Integer.valueOf(req.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		if(id != null) {
			Optional<Advertisement> advertisement = AdvertisementRepository.findById(id);
//			if(advertisement.isPresent()) {
//				
//			}
			advertisement.ifPresent(a -> disableAd(a));
		}
		PrintWriter writer = resp.getWriter();
		writer.write("ok");
	}

	private void disableAd(Advertisement a) {
		a.setIsActive(false);
		AdvertisementRepository.merge(a);
	}

	
	
}
