package ogloszenia;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ogloszenia.model.User;
import ogloszenia.repository.UserRepository;

public class RegisterUserServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nick;
		String password;
		String email;
		String location;
		
		nick = req.getParameter("nick");
		password =req.getParameter("password");
		email = req.getParameter("email");
		location = req.getParameter("location");
		
		if(isNotValid(nick, password, email, location))
		{
			PrintWriter writer = resp.getWriter();
			writer.write("blad");
		}
		else {
			User user = new User(nick, password, email, location);
			UserRepository.persist(user);
			PrintWriter writer = resp.getWriter();
			writer.write("ok");
		}
	}

	private boolean isNotValid(String nick, String password, String email, String location) {
		return nick.isEmpty() || password.isEmpty() || email.isEmpty() || location.isEmpty();
	}
	
}
