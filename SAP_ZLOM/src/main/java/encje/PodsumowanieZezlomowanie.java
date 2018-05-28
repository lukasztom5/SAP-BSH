package encje;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PODSUMOWANIE_ZEZLOMOWANIE database table.
 * 
 */
@Embeddable
@Table(name="PODSUMOWANIE_ZEZLOMOWANIE")
public class PodsumowanieZezlomowanie implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idpodsumowanie", nullable = false)
	private int idpodsumowanie;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idzezlomowanie", nullable = false)
	private int idzezlomowanie;

	public PodsumowanieZezlomowanie() {
	}

	public int getIdpodsumowanie() {
		return this.idpodsumowanie;
	}

	public void setIdpodsumowanie(int idpodsumowanie) {
		this.idpodsumowanie = idpodsumowanie;
	}

	public int getIdzezlomowanie() {
		return this.idzezlomowanie;
	}

	public void setIdzezlomowanie(int idzezlomowanie) {
		this.idzezlomowanie = idzezlomowanie;
	}

}