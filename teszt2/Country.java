
package worldstatistics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author User
 */
public class Country {

    private String countryCode;
    private String name;
    private String continent;
    private String region;
    private double area;
    private int independence;
    private int populationCountry;
    private String president;
    private ArrayList<City> myCities;

    public Country(String countryCode, String name, String continent, String region, double area, int independence, int populationCountry, String president) {
        this.countryCode = countryCode;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.area = area;
        this.independence = independence;
        this.populationCountry = populationCountry;
        this.president = president;
        this.myCities = new ArrayList<>();

    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public double getArea() {
        return area;
    }

    public int getIndependence() {
        return independence;
    }

    public int getPopulationCountry() {
        return populationCountry;
    }

    public String getPresident() {
        return president;
    }

    public List<City> getCities() {
        return myCities;
    }

//A Country osztályba írj egy 
//public double getPopulationDensity()
//metódust, ami visszaadja az adott ország népsűrűségét fő/km2-ben! 
//Ha az ország területe vagy népessége nincs megadva, akkor metódusod -1-gyel térjen vissza!
//    this.area = area 0  
//    this.populationCountry = populationCountry  0
    public double getPopulationDensity() {

        if (this.area == 0 || this.populationCountry == 0) {
            return -1;
        }
        double popDens = populationCountry / area;
        return popDens;
    }

//    A Country osztályba írj egy 
//public double getRuralPopulation()
//metódust, ami visszaadja, hogy az adott ország-ban hány fő él a felsorolt városokon kívül!
    public double getRuralPopulation() {
        // az országhoz tartozó városok lakosságát kell kivonni az ország lakosságából

        double pop = (double) populationCountry;
        int sumOfMyCitiesPop = 0;
        // végig iterálok a városok listáján és a lakosságokat összegzem
        for (City actCity : myCities) {
            sumOfMyCitiesPop += actCity.getPopulationCity();
        }

        return pop - sumOfMyCitiesPop;
    }

}
