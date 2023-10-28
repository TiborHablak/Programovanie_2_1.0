package db;

public class Osoba {
	public Long cislo_studenta;
	public String meno;
	public String priezvisko;
	public Double body;

	public Osoba(Long cislo_studenta, String meno, String priezvisko, Double body) {
		this.cislo_studenta = cislo_studenta;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.body = body;
	}

	public Osoba(Long cislo_studenta) {
		this.cislo_studenta = cislo_studenta;

	}

	public Osoba(Double body) {
		this.body = body;
	}
}
