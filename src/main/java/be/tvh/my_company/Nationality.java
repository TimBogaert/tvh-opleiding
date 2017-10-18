package be.tvh.my_company;

/**
 * Created by tim036 on 29/09/2017.
 */
public enum Nationality {
    BELGIAN("Belgium"),
    DUTCH("Netherlands"),
    FRENCH("France"),
    GERMAN("Germany"),
    ENGLISH("United Kingdom");

    private String country;

    Nationality(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
