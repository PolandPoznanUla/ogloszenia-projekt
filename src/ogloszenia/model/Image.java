package ogloszenia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/*
 * Zdjecie
    id ogloszenia
    zdjecie
    
 */

@Entity
public class Image {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Advertisement advertisement;
	
	@Lob
	private byte[] img;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Advertisement getAdvertisementId() {
		return advertisement;
	}

	public void setAdvertisementId(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Image() {}
	
	
	

}
