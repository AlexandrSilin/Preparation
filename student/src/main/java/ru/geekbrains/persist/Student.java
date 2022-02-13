package ru.geekbrains.persist;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Student {

    private long id;

    @NotBlank
    private String name;

    @Positive
    private long age;

    public Student() {
    }

    public Student(long id, String name, long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
