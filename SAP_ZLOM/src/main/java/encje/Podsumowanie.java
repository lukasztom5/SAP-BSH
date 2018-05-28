package encje;

import java.io.Serializable;

import javax.persistence.*;



/**
 * The persistent class for the PODSUMOWANIE database table.
 * 
 */
@Entity
@Table(name="PODSUMOWANIE")
@NamedQuery(name="Podsumowanie.findAll", query="SELECT p FROM Podsumowanie p")
public class Podsumowanie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idpodsumowanie",unique = true, nullable = false)
	private int idpodsumowanie;

	@Column(name="cena_na_liczba_zm")
	private String cenaNaLiczbaZm;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idprodukcja", nullable = false)
	private Produkcja idprodukcja;

	@Column(name="jedn_na_sztuki")
	private String jednNaSztuki;

	@Column(name="koszt_zlomowania")
	private String kosztZlomowania;
	
	@Column(name="liczba_zezl")
	private String liczbaZezl;

	@Column(name="liczba_zmywarek")
	private int liczbaZmywarek;
	@Column(name="rok")
	private int rok;
	@Column(name="tydzien")
	private int tydzien;

	@Column(name="wal_na_szt")
	private String walNaSzt;

	@Column(name="zezl_na_liczba_zmyw")
	private String zezlNaLiczbaZmyw;

	public Podsumowanie() {
	}

	public int getIdpodsumowanie() {
		return this.idpodsumowanie;
	}

	public void setIdpodsumowanie(int idpodsumowanie) {
		this.idpodsumowanie = idpodsumowanie;
	}

	public String getCenaNaLiczbaZm() {
		return this.cenaNaLiczbaZm;
	}

	public void setCenaNaLiczbaZm(String cenaNaLiczbaZm) {
		this.cenaNaLiczbaZm = cenaNaLiczbaZm;
	}

	public Produkcja getIdprodukcja() {
		return this.idprodukcja;
	}

	public void setIdprodukcja(Produkcja idprodukcja) {
		this.idprodukcja = idprodukcja;
	}

	public String getJednNaSztuki() {
		return this.jednNaSztuki;
	}

	public void setJednNaSztuki(String jednNaSztuki) {
		this.jednNaSztuki = jednNaSztuki;
	}

	public String getLiczbaZezl() {
		return this.liczbaZezl;
	}

	public void setLiczbaZezl(String liczbaZezl) {
		this.liczbaZezl = liczbaZezl;
	}

	public int getLiczbaZmywarek() {
		return this.liczbaZmywarek;
	}

	public void setLiczbaZmywarek(int liczbaZmywarek) {
		this.liczbaZmywarek = liczbaZmywarek;
	}

	public int getRok() {
		return this.rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

	public int getTydzien() {
		return this.tydzien;
	}

	public void setTydzien(int tydzien) {
		this.tydzien = tydzien;
	}

	public String getWalNaSzt() {
		return this.walNaSzt;
	}

	public void setWalNaSzt(String walNaSzt) {
		this.walNaSzt = walNaSzt;
	}

	public String getZezlNaLiczbaZmyw() {
		return this.zezlNaLiczbaZmyw;
	}

	public void setZezlNaLiczbaZmyw(String zezlNaLiczbaZmyw) {
		this.zezlNaLiczbaZmyw = zezlNaLiczbaZmyw;
	}
	
	public String getKosztZlomowania() {
		return this.kosztZlomowania;
	}

	public void setKosztZlomowania(String kosztZlomowania) {
		this.kosztZlomowania = kosztZlomowania;
	}

}