
package worldstatistics;

/**
 *
 * @author User
 */
public class City {

    private String name;
    private String countryCode;
    private int populationCity;
    private Country country;

    public City(String name, String countryCode, int populationCity) {
        this.name = name;
        this.countryCode = countryCode;
        this.populationCity = populationCity;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getPopulationCity() {
        return populationCity;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

//Bónusz A City osztályba írj egy 
//public double getPopulationPercentage()
//metódust, ami visszaadja, hogy az adott város populációja hány százaléka 
//az anyaország populációjának! Ha akár a város, akár az anyaország populációja nincs megadva 
//metódusod -1-gyel térjen vissza!
    public double getPopulationPercentage() {
        double percentage = -1;
        if (this.populationCity == 0 || this.country.getPopulationCountry() == 0) {
            return -1;
        } else {
            percentage = (double) this.populationCity / this.country.getPopulationCountry();
        }
        return percentage * 100;
    }

}
