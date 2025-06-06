package lab9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */
    private Set<K> keySet;

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
        keySet = new HashSet<>();
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int compareRes = key.compareTo(p.key);
        if (compareRes == 0) {
            return p.value;
        } else if (compareRes > 0) {
            return getHelper(key, p.right);
        } else {
            return getHelper(key, p.left);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        keySet.add(key);
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        int compareRes = key.compareTo(p.key);
        if (compareRes == 0) {
            p.value = value;
        } else if (compareRes > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.left = putHelper(key, value, p.left);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V removeValue = get(key);
        if (removeValue == null) {
            return null;
        }
        root = removeHelper(key, root);
        size -= 1;
        keySet.remove(key);
        return removeValue;
    }

    // 返回删除后的根节点
    private Node removeHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int compareRes = key.compareTo(p.key);
        if (compareRes > 0) {
            p.right = removeHelper(key, p.right);
        } else if (compareRes < 0) {
            p.left = removeHelper(key, p.left);
        } else {
            if (p.left == null) {
                return p.right;
            }
            if (p.right == null) {
                return p.left;
            }
            // 左子树的最大子节点替代p
            Node newP = findMaxKey(p.left);
            p.left = removeMaxKey(p.left);
            p.key = newP.key;
            p.value = newP.value;
        }
        return p;
    }

    private Node removeMaxKey(Node p) {
        if (p.right == null) {
            return p.left;
        }
        p.right = removeMaxKey(p.right);
        return p;
    }
    
    private Node findMaxKey(Node p) {
        if (p.right == null) {
            return p;
        }
        return findMaxKey(p.right);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (!get(key).equals(value)) {
            return null;
        }
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
