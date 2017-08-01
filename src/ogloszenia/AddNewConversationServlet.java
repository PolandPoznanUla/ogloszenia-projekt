package ogloszenia;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ogloszenia.model.Advertisement;
import ogloszenia.model.Conversation;
import ogloszenia.model.ConversationMessage;
import ogloszenia.model.User;
import ogloszenia.repository.AdvertisementRepository;



public class AddNewConversationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	
	protected void doPost(HttpServletRequest req, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String text;
		Integer idAdvertisement = 0;
		User messageSender = new User("Romek", "aaaa", "aaa@net.pl", "Poznan");
		
		
		
		text = req.getParameter("message");
		
		try {
			idAdvertisement = Integer.valueOf(req.getParameter("idAdvertisement"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(text.isEmpty() || idAdvertisement == 0) {
			PrintWriter writer = response.getWriter();
			writer.write("Error");
		} else {
			Conversation conversation = new Conversation();
			conversation.setMessageDate(LocalDate.now());
			Optional<Advertisement> ad = AdvertisementRepository.findById(idAdvertisement);
			if(ad.isPresent()) {
				conversation.setAdvertisement(ad.get());
				conversation.setMessageSender(messageSender);
				conversation.setMessageSender(ad.get().getOwner());
				
			ConversationMessage conversationMessage = new ConversationMessage();
			
			conversation.setMessageDate(LocalDate.now());
			
			}
			
		}
	}

}
