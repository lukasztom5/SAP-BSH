package encje;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ZEZLOMOWANIE database table.
 * 
 */
@Entity
@Table(name="ZEZLOMOWANIE")
@NamedQuery(name="Zezlomowanie.findAll", query="SELECT z FROM Zezlomowanie z")
public class Zezlomowanie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idzezlomowanie",unique = true, nullable = false)
	private int idzezlomowanie;
	@Column(name="cena")
	private BigDecimal cena;

	@Column(name="data_zlomowania")
	private Date dataZlomowania;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idczesc", nullable = false)
	private Czesc idczesc;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "iduzytkownik", nullable = false)
	private Uzytkownik iduzytkownik;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idzlecenie", nullable = false)
	private Zlecenie idzlecenie;

	@Column(name="liczba_zezlomowanych")
	private BigDecimal liczbaZezlomowanych;
	@Column(name="rok")
	private int rok;
	@Column(name="tydzien")
	private int tydzien;
	@Column(name="waluta")
	private String waluta;
	@Column(name="wartosc")
	private String wartosc;

	public Zezlomowanie() {
	}

	public int getIdzezlomowanie() {
		return this.idzezlomowanie;
	}

	public void setIdzezlomowanie(int idzezlomowanie) {
		this.idzezlomowanie = idzezlomowanie;
	}

	public BigDecimal getCena() {
		return this.cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public Date getDataZlomowania() {
		return this.dataZlomowania;
	}

	public void setDataZlomowania(Date dataZlomowania) {
		this.dataZlomowania = dataZlomowania;
	}

	public Czesc getIdczesc() {
		return this.idczesc;
	}

	public void setIdczesc(Czesc idczesc) {
		this.idczesc = idczesc;
	}

	public Uzytkownik getIduzytkownik() {
		return this.iduzytkownik;
	}

	public void setIduzytkownik(Uzytkownik iduzytkownik) {
		this.iduzytkownik = iduzytkownik;
	}

	public Zlecenie getIdzlecenie() {
		return this.idzlecenie;
	}

	public void setIdzlecenie(Zlecenie idzlecenie) {
		this.idzlecenie = idzlecenie;
	}

	public BigDecimal getLiczbaZezlomowanych() {
		return this.liczbaZezlomowanych;
	}

	public void setLiczbaZezlomowanych(BigDecimal liczbaZezlomowanych) {
		this.liczbaZezlomowanych = liczbaZezlomowanych;
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

	public String getWaluta() {
		return this.waluta;
	}

	public void setWaluta(String waluta) {
		this.waluta = waluta;
	}

	public String getWartosc() {
		return this.wartosc;
	}

	public void setWartosc(String wartosc) {
		this.wartosc = wartosc;
	}

}