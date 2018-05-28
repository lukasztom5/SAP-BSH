package encje;

import java.io.Serializable;

import javax.persistence.*;



/**
 * The persistent class for the PODSUMOWANIE_SKUMULOWANE database table.
 * 
 */
@Entity
@Table(name="PODSUMOWANIE_SKUMULOWANE")
@NamedQuery(name="PodsumowanieSkumulowane.findAll", query="SELECT p FROM PodsumowanieSkumulowane p")
public class PodsumowanieSkumulowane implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idpodsumowanie_skum")
	private int idpodsumowanieSkum;

	@Column(name="cena_na_liczba_calk")
	private String cenaNaLiczbaCalk;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idprodukcja", nullable = false)
	private Produkcja idprodukcja;

	@Column(name="jedn_na_sztuki")
	private String jednNaSztuki;

	@Column(name="koszt_zlomowania_skum")
	private String kosztZlomowaniaSkum;

	@Column(name="liczba_zezl_skum")
	private String liczbaZezlSkum;

	@Column(name="liczba_zmywarek_skum")
	private int liczbaZmywarekSkum;

	@Column(name="rok")
	private int rok;
	@Column(name="tydzien")
	private int tydzien;

	@Column(name="wal_na_szt")
	private String walNaSzt;

	@Column(name="zezl_na_liczba_zmyw_calk")
	private String zezlNaLiczbaZmywCalk;

	public PodsumowanieSkumulowane() {
	}

	public int getIdpodsumowanieSkum() {
		return this.idpodsumowanieSkum;
	}

	public void setIdpodsumowanieSkum(int idpodsumowanieSkum) {
		this.idpodsumowanieSkum = idpodsumowanieSkum;
	}

	public String getCenaNaLiczbaCalk() {
		return this.cenaNaLiczbaCalk;
	}

	public void setCenaNaLiczbaCalk(String cenaNaLiczbaCalk) {
		this.cenaNaLiczbaCalk = cenaNaLiczbaCalk;
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

	public String getKosztZlomowaniaSkum() {
		return this.kosztZlomowaniaSkum;
	}

	public void setKosztZlomowaniaSkum(String kosztZlomowaniaSkum) {
		this.kosztZlomowaniaSkum = kosztZlomowaniaSkum;
	}

	public String getLiczbaZezlSkum() {
		return this.liczbaZezlSkum;
	}

	public void setLiczbaZezlSkum(String liczbaZezlSkum) {
		this.liczbaZezlSkum = liczbaZezlSkum;
	}

	public int getLiczbaZmywarekSkum() {
		return this.liczbaZmywarekSkum;
	}

	public void setLiczbaZmywarekSkum(int liczbaZmywarekSkum) {
		this.liczbaZmywarekSkum = liczbaZmywarekSkum;
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

	public String getZezlNaLiczbaZmywCalk() {
		return this.zezlNaLiczbaZmywCalk;
	}

	public void setZezlNaLiczbaZmywCalk(String zezlNaLiczbaZmywCalk) {
		this.zezlNaLiczbaZmywCalk = zezlNaLiczbaZmywCalk;
	}

}