package ogloszenia.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/*
 * Wiadomosc
    id ogloszenia
    id nadawca
    id odbiorca
    tresc
    data
 */

@Entity
public class Conversation {
	
	@Id
	@Column(name="id", unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Advertisement advertisementId;
	
	@JoinColumn(nullable=false)
	@ManyToOne
	private User conversationSender;
	
	@JoinColumn(nullable=false)
	@ManyToOne
	private User conversationReceiver;
	
	
	@OneToMany(mappedBy="conversation")
	Set<ConversationMessage> conversationMessage;
	
	@Column(nullable=false)
	private LocalDate messageDate;
	

	public Conversation() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Advertisement getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Advertisement advertisementId) {
		this.advertisementId = advertisementId;
	}

	public User getConversationSender() {
		return conversationSender;
	}

	public void setConversationSender(User messageSender) {
		this.conversationSender = messageSender;
	}

	public User getConversationReceiver() {
		return conversationReceiver;
	}

	public void setConversationReceiver(User messageReceiver) {
		this.conversationReceiver = messageReceiver;
	}



	public LocalDate getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(LocalDate messageDate) {
		this.messageDate = messageDate;
	}
	
	
	
	

}
