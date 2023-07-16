import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/*
    Creare un programma Java che possa gestire una serie di giardini zoologici

    Ogni parco deve poter contenere:
    -orario di apertura
    -numero di animali max (capienza)
    -deve indicare quali sezioni/spazi sono presenti fra questi 3:
        - RecintoAcquatico (può contenere solo animali acquatici)
        - RecintoVolatili (può contenere solo volatili)
        - RecintoStandard (può contenere il resto degli animali)
    -ogni sezione/spazio deve specificare:
        -lista degli animali presenti
        -orario di apertura del recinto (OGNI ZOO DEVE AVERE ORARI DIVERSI)

    Il programma  deve consentire ad un utente di consultare il registro di questi parchi per:
    1)	Cercare quali zoo contengono un certo tipo di animale
    2)	Selezionare uno zoo per capire se è aperto in questo momento
    3)	Cercare in modo specifico un certo animale in un certo zoo
    4)	Richiedere la possibilità di visitare un certo animale in un certo zoo all’interno di un recinto in questo momento
    5)	Stampare lista degli animali presenti in un certo zoo
    6)	Stampare lista degli animali presenti in un certo zoo per un certo sezione/spazio richiesto
 */
public class SoftwareManager {
    private List<Zoo> listOfZoo;

    public SoftwareManager(List<Zoo> listOfZoo) {
        this.listOfZoo = listOfZoo;
    }

    public static void main(String[] args) {

        List<Zoo> listOfZoo = new ArrayList<>();

        listOfZoo.add(createPark("Zoo Udine", LocalTime.of(8, 00), LocalTime.of(21, 00), 200));
        listOfZoo.add(createPark("Zoo Trieste", LocalTime.of(9, 00), LocalTime.of(19, 00), 300));
        listOfZoo.add(createPark("Zoo Lignano", LocalTime.of(8, 00), LocalTime.of(20, 00), 300));

        SoftwareManager softwareManager = new SoftwareManager(listOfZoo);

        //1) cerco quali zoo contengono un certo tipo di animale
        List<Zoo> zooWithpesceRosso = softwareManager.findAnimal("Pesce rosso");
        for (Zoo zoo : zooWithpesceRosso) {
            System.out.println(zoo);
        }

        //2) Selezionare uno zoo per capire se è aperto in questo momento
        boolean isOpen = softwareManager.isZooOpen("Zoo Udine", LocalTime.now());
        if (isOpen) {
            System.out.println("Zoo aperto");
        } else {
            System.out.println("Zoo chiuso.");
        }

        //3) Cercare in modo specifico un certo animale in un certo zoo
        if (softwareManager.findAnimalInZoo("Zoo Udine", "Delfino")) {
            System.out.println("Lo Zoo di udine ha i delfini.");
        } else {
            System.out.println("Lo Zoo di Udine non ha i delfini.");
        }
        if (softwareManager.findAnimalInZoo("Zoo Trieste", "Tigre")) {
            System.out.println("Lo zoo di trieste ha le tigri.");
        } else {
            System.out.println("Lo zoo di Trieste non ha le tigri .");
        }

        // 4)Richiedere la possibilità di visitare un certo animale in un certo zoo all’interno di un recinto in questo momento
        Zoo zoo = softwareManager.getZooByName("Zoo Trieste");
        Animal animal = zoo.getAnimalByName("Pesce rosso");
        Section section = zoo.getSectionBySectionType(animal.getSectionType());
        boolean isSectionOpen = section.isOpenAt(LocalTime.now());
        if (isSectionOpen) {
            System.out.printf("Puoi visitare %s presso %s alle %s\n",
                    animal, zoo, LocalTime.now());
        }
        else {
            System.out.printf("Siamo spiacenti, non puoi visitare %s presso %s, alle %s\n", animal, zoo, LocalTime.now());
        }

        // 5) Stampare lista degli animali presenti in un certo zoo.
        softwareManager.getZooByName("Zoo Trieste").printAnimals();

        // 6)	Stampare lista degli animali presenti in un certo zoo per una certa sezione richiesta
        softwareManager.getZooByName("Zoo Udine").getSectionBySectionType(SectionType.STD_SECTION).printAnimals();


    }

