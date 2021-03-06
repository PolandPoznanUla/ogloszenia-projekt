package ogloszenia;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ogloszenia.model.Advertisement;
import ogloszenia.repository.AdvertisementRepository;


public class SearchAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String location;
		String phrase;
		
		location=req.getParameter("location");
		phrase = req.getParameter("phrase");
		
		if(phrase.isEmpty()) {
			resp.getWriter().write("proszę wpisać tekst");
		} else if(location.isEmpty()) {
			List<Advertisement> ad = AdvertisementRepository.findByPhrase(phrase);
		} else {
			List<Advertisement> ad = AdvertisementRepository.findByPhraseAndLocation(phrase, location);
		}
		
	}

}
