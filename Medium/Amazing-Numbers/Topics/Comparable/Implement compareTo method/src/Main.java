class Person implements Comparable<Person> {
    private String name;
    private int age;
    private int height;
    private int weight;

    public Person(String name, int age, int height, int weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Person otherPerson) {
        if (this.name.compareTo(otherPerson.name) == 0) {
            return Integer.valueOf(getAge()).compareTo(otherPerson.getAge());
        } else {
            return this.name.compareTo(otherPerson.getName());
        }
    }
}