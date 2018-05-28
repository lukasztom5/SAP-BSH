package encje;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the OBSZAR database table.
 * 
 */
@Entity
@Table(name="OBSZAR")
@NamedQuery(name="Obszar.findAll", query="SELECT o FROM Obszar o")
public class Obszar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idobszar",unique = true, nullable = false)
	private int idobszar;
	@Column(name="obszar")
	private String obszar;

	public Obszar() {
	}

	public int getIdobszar() {
		return this.idobszar;
	}

	public void setIdobszar(int idobszar) {
		this.idobszar = idobszar;
	}

	public String getObszar() {
		return this.obszar;
	}

	public void setObszar(String obszar) {
		this.obszar = obszar;
	}

}