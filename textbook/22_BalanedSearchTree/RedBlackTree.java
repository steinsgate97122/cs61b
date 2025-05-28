/*
对于一颗BST，如果可以等效为2-3 tree，那么满足以下性质：
1、No node has two red links
red link连接的2个节点在2-3 tree中是同一个节点，如果node有2条red link，那么一个node中有3个item，这个就不是2-3 tree
2、Every path from root to a leaf has same number of black links.
已知2-3 tree完全平衡，所有路径都相同；在BST中的red link如果都不算层级，那么black link的数量就是原始2-3 tree的高度
3、Red links lean left
red link都是父节点连接到left child，其实这条可以不这么规定，但是按照实现方便可以这么规定，所得到的就是Left-Leaning Red-Black Tree（LLRB）
4、For any 2-3 tree, there exists a corresponding red-black tree with depth ≤ 2×(depth of 2-3 tree)
最坏情况下每个层级都用了一个red link，那么就是将树的高度从H变成2H，所以深度不超过原2-3 tree的2倍

下一个问题就是这么通过rotation来实现red-black tree？
假如对于一个叶子节点S，要insert一个节点E，在2-3 tree中，优先把一个node先塞满，塞不下之后再spilt
那么等效为red-black tree，E在S的左子节点使用red line连接
考虑问题1：如果针对节点E来insert S，那么当然E和S用red link连接，但直接按照BST的insert，S插入在了E的右子树
希望E在S的左子子树并使用red line连接，这种情况下用一次rotateLeft即可解决
接下来考虑问题2：当前节点已经有2个item：A E，再insert一个节点F，按照2-3 tree这个时候该split了，E提到父节点，A和F分别作为左右子树
对应到BST，原先结构：A在E的左子节点使用red line连接；之后F放在E的右节点使用red line连接；再往后把A和F的连接变成黑色，E和父节点的连接变红
（因为A和F与E不再是同一个node了，所以用黑色连接，E和上面的变成了一个node，所以用红色连接，这个称为color flipping）
         E                E
        /          =>    / \
       A                A   F
考虑问题3：原先的2个item：E F，那么对应的树结构是E在F的左子节点使用red line连接，这个时候insert A
期望的插入后结构还是E在上，A F分别作为E的左右节点与E连接，但直接用BST来insert的结果如下：
            F
           /               E
          E       =>      / \
         /               A   F
        A
rotateRight即可，把E提成根节点
考虑问题4：原先结构是F-A，insert E
            F
           /               E
          A       =>      / \
           \             A   F
            E
先对A进行rotateLeft，变成问题3的情况，然后对F进行rotateRight即可

总结一下
1、如果只有右子树用红色连接，那么left rotate
2、如果左右子树都用红色连接，那么就color flipping
3、如果左子树和左子树的左子树都是用红色连接，那么right flipping
4、case4实际上就是先命中case1，再case3，不用专门考虑了
实现参考RedBlackLiteBST
 */
public class RedBlackTree {

}
