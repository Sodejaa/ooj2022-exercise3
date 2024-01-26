package fi.utu.tech.ooj.exercise3.tehtava2;

import java.util.Random;

/**
 * Lukukokoelma esittää listaa, jonka alkiot ovat Number-tyyppisiä lukuja.
 * 
 * Huom: luokan toteutuksesta ja määrittelyistä (loppuehdot, luokkainvariantit) on pääosin jätetty pois sellaiset
 * asiat, jotka eivä ole tehtävän kannalta tärkeitä. Yksinkertaisuussyistä kokoelmalla on mm. maksimikapasiteetti, 
 * joka annetaan parametrina konstruktorille.
 * 
 * @.classInvariant:  koko() <= kapasiteetti()
 * @.classInvariantPrivate koko() == viimeinenIndeksi && (listan alkiot on tallennettu taulukkoon
 *                         luvut indekseihin 0, 1, ..., viimeinenIndeksi)
 */
class Lukukokoelma {
	private Number[] luvut;
	private int viimeinenIndeksi = -1;
	
	public Lukukokoelma(int maksimiKapasiteetti) {
		luvut = new Number[maksimiKapasiteetti];
		
	}
	
	/**
	 * Palauttaa suurimman mahdollisen listaan tallennettavien alkioiden määrän. 
	 */
	public int kapasiteetti() { 
		return luvut.length;
	}
	
	/**
	 * Palauttaa listan nykyisen koon.
	 * @.pre: true
	 * @.post: RESULT == (listassa olevien alkioiden lukumäärä)
	 */
	public int koko() {
		return viimeinenIndeksi + 1;
	}
	
	/**
	 * Lisää luvun n listan viimeiseksi alkioksi.
	 * 
	 *  @.pre koko() < kapasiteetti()
	 *  @.post hae(koko()-1) == n
	 */
	public void lisää(Number n) {
		luvut[++viimeinenIndeksi] = n;
	}
	
	/**
	 * Palauttaa listan paikassa i olevan alkion.
	 * 
	 * @.pre 0 <= i < koko()
	 * @.post RESULT == (listan paikassa i oleva alkio)
	 */
	public Number hae(int i) {
		return luvut[i];
	}
}

/**
 * Lukukokoelma, jonka alkiot ovat tyyppiä Integer.
 * 
 * @.classInvariant:  koko() <= kapasiteetti() && FORALL(i : 0 <= i < koko(); hae(i) instanceof Integer)
 *
 */
class Kokonaislukukokoelma extends Lukukokoelma {
	public Kokonaislukukokoelma(int maksimiKapasiteetti) {
		super(maksimiKapasiteetti);
	}
	
	/**
	 *  Lisää luvun n listan viimeiseksi alkioksi.
	 * 
	 *  @.pre koko() < kapasiteetti() && n instanceof Integer
	 *  @.post hae(koko()-1) == n
	 */
	@Override
	public void lisää(Number n) {
		if (!(n instanceof Integer)) {
			throw new NumberFormatException("Vääräntyyppinen luku: " + n);
		}
		super.lisää(n);
	}
	
	/**
	 * Palauttaa listan paikassa i olevan alkion.
	 * 
	 * @.pre 0 <= i < koko()
	 * @.post RESULT == (listan paikassa i oleva alkio)
	 */
	@Override
	public Integer hae(int i) {
		return (Integer) super.hae(i);
	}

	public static void main(String[] args) {
		Lukukokoelma l = new Lukukokoelma(5);
		Kokonaislukukokoelma k = new Kokonaislukukokoelma(5);
		System.out.println(l.kapasiteetti());
		l.lisää(5);
		k.lisää(5.5);
		l.lisää(5.5);
		System.out.println((k.hae(0)));
		System.out.println(l.hae(1));
		System.out.println(l.koko());
		/**
		 * A. Ohjelma ei toimi, sillä yritetään lisätä vääräntyyppinen arvo. Ohjelma ei tarkasta lisätäänkö Integer,
		 * tyyppinen arvo.
		 * B.Tehtävän Palautustyyppi ja loppuehto toimivat kovarianssi säännöllä, koska rutiinin palautustyyppi ja loppuehto ovat samat, kuin yläluokalla.
		 * Alkuehdossa pätee kontravariantti sääntö, koska tehtävä toimii pelkästään kokonaisluvulla, sekä kääntää tyypin järjestyksen.
		 * C. Tehtävän Alkuehto ja loppuehto seuraavat kovarianssi sääntöä eli ne eivät käännä tyyppin järjestystä. Loppuehto noudattaa
		 * kontravariantti sääntöä, koska se palauttaa eri tyyppisen arvon rutiinista ja siten muuttaa tyyppien järjestyksen käänteiseksi. (Interger)
		 * D. Tehtävän Luokka invarianteissa noudattaa kovariantti sääntö, koska Kokonaislukukokoelmassa määritellään muuttujien olevan kokonaislukuja, joka osittain tiukentaa ehtoja.
		 */
	}
}
