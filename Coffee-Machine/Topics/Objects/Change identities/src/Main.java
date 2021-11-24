class Person {
    String name;
    int age;

}

class MakingChanges {
    public static void changeIdentities(Person p1, Person p2) {
        String nameToSwap = p2.name;
        int ageToSwap = p2.age;

        p2.name = p1.name;
        p2.age = p1.age;

        p1.name = nameToSwap;
        p1.age = ageToSwap;

    }
}