package fi.utu.tech.ooj.exercise3.tehtava1;

import java.util.ArrayList;

public class T1Selitettävät {

}

// Tehtävät 1A ja 1B: selitä annettujen equals- ja clone-metodien toiminta. Luokat on toteutettu vain siltä
// osin kuin on tämän tehtävän kannalta tarpeellista.

/**
 * Teoskappale esittää kirjastosta löytyvää teoksen yksittäiskappaletta. Teoskappaleilla on numerot,
 * joiden avulla voidaan pitää kirjaa siitä, mitkä teoskappaleet ovat parhaillaan lainassa. Numerot
 * ovat yksikäsitteisiä vain saman teoksen eri kappaleille, eri teosten teoskappaleilla voi olla sama numero.
 * Lisäksi kirjaa pidetään teoskappaleen kunnosta sekä muutamasta viimeisimmästä lainaajasta.
 */
class Teoskappale {
    enum Kuntoluokitus {HYVÄ, OK, HUONO}

    private Teos teos;
    private int numero;
    private Kuntoluokitus kunto;
    private ArrayList<String> edellisetLainaajat;

    public Teoskappale(Teos t, int numero, Kuntoluokitus k) {
        teos = t;
        this.numero = numero;
        kunto = k;
        edellisetLainaajat = new ArrayList<>();
    }

    // 1A: selitä tämän metodin toiminta: 1) Kerro, mihin equals-metodeilta vaadittaviin ominaisuuksiin kukin
    // ohjelmakoodirivi liittyy. 2) Arvioi jokaisesta koodirivistä, toimisiko metodi oikein, mikäli ko.
    // rivi jätettäisiin pois ja toteutus pidettäisiin muilta osin samana.

    /**
     * 1. Ensimmäinen equals-metodin koodirivi perustuu ominaisuuteen jonka mukaan rutiinin on toimittava olioiden tyypistä huolimatta. 2. Ei toimisi, silä rivillä luodaan metodi.
     * 1. Toinen koodirivi perusustuu Null-epäsamuus ominaisuuteen eli, jos toinen on null palautetaan suoraan false. 2. Ei Toimisi, sillä jos olio toineno olisi null, silloin saattaisi tulla NullPointerException
     * 1. kolmas koodirivi perustuu refleksiivisyys ominaisuuteen eli "toinen" on oltava yhtäsuuri itsensä kanssa ja, 2. Toimisi, sillä toinen on identtisesti sama itsensä kanssa.
     *  jos toinen on oltava yhtäsuuri, kuin "this" eli luokan oma muodostin, joka on "toinen" eli identtisesti sama.
     *  Neljäs koodirivi perustuu ominaisuuteen jonka mukaan rutiinin on toimittava olioiden tyypistä huolimatta, eli jos oliot eivät ole 2. Toimisi, sillä instanceof palauttaa automaattisesti true jos se toteutuu ja kääntesesti false.
     *  yhteensopivia tyyppejä palautetaan false. Eli jos (toinen instanceof Teoskappale) palautetaan true ja muussa tapauksessa palautetaan false.
     *  Viidennellä rivillä pakotetaan toinen teoskappale-tyyppiseksi, jotta voidaan vertailla teoksia keskenään.
     *  Kuudennella rivillä vertaillaan primitiivityyppejä ja Stringissä delekoidaan equalsille, sillä vaikka ne olisivat samat ne voivat, kuitenkin olla eri muistipaikoissa 2. Ei toimisi, sillä boolean tarvitsee return lausekkeen.
     *  ja testataan numeroiden identtisyys == notaatiolla. Eli riviin liittyy ominaisuudet "Symmetrisyys", "Transitiivisuus" ja "Rutiinin on toimittava olioiden tyypistä huolimatta".
     *
     */
    @Override
    public boolean equals(Object toinen) {
        if (toinen == null) return false;
        if (toinen == this) return true;
        if (!(toinen instanceof Teoskappale)) return false;
        Teoskappale t2 = (Teoskappale)toinen;
        return t2.teos.equals(teos) && t2.numero == numero;
    }

    // 1B: selitä tämän metodin toiminta. Kerro erityisesti, millaisessa suhteessa tuloksena saadun Teoksen
    // viittaustyyppiset instanssimuuttujat ovat kloonattavaan Teokseen verrattuna: viittaavatko ne
    // samaan objektiin vai eri objekteihin, ja mitä merkitystä asialla on käytännössä.
    //
    // Huom: metodille ei ole suunniteltu mitään erityistä käyttötarkoitusta, joten selityksessä ei kannata
    // lähteä oletuksesta, että toiminta on kaikin puolin järkevää.
    @SuppressWarnings("unchecked")
    /**
     * Metodissa suoritetaan Teoskappaleelle pintapuolinen kloonaus, eli luodaan "tulos" niminen klooni Teoskappaleesta. Try-loopissa kutsutaan super.clone() metodia,
     * sillä jos määritettäisiin uusia tulos jäseniä super.clone() katoasisi näkyvistä uuden kloonin takia. Siksi käytetään super.clone():a, joka tarjoaa pääsyn ylempään
     * luokkaan ja aliluokka pystyy kopioimaan itsensä oikein.
     * Luokan instanssit teos ja kunto viittaavat samaan objektiin. Numero viittaa eri objektiin, sillä siinä on käytetty "this." avainsanaa.
     * Lisäksi ArrayList<String> edellisetLainaajat viittaa eri objektiin, sillä ei ole luokan konstruktori.
     * CloneNotSupportedException poikkeusta ei tepahdu sillä kloonattava implementoi kloonin.
     */
    @Override
    public Teoskappale clone() {
        Teoskappale tulos;
        try {
            tulos = (Teoskappale)super.clone();
        } catch (CloneNotSupportedException e) { // Tätä poikkeusta ei oikeasti pitäisi tapahtua
            throw new RuntimeException("Kloonaus epäonnistui!");
        }
        tulos.edellisetLainaajat = (ArrayList<String>) edellisetLainaajat.clone();
        return tulos;
    }


    // Pari esimerkkiä havainnointi- ja asetusmetodeista, loppuja metodeja ei toteutettu.
    public Kuntoluokitus annaKunto() {
        return kunto;
    }

    public void asetaKunto(Kuntoluokitus kunto) {
        this.kunto = kunto;
    }
}

/**
 * Teos esittää teokseen liittyviä perustietoja. (Toteutettu vain osin)
 */
class Teos implements Cloneable {
    String nimi;
    int julkaisuvuosi;

    public Teos(String nimi, int julkaisuvuosi) {
        this.nimi = nimi;
        this.julkaisuvuosi = julkaisuvuosi;
    }

    @Override
    public Teos clone() {
        try {
            return (Teos) super.clone();
        } catch (CloneNotSupportedException e) { // Tätä poikkeusta ei oikeasti pitäisi tapahtua
            throw new RuntimeException("Kloonaus epäonnistui!");
        }
    }
}