package ogloszenia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Image {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(unique=true)
private Integer id;


@ManyToOne
@JoinColumn(nullable=false)
private Advertisement advertisementId;

@Lob
private byte[] img;

}
