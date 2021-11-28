package com.monour.model;

public class Employee {

    private int id;
    private String name , age , job;
    private double salary;

    public Employee(){

    }

    public Employee(int id , String name, String age, String job, double salary)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }


    public String toString()
    {
        return id + " " + name + " " + age + " " + job + " " + salary;
    }

}
