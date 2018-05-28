package encje;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PRODUKCJA_ZLECENIE database table.
 * 
 */
@Embeddable
@Table(name="PRODUKCJA_ZLECENIE")
@NamedQuery(name="ProdukcjaZlecenie.findAll", query="SELECT p FROM ProdukcjaZlecenie p")
public class ProdukcjaZlecenie implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idprodukcja", nullable = false)
	private Produkcja idprodukcja;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idzlecenie", nullable = false)
	private Zlecenie idzlecenie;

	public ProdukcjaZlecenie() {
	}

	public Produkcja getIdprodukcja() {
		return this.idprodukcja;
	}

	public void setIdprodukcja(Produkcja idprodukcja) {
		this.idprodukcja = idprodukcja;
	}

	public Zlecenie getIdzlecenie() {
		return this.idzlecenie;
	}

	public void setIdzlecenie(Zlecenie idzlecenie) {
		this.idzlecenie = idzlecenie;
	}

}