public class Practice {
    // represents a node in a 2-4 tree, leaf都用Node2_4.EMPTY表示，而不是用常见的null
    static class Node2_4 {
        // the unique empty node
        final static Node2_4 EMPTY = new Node2_4();

        // return my Kth child (numbering from 0)
        Node2_4 kid(int k) {
            // Implementation not shown，为了编译通过下面return null
            return null;
        }

        // return the number of my children (which is one more than the number of my keys)
        int arity() {
            // Implementation not shown，为了编译通过下面return 2
            return 2;
        }

        // return my Kth key (numbering from 0)
        String key(int k) {
            // Implementation not shown，为了编译通过下面return null
            return null;
        }

        // return true if key is a key in the tree rooted at me
        boolean contains(String key) {
            return false;
        }
    }

    /* represents non-empty nodes
    那么false的情况怎么处理？？
    因为leaf都用Node2_4.EMPTY表示，所以arity()至少是1，碰见叶子节点的时候，kid(0)都会返回Node2_4.EMPTY
    而Node2_4.EMPTY.contains()固定返回false，所以直接按照下面这么写不会出问题
     */
    static class InnerNode2_4 extends Node2_4 {
        @Override
        boolean contains(String key) {
            // M个children，当前节点内有M-1个key
            for (int i = 0; i < arity() - 1; i++) {
                if (this.key(i).equals(key)) {
                    return true;
                }
                // 如果当前遍历到的key比target大，那么在当前key对应的子树
                else if (this.key(i).compareTo(key) > 0) {
                    return kid(i).contains(key);
                }
            }
            // 所有遍历过的key都比target小，可能在最右边的树
            return kid(arity() - 1).contains(key);
        }
    }
}
