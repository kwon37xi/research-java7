package ch11.objects;

import java.util.Objects;

public class Item {
    private String name;
    private int partNumber;

    public Item() {
        this("Widget", 0);
    }

    public Item(String name, int partNumber) {
        this.name = Objects.requireNonNull(name);
        this.partNumber = partNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        if (this.partNumber != other.partNumber) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + this.partNumber;
        return hash;
    }

    @Override
    public String toString() {
        return name + " - " + partNumber;
    }

    public static void main(String[] args) {
        Item item1 = new Item("Eraser", 2200);
        Item item2 = new Item("Eraser", 2200);
        Item item3 = new Item("Pencil", 1100);
        Item item4 = null;

        System.out.println("item1 equals item1: " + item1.equals(item1));
        System.out.println("item1 equals item2: " + item1.equals(item2));
        System.out.println("item1 equals item3: " + item1.equals(item3));
        System.out.println("item1 equals item4: " + item1.equals(item4));
        item2.setName(null);
        System.out.println("item1 equals item2: " + item1.equals(item2));
    }
}
