package fi.utu.tech.ooj.exercise3.tehtava1;

import java.util.ArrayList;

public class Jäsenrekisteri implements Cloneable{
    private String nimi;  // jäsenrekisterin nimi
    public ArrayList<Jäsen> jäsenet; // equals-metodin suorituskyvyn kannalta ei paras mahdollinen tietorakenne

    public Jäsenrekisteri(String nimi) {
        this.nimi = nimi;
        jäsenet = new ArrayList<>();
    }


    public boolean lisääJäsen(Jäsen j) {
        if (jäsenet.contains(j)) return false;
        jäsenet.add(j);
        return true;
    }

    public boolean poistaJäsen(Jäsen j) {
        if (jäsenet.contains(j)) return false;
        jäsenet.remove(j);
        return true;
    }

    @Override
    public boolean equals(Object rekisteri1) {
        boolean sama = false;
        if (rekisteri1 == null) return false;
        if (rekisteri1 == this) return true;
        if (!(rekisteri1 instanceof Jäsenrekisteri)) return false;
        Jäsenrekisteri r1 = (Jäsenrekisteri) rekisteri1;
        if(this.jäsenet.size() != r1.jäsenet.size()) return false;
            for (Jäsen j1 : this.jäsenet) {
                for (Jäsen j2 : r1.jäsenet) {
                    if (j1.annaHetu() == j2.annaHetu()) {
                        sama = true;
                        break;
                    }
                }
                if (sama == false) {
                    return false;
                }
                sama = false;
            }
        return true;
    }


    @Override
    public Jäsenrekisteri clone() {
        Jäsenrekisteri rekisteri;
        try {
            rekisteri = (Jäsenrekisteri)super.clone();
        } catch (CloneNotSupportedException e) { // Tätä poikkeusta ei oikeasti pitäisi tapahtua
            throw new RuntimeException("Kloonaus epäonnistui!");
        }
        rekisteri.jäsenet = (ArrayList<Jäsen>) jäsenet.clone();
        return rekisteri;
    }

   public static class Jäsen {
        private String nimi;
        private String hetu;

        public Jäsen(String nimi, String hetu) {
            this.nimi = nimi;
            this.hetu = hetu;
        }

        public String annaNimi() {
            return nimi;
        }

        public void asetaNimi(String nimi) {
            this.nimi = nimi;
        }

        public String annaHetu() {
            return hetu;
        }

        public void asetaHetu(String hetu) {
            this.hetu = hetu;
        }
    }
}