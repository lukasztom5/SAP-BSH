package encje;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="CZESC")
@NamedQuery(name="Czesc.findAll", query="SELECT c FROM Czesc c")
public class Czesc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idczesc",unique = true, nullable = false)
	private int idczesc;

	@Column(name="numer_materialu")
	private long numerMaterialu;
	@Column(name="opis")
	private String opis;

	public Czesc() {
	}

	public int getIdczesc() {
		return this.idczesc;
	}

	public void setIdczesc(int idczesc) {
		this.idczesc = idczesc;
	}

	public long getNumerMaterialu() {
		return this.numerMaterialu;
	}

	public void setNumerMaterialu(long numerMaterialu) {
		this.numerMaterialu = numerMaterialu;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

}