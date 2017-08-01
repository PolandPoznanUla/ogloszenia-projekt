package ogloszenia.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ConversationMessage {

	@Id
	Integer id;
	
	@Column(nullable=false)
	private String messageContent;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn
	Conversation conversation;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn
	User owner;
		
	public ConversationMessage() {
		
	}

	public ConversationMessage(String messageContent, Conversation conversation, User owner) {
		this.messageContent = messageContent;
		this.conversation = conversation;
		this.owner = owner;
	}

	public Conversation getConversation() {
		return conversation;
	}

	

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
}
