package utils;

public class Tree {
    public String label;
    public Tree left;
    public Tree right;

    public Tree(String label) {
        this.label = label;
    }

    public Tree() {
    }

    public int height() {
        return 5;  // not implement
    }
}
