package ua.koval.footballmanager.dto;

import org.springframework.hateoas.RepresentationModel;

public class PlayerDto extends RepresentationModel<PlayerDto> {
    private int id;

    private String name;

    private int age;

    private int experience;

    private double salary;

    public PlayerDto() {
    }

    public PlayerDto(int id, String name, int age, int experience, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
