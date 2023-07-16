import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Section {

    private final List<Animal>  listOfAnimals;
    private final LocalTime openingTime;
    private final LocalTime closingTime;

    private final SectionType sectionType;

    public Section(List<Animal> listOfAnimals, LocalTime openingTime, LocalTime closingTime, SectionType sectionType) {
        this.listOfAnimals = listOfAnimals;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.sectionType = sectionType;
    }
    public boolean findAnimal(String name) {
        for (Animal animal: listOfAnimals) {
            if (animal.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public Animal getAnimalByName(String animalName) {
        for (Animal animal: listOfAnimals) {
            if(animal.getName().equals(animalName)) {
                return animal;
            }
        }
        return null;
    }

    public void printAnimals() {
        for (Animal animal: listOfAnimals) {
            System.out.println(animal.getName());
        }
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public boolean isOpenAt(LocalTime now) {
        return now.isAfter(openingTime) && now.isBefore(closingTime);
    }
}
