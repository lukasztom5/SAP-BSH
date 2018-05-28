package encje;

import encje.Czesc;
import encje.Zlecenie;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: CzescZlecenie
 *
 */
@Embeddable
@Table(name = "CZESC_ZLECENIE")
public class CzescZlecenie implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idczesc", nullable = false)
	private Czesc Czesc;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idzlecenie", nullable = false)
	private Zlecenie Zlecenie;
	private static final long serialVersionUID = 1L;

	public CzescZlecenie() {
		super();
	}   
	public Czesc getCzesc() {
		return this.Czesc;
	}

	public void setCzesc(Czesc Czesc) {
		this.Czesc = Czesc;
	}   
	public Zlecenie getZlecenie() {
		return this.Zlecenie;
	}

	public void setZlecenie(Zlecenie Zlecenie) {
		this.Zlecenie = Zlecenie;
	}
   
}
