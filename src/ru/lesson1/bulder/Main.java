package ru.lesson1.bulder;

public class Main {
    public static void main(String[] args) {
        Person person = Person.getBuilder()
                .withFirstName("first name")
                .withLastName("last name")
                .withMiddleName("middle name")
                .withCountry("country")
                .withAddress("address")
                .withPhone("phone")
                .withAge(22)
                .withGender("m")
                .build();
        System.out.println(person);
    }
}
