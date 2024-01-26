package fi.utu.tech.ooj.exercise3;

import fi.utu.tech.ooj.exercise3.tehtava1.Jäsenrekisteri;
import fi.utu.tech.ooj.exercise3.tehtava5.Säähavainto;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static fi.utu.tech.ooj.exercise3.tehtava5.Säähavainto.*;


public class Main {
    public static void main(String[] args) {
        // TODO: kutsu tästä eri tehtävien suoritettavia demonstraatio-osuuksia

        //1.
        Jäsenrekisteri uusi1 = new Jäsenrekisteri("uusi1");
        Jäsenrekisteri uusi2 = new Jäsenrekisteri("uusi2");
        Jäsenrekisteri.Jäsen eetu = new Jäsenrekisteri.Jäsen("eetu", "123abc");
        Jäsenrekisteri.Jäsen omar = new Jäsenrekisteri.Jäsen("omar", "12345abcef");
        Jäsenrekisteri.Jäsen elias = new Jäsenrekisteri.Jäsen("elias", "1234567abcefgh");
        //lisätään jäsenet
        uusi1.lisääJäsen(eetu);
        uusi2.lisääJäsen(eetu);
        uusi1.lisääJäsen(omar);
        uusi2.lisääJäsen(omar);
        //kloonataan lista
        Jäsenrekisteri uusi1K = uusi1.clone();
        //
        uusi1.lisääJäsen(elias);
        //päivitetään nimi
        eetu.asetaNimi("eetu-päivitetty");
        // tulostetaan listan jäsenet
        for (Jäsenrekisteri.Jäsen str : uusi1K.jäsenet)
            System.out.println("Ensimmäinen: " + str.annaNimi());
        // tulostetaan toisen listan jäsenet
        for (Jäsenrekisteri.Jäsen str : uusi1.jäsenet)
            System.out.println("Toinen: " + str.annaNimi());
        // testataan equals metodia
        System.out.println(uusi1.equals(uusi2));


        //5.
        //Luodaan uusi havaintolista
        ArrayList<Säähavainto> jokulista = new ArrayList<>();
        //Lisätään 10 havaintoa
        for (int i = 0; i < 10; i++) {
            jokulista.add(keksi());
        }
        System.out.println("Tulostetaan alkuperäinen lista...");
        // tulostetaan alkuperäinen lista
        for (int i = 0; i < jokulista.size(); i++) {
            System.out.println(jokulista.get(i));
        }
        System.out.println("Järjestetään havainnot vanhimmasta uusimpaan...");
        //järjestetnään lista käyttäen Collections.sort metodia
        Collections.sort(jokulista, Comparator.comparing(Säähavainto::getAika));
        //tulostetaan uudelleen järjestetty lista
        for (int i = 0; i < jokulista.size(); i++) {
            System.out.println(jokulista.get(i));
        }
        System.out.println("Järjestetään pituusasteen mukaan...");
        Collections.sort(jokulista, Comparator.comparing(Säähavainto::getPituusaste));
        //tulostetaan uudelleen järjestetty lista
        for (int i = 0; i < jokulista.size(); i++) {
            System.out.println(jokulista.get(i));
        }
        System.out.println("Järjestetään leveysasteen mukaan...");
        Collections.sort(jokulista, Comparator.comparing(Säähavainto::getLeveysaste));
        //tulostetaan uudelleen järjestetty lista
        for (int i = 0; i < jokulista.size(); i++) {
            System.out.println(jokulista.get(i));
        }
        System.out.println("Järjestetään sijainnin mukaan...");
        Collections.sort(jokulista, Comparator.comparing(Säähavainto::getPaikka));
        //tulostetaan uudelleen järjestetty lista
        for (int i = 0; i < jokulista.size(); i++) {
            System.out.println(jokulista.get(i));
        }
    }
}