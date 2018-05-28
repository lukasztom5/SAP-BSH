package encje;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PRODUKCJA database table.
 * 
 */
@Entity
@Table(name="PRODUKCJA")
@NamedQuery(name="Produkcja.findAll", query="SELECT p FROM Produkcja p")
public class Produkcja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idprodukcja",unique = true, nullable = false)
	private int idprodukcja;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idlinia", nullable = false)
	private Obszar idlinia;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idrok", nullable = false)
	private Rok idrok;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idtydzien", nullable = false)
	private Tydzien idtydzien;

	public Produkcja() {
	}

	public int getIdprodukcja() {
		return this.idprodukcja;
	}

	public void setIdprodukcja(int idprodukcja) {
		this.idprodukcja = idprodukcja;
	}

	public Obszar getIdlinia() {
		return this.idlinia;
	}

	public void setIdlinia(Obszar idlinia) {
		this.idlinia = idlinia;
	}

	public Rok getIdrok() {
		return this.idrok;
	}

	public void setIdrok(Rok idrok) {
		this.idrok = idrok;
	}

	public Tydzien getIdtydzien() {
		return this.idtydzien;
	}

	public void setIdtydzien(Tydzien idtydzien) {
		this.idtydzien = idtydzien;
	}

}