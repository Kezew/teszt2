
package worldstatistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class WorldStatistics {

    static HashMap<String, Country> countries = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        readFile();

// népsűrűség teszt
//        for (String orsz : countries.keySet()) {
//            System.out.println(countries.get(orsz).getName() + ", " + countries.get(orsz).getPopulationDensity());
//        }
        //System.out.println(countries.get("CHN").getRuralPopulation());
        //getCitiesOfCountry("AFG");
        //getCountriesOfContinent("Oceania");
        //getAhmedCount();
        //lastIndependentCountry();
        //getPopularFirstLetter();
// szorgalmi ellenőrzés        
// for (int i = 0; i < countries.get("AFG").getCities().size(); i++) {
//      City c = countries.get("AFG").getCities().get(i);
//      System.out.println(c.getName() + ": " + c.getPopulationPercentage());
// }
    }

//Írj egy olyan metódust, amely visszaadja egy paraméterül kapott kontinens 
//országainak az országkódjait! A metódus szignatúrája az alábbi legyen:
//ArrayList<String> getCountriesOfContinent(String continentName)
    public static ArrayList<String> getCountriesOfContinent(String continentName) {

        ArrayList<String> countryCodesOfContinent = new ArrayList<>();
        // végigiterálok a countries HashMap-en és ha a kontinens megegyezik a paraméterrel akkor az ország kódját belerakom az itteni ArrayList-be
        for (String country : countries.keySet()) {
            if (countries.get(country).getContinent().equals(continentName)) {
                countryCodesOfContinent.add(countries.get(country).getCountryCode());
            }
        }
        return countryCodesOfContinent;
    }

//Írj egy olyan metódust, amely visszaadja egy paraméterül kapott ország városainak a neveit 
//(az országot országkóddal adjuk meg)! A metódus szignatúrája az alábbi legyen:
//HashSet<String> getCitiesOfCountry(String countryCode)
    public static HashSet<String> getCitiesOfCountry(String countryCode) {
        HashSet<String> citiesOfGivenCountry = new HashSet<>();
        for (String code : countries.keySet()) {
            if (code.equals(countryCode)) {
                for (int i = 0; i < countries.get(code).getCities().size(); i++) {
                    String actCityName = countries.get(code).getCities().get(i).getName();
                    citiesOfGivenCountry.add(actCityName);

                }
            }
        }
        return citiesOfGivenCountry;
    }

//Hány országnak az államfőjének nevében szerepel “Hamad” vagy “Ahmad” vagy “Ahmed”? 
//A metódus szignatúrája az alábbi legyen:
//int getAhmedCount()
    public static int getAhmedCount() {
        int counter = 0;
        // iteráció a countries HashMap-en megnézni, hogy a president név tartalmaza-e?
        for (String country : countries.keySet()) {
            if (countries.get(country).getPresident().contains("Hamad")) {
                counter++;
                //System.out.println(countries.get(country).getPresident());
            }
            if (countries.get(country).getPresident().contains("Ahmad")) {
                counter++;
                //System.out.println(countries.get(country).getPresident());
            }
            if (countries.get(country).getPresident().contains("Ahmed")) {
                counter++;
                //System.out.println(countries.get(country).getPresident());
            }
        }
        //System.out.println(counter);
        return counter;
    }

//Melyik betűvel kezdődik a legtöbb országkód? A visszatérési érték egy egybetűs String, 
//a metódus szignatúrája pedig az alábbi legyen:
//String getPopularFirstLetter()
    public static String getPopularFirstLetter() {
        String popularLetter = "";
        int maxNumPopLetter = 0;
        // itt tárolom a kezdőbetűk előfordulásait
        HashMap<String, Integer> lettersFrequency = new HashMap<>();

        for (String country : countries.keySet()) {
            // eldönteni, hogy a key első betűje benne van-e már a lettersFrequency HashMap-ben??
            String firstLetter = "" + country.charAt(0);
            if (lettersFrequency.containsKey(firstLetter)) {  // ha már benne van akkor a kulcshoz tartozó értéket növeljük eggyel
                int actValue = lettersFrequency.get(firstLetter);
                lettersFrequency.put(firstLetter, actValue + 1);

            } else {    // még nincs ilyen kulcs >>> tegyük bele 1-es értékkel
                lettersFrequency.put(firstLetter, 1);
            }
        }
        // maximum keresés és tárolás (betű és előfordulás) 
        for (String letter : lettersFrequency.keySet()) {
            // System.out.println(letter + " - " + lettersFrequency.get(letter));
            if (lettersFrequency.get(letter) > maxNumPopLetter) {
                popularLetter = letter;
                maxNumPopLetter = lettersFrequency.get(letter);
            }
        }

        System.out.println(popularLetter);
        return popularLetter;
    }

//Melyik ország nyerte el legkésőbb (a nyilvántartás szerint) a függetlenségét? 
//Add vissza a megfelelő országkódot, a metódus szignatúrája az alábbi legyen:
//String lastIndependentCountry()
    public static String lastIndependentCountry() {
        String lastIndName = "";
        int youngerCountry = Integer.MIN_VALUE;
        // országok HashMap-en iteráció és a függetlenség évére maximum keresés
        for (String country : countries.keySet()) {
            if (countries.get(country).getIndependence() > youngerCountry) {
                lastIndName = country;
                youngerCountry = countries.get(country).getIndependence();
            }
        }
        //System.out.println(lastIndName + ", " + youngerCountry);
        return lastIndName;
    }

    private static void readFile() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("orszagok.txt"));
        while (sc.hasNext()) {
            String[] parts = sc.nextLine().split(",");
            String code = parts[0];
            String name = parts[1];

            // kontinens, régió 
            String cont = parts[2];
            String region = parts[3];

            double area = Double.parseDouble(parts[4]);
            // függetlenség
            int indep = 0;
            if (parts[5].equals("NULL")) {
                indep = Integer.MIN_VALUE;
            } else {
                indep = Integer.parseInt(parts[5]);
            }

            int popul = Integer.parseInt(parts[6]);

            // elnök >>> van olyan ország ahol nincs megadva név !!!
            String pres = "";
            if (parts.length < 8) {
                pres = "noName";
            } else {
                pres = parts[7];
            }

            // ország példányosítás
            Country c = new Country(code, name, cont, region, area, indep, popul, pres);
            countries.put(code, c);
        }
        sc.close();

        // városok beolvasása, példányosítása és az Country myCities ArrayList-be beszúrni.
        sc = new Scanner(new File("varosok.txt"));
        while (sc.hasNext()) {
            String[] partLine = sc.nextLine().split(",");
            String name = partLine[0];
            String codeCountry = partLine[1];
            int pop = Integer.parseInt(partLine[2]);
            City cit = new City(name, codeCountry, pop);
            // a várost betesszük a Country myCities ArrayList-be
            countries.get(codeCountry).getCities().add(cit);
            cit.setCountry(countries.get(codeCountry));
        }
    }
}
