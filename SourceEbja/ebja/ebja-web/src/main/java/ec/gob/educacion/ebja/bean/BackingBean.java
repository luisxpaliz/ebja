package ec.gob.educacion.ebja.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class BackingBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Family> families;
    private List<String> countries;

    public void generateFamilies() {
        this.families = new ArrayList<Family>();

        Family f = new Family("Beta");
        f.getCars().add("Mercedes");
        f.getCars().add("Porsche");
        f.getCars().add("VW");
        f.getCars().add("Dodge");
        f.getPersons().add("he");
        f.getPersons().add("she");
        families.add(f);

        f = new Family("Alpha");
        f.getCars().add("BMW");
        f.getPersons().add("he");
        f.getPersons().add("she");
        families.add(f);
    }

    private void generateCountries() {
        this.countries = new ArrayList<String>();
        this.countries.add("Germany");
        this.countries.add("England");
        this.countries.add("Spain");
    }

    public List<Family> getFamilies() {
        if (null == families) {
            generateFamilies();
        }
        return families;
    }

    public List<String> getCountries() {
        if (null == countries) {
            generateCountries();
        }
        return countries;
    }

    public class Family {
        private String name;
        private List<String> persons;
        private List<String> cars;

        public Family(String name) {
            this.name = name;
            this.persons = new ArrayList<String>();
            this.cars = new ArrayList<String>();
        }

        public String getName() {
            return name;
        }

        public List<String> getPersons() {
            return persons;
        }

        public List<String> getCars() {
            return cars;
        }
    }

}