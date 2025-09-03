package org.example;

public class Employee {
    private String department;
    private String name;
    private int salary;

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Employee(String name, int salary, String department) {
        this.salary = salary;
        this.name = name;
        this.department = department;
    }

    public void doubleSalary() {
        this.salary = this.salary * 2;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
