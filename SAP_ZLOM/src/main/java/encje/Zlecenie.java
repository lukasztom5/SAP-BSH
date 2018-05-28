package encje;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ZLECENIE database table.
 * 
 */
@Entity
@Table(name="ZLECENIE")
@NamedQuery(name="Zlecenie.findAll", query="SELECT z FROM Zlecenie z")
public class Zlecenie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idzlecenie",unique = true, nullable = false)
	private int idzlecenie;
	@Column(name="numer")
	private long numer;

	public Zlecenie() {
	}

	public int getIdzlecenie() {
		return this.idzlecenie;
	}

	public void setIdzlecenie(int idzlecenie) {
		this.idzlecenie = idzlecenie;
	}

	public long getNumer() {
		return this.numer;
	}

	public void setNumer(long numer) {
		this.numer = numer;
	}

}