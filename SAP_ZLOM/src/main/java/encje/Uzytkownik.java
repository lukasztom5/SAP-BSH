package encje;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the UZYTKOWNIK database table.
 * 
 */
@Entity
@Table(name="UZYTKOWNIK")
@NamedQuery(name="Uzytkownik.findAll", query="SELECT u FROM Uzytkownik u")
public class Uzytkownik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="iduzytkownik",unique = true, nullable = false)
	private int iduzytkownik;
	@Column(name="nazwa")
	private String nazwa;

	public Uzytkownik() {
	}

	public int getIduzytkownik() {
		return this.iduzytkownik;
	}

	public void setIduzytkownik(int iduzytkownik) {
		this.iduzytkownik = iduzytkownik;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

}