package ru.lesson1.bulder;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person() {

    }

    public static PersonBuilder getBuilder() {
        return new PersonBuilder();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public static class PersonBuilder {
        private final Person person;

        public PersonBuilder() {
            person = new Person();
        }

        public PersonBuilder withFirstName(String firstName) {
            person.setFirstName(firstName);
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            person.setLastName(lastName);
            return this;
        }

        public PersonBuilder withMiddleName(String middleName) {
            person.setMiddleName(middleName);
            return this;
        }

        public PersonBuilder withCountry(String country) {
            person.setCountry(country);
            return this;
        }

        public PersonBuilder withAddress(String address) {
            person.setAddress(address);
            return this;
        }

        public PersonBuilder withPhone(String phone) {
            person.setPhone(phone);
            return this;
        }

        public PersonBuilder withAge(int age) {
            person.setAge(age);
            return this;
        }

        public PersonBuilder withGender(String gender) {
            person.setGender(gender);
            return this;
        }

        public Person build() {
            return person;
        }
    }
}