    public static Section createWaterSection() {
        Animal pesceNeon = new Animal("Pesce neon", SectionType.WATER_SECTION);
        Animal pesceRosso = new Animal("Pesce rosso", SectionType.WATER_SECTION);
        Animal delfino = new Animal("Delfino", SectionType.WATER_SECTION);
        Animal squalo = new Animal("squalo", SectionType.WATER_SECTION);
        Animal cavalluccioMarino = new Animal("Cavalluccio marino", SectionType.WATER_SECTION);

        List<Animal> waterSection = new ArrayList<>();

        waterSection.add(pesceNeon);
        waterSection.add(pesceRosso);
        waterSection.add(delfino);
        waterSection.add(squalo);
        waterSection.add(cavalluccioMarino);

        Section result = new Section(waterSection, LocalTime.of(9, 0), LocalTime.of(18, 0), SectionType.WATER_SECTION);
        return result;
    }

    public static Section createBirdSection() {
        Animal fenicotteroRosa = new Animal("Fenicottero rosa ", SectionType.BIRD_SECTION);
        Animal gufoReale = new Animal("Gufo reale", SectionType.BIRD_SECTION);
        Animal struzzo = new Animal("Struzzo", SectionType.BIRD_SECTION);
        Animal cicognaNera = new Animal("Cicogna nera", SectionType.BIRD_SECTION);
        Animal grifone = new Animal("Grifone", SectionType.BIRD_SECTION);

        List<Animal> birdSection = new ArrayList<>();

        birdSection.add(fenicotteroRosa);
        birdSection.add(gufoReale);
        birdSection.add(struzzo);
        birdSection.add(cicognaNera);
        birdSection.add(grifone);

        Section result = new Section(birdSection, LocalTime.of(9, 0), LocalTime.of(13, 0), SectionType.WATER_SECTION);
        return result;
    }

    public static Section createStdSection() {
        Animal leone = new Animal("Leone ", SectionType.STD_SECTION);
        Animal giraffa = new Animal("Giraffa", SectionType.STD_SECTION);
        Animal pantera = new Animal("Pantera", SectionType.STD_SECTION);
        Animal elefante = new Animal("Elefante", SectionType.STD_SECTION);
        Animal rinoceronte = new Animal("Rinoceronte", SectionType.STD_SECTION);

        List<Animal> stdSection = new ArrayList<>();

        stdSection.add(leone);
        stdSection.add(giraffa);
        stdSection.add(pantera);
        stdSection.add(elefante);
        stdSection.add(rinoceronte);

        Section result = new Section(stdSection, LocalTime.of(9, 0), LocalTime.of(16, 0), SectionType.STD_SECTION);
        return result;
    }

    private static Zoo createPark(String name, LocalTime openingHour, LocalTime closingHour, Integer maxCapacity) {
        List<Section> sections = new ArrayList<>();

        Section waterSection = createWaterSection();
        Section birdSection = createBirdSection();
        Section stdSection = createStdSection();

        sections.add(waterSection);
        sections.add(birdSection);
        sections.add(stdSection);

        Zoo res = new Zoo(name, openingHour, closingHour, maxCapacity, sections);
        return res;

    }

    public List<Zoo> findAnimal(String name) {
        List<Zoo> result = new ArrayList<>();
        for (Zoo zoo : listOfZoo) {
            if (zoo.findAnimal(name)) {
                result.add(zoo);
            }

        }
        return result;
    }

    public boolean findAnimalInZoo(String zooName, String animalName) {
        Zoo zoo = getZooByName(zooName);
        return zoo.findAnimal(animalName);
    }

    public Zoo getZooByName(String name) {
        for (Zoo zoo : listOfZoo) {
            if (zoo.getName().equals(name)) {
                return zoo;
            }
        }
        return null;
    }

    public boolean isZooOpen(String name, LocalTime time) {
        boolean result = getZooByName(name).isZooOpen(time);
        return result;
    }
}

