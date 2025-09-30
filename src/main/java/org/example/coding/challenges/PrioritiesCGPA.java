package org.example.coding.challenges;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;
/*
 * Create the Student and Priorities classes here.
 */

class Student {
    private int id;
    private String name;
    private double cgpa;

    public Student(String name, double cgpa, int id) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getCGPA() {
        return this.cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }
}


class Priorities {
    PriorityQueue<Student> priorityQueue = new PriorityQueue<>(
            (s1, s2) -> {
                if (s1.getCGPA() == s2.getCGPA()) {
                    return s1.getName().compareTo(s2.getName());
                } else if (s2.getName().equals(s1.getName())) {
                    return Integer.compare(s1.getID(), s2.getID());
                } else {
                    return Double.compare(s2.getCGPA(), s1.getCGPA());
                }
            }
    );

    public List<Student> getStudents(List<String> events) {
        if (events.isEmpty()) return new ArrayList<>();
        for (String event : events) {
            String[] s = event.split(" ");
            String action = s[0];
            if (action.equals("ENTER")) {
                Student student = new Student(s[1], Double.parseDouble(s[2]), Integer.parseInt(s[3]));
                priorityQueue.offer(student);
            } else if (action.equals("SERVED")) {
                priorityQueue.poll();
            }
            System.out.println(priorityQueue);
        }
        System.out.println("Final queue " + priorityQueue);
        List<Student> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            result.add(priorityQueue.poll());
        }
        return result;
    }
}


public class PrioritiesCGPA {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        System.out.println("waiting for n");
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();
        System.out.println("waiting for events");
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        scan.close();

        List<Student> students = priorities.getStudents(events);

        System.out.println(students);
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }
    }
}
