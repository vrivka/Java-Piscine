package edu.school21.classes;

public class Dragon {
    private String name = "Brumberger";
    private Integer health = 1_000_000;
    private String color = "Red";
    private Double damage = 1000d;

    public Dragon() {}

    public Dragon(String name, Integer health, String color, Double damage) {
        this.name = name;
        this.health = health;
        this.color = color;
        this.damage = damage;
    }

    public String introduce() {
        return color + " dragon " + name;
    }

    public String getDamage(Integer damage) {
        health -= damage;
        return this.toString();
    }

    @Override
    public String toString() {
        return introduce() + ", has " + health + " health and deal " + damage + " damage";
    }
}
