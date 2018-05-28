package encje;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ROK database table.
 * 
 */
@Entity
@Table(name="ROK")
@NamedQuery(name="Rok.findAll", query="SELECT r FROM Rok r")
public class Rok implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idrok",unique = true, nullable = false)
	private int idrok;
	@Column(name="rok")
	private int rok;

	public Rok() {
	}

	public int getIdrok() {
		return this.idrok;
	}

	public void setIdrok(int idrok) {
		this.idrok = idrok;
	}

	public int getRok() {
		return this.rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

}