import java.time.LocalTime;
import java.util.List;

public class Zoo {

    private final String name;
    private final LocalTime openingHour;
    private final LocalTime closingHour;
    private final Integer maxCapacity;
    private final List<Section> sections;


    public Zoo(String name, LocalTime openingHour, LocalTime closingHour, Integer maxCapacity, List<Section> sections) {
        this.name = name;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.maxCapacity = maxCapacity;
        this.sections = sections;
    }
    public boolean findAnimal(String name) {
        for (Section section: sections) {
            if (section.findAnimal(name)) {
                return true;
            };
        }
        return false;
    }
    public Animal getAnimalByName(String animalName) {
        for (Section section : sections) {
            if (section.findAnimal(animalName)) {
                return section.getAnimalByName(animalName);
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
    public Boolean isZooOpen(LocalTime time) {
       if (time.isAfter(openingHour) && time.isBefore(closingHour)) {
           return true;
       }
       else {
           return false;
       }
    }

    @Override
    public String toString() {
        return name;
    }

    public void printAnimals() {
      for (Section section: sections) {
          section.printAnimals();
      }
    }

    public Section getSectionBySectionType(SectionType sectionType) {
        for(Section section: sections) {
            if (section.getSectionType().equals(sectionType)) {
                return section;
            }
        }
        return null;
    }
}
