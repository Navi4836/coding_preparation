package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.poll());
        System.out.println(list.getLast());
        System.out.println(list.removeFirst());

        list.sort(Comparator.comparing(String::length));

        Employee alice = new Employee("Alice", 1000, "IT");
        Employee bob = new Employee("Bob", 7500, "Finance");
        Employee chris = new Employee("Chris", 6000, "IT");
        Employee david = new Employee("David", 5000, "Finance");

        Comparator<Employee> salaryComparator = Comparator.comparingInt(Employee::getSalary);

        List<Employee> employeeList = Arrays.asList(alice, bob, chris, david);
        Map<String, Long> groupedByDepartment = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingLong(Employee::getSalary)));

        employeeList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).distinct().skip(1).findFirst().ifPresent(System.out::println);
        System.out.println(employeeList);
        System.out.println(groupedByDepartment);

        List<Employee> doubledSalary = employeeList.stream().map(employee -> {
            employee.doubleSalary();
            return employee;
        }).toList();
        System.out.println(doubledSalary);
    }
}