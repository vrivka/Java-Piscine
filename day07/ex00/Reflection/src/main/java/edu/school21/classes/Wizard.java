package edu.school21.classes;

import edu.school21.app.Program;

import java.util.Random;

public class Wizard {
    private String name;
    private String title;
    private Integer age;
    private Boolean side;

    public Wizard() {
        name = "Harald";
        title = "The Mysterious";
        age = 35;
        side = Program.LIGHT_SIDE;
    }

    public Wizard(String name, String title, Integer age, Boolean side) {
        this.name = name;
        this.title = title;
        this.age = age;
        this.side = side;
    }

    public void castTheSpell(String spell, String target, Integer damage) {
        Random rand = new Random();
        String first = fullName() + ", cast the " + spell + " to target " + target;

        switch (rand.nextInt() % 3) {
            case 0:
                System.out.println(first + ", but misses and destroy the barn!");
                return;
            case 1:
                System.out.println(first + ", but " + target + " dodging the spell");
                return;
            default:
                System.out.println(first + " and dealing " + damage + " damage");
        }
    }

    public void meditation(Integer years) {
        age += years;
        System.out.println(fullName() + ", meditate " + years + " years. His age now - " + age);
    }

    public void changeSide() {
        side = side ? Program.DARK_SIDE : Program.LIGHT_SIDE;
        System.out.println(name + ", " + title + ", change side. How its on " + (side ? "Light" : "Dark") + " side");
    }

    public String fullName() {
        return name + ", " + title;
    }

    @Override
    public String toString() {
        return "Wizard: " + fullName() + ", from the " + (side ? "Light" : "Dark") + " side" + ", " + age + " years old.";
    }
}
