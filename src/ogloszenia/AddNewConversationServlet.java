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
import ogloszenia.repository.ConversationMessageRepository;

/**
 * Servlet implementation class AddNewMessageServlet
 */
public class AddNewConversationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String text;
		Integer idAdvertisement = 0;
		User messageSender = new User("Romek", "11111", "romek@gmail.com", "Poznan");

		text = req.getParameter("message");
		try {
			idAdvertisement = Integer.valueOf(req.getParameter("idAdvertisement"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		Optional<Advertisement> ad = AdvertisementRepository.findById(idAdvertisement);
		if (text.isEmpty() || !ad.isPresent()) {
			PrintWriter writer = res.getWriter();
			writer.write("Error!!");
		} else {
			Conversation conversation = new Conversation();
			conversation.setMessageDate(LocalDate.now());
			conversation.setAdvertisementId(ad.get());
			conversation.setConversationSender(messageSender);
			conversation.setConversationReceiver(ad.get().getOwner());
			ConversationMessage conversationMessage = new ConversationMessage(text, conversation, messageSender);
			
			ConversationMessageRepository.persist(conversationMessage);
			
			res.getWriter().write("wiadomosc zostala wyslana");
			
		}

	}

}
