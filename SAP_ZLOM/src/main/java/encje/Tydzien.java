package encje;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the TYDZIEN database table.
 * 
 */
@Entity
@Table(name="TYDZIEN")
@NamedQuery(name="Tydzien.findAll", query="SELECT t FROM Tydzien t")
public class Tydzien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idtydzien",unique = true, nullable = false)
	private int idtydzien;
	@Column(name="tydzien")
	private int tydzien;

	public Tydzien() {
	}

	public int getIdtydzien() {
		return this.idtydzien;
	}

	public void setIdtydzien(int idtydzien) {
		this.idtydzien = idtydzien;
	}

	public int getTydzien() {
		return this.tydzien;
	}

	public void setTydzien(int tydzien) {
		this.tydzien = tydzien;
	}

}