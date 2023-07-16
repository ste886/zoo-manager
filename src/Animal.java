public class Animal {
    private final String name;
    private final SectionType sectionType;

    public Animal(String name, SectionType sectionType) {
        this.name = name;
        this.sectionType = sectionType;
    }

    public String getName() {
        return name;
}


    public SectionType getSectionType() {
        return sectionType;
    }

    @Override
    public String toString() {
        return name;
    }
}
