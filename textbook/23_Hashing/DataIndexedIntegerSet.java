/*
将存储的数据试做array的index，在array中存储true和false
缺点：很浪费空间、另外只能存储Integer
 */
public class DataIndexedIntegerSet {
    boolean[] present;

    public DataIndexedIntegerSet() {
        present = new boolean[100000];
    }

    public void insert(int i) {
        present[i] = true;
    }

    public boolean contains(int i) {
        return present[i];
    }
}