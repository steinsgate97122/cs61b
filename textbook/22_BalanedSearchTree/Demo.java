/*
之前的BST，在最坏情况下会退化为一个链表
不过已知Insert in random order的情况下，树的平均高度是logN，但很多情况下，执行insert操作时没有所有数据，所以不好保证随机

leftRotation:
     X                  Y
      \                /
       Y     ==>      X
      /                \
     B                  B
rightRotation:
     Y                  X
    /                    \
   X         ==>          Y
    \                    /
     B                  B
rotation可以调整树的高度，一个不太显然的事实是，任意BST都可以通过rotation来balance
但问题在于，怎么rotate？？

先给search tree分类，已知的BST，还有treaps，splay trees，2-3 trees，2-3-4 trees，B-trees
新增left时，可能会让树变得不平衡，可以通过rotation来平衡，但怎么rotation也是个问题
现在换个思路，不新增leaf，要新增key直接加在现有的left中
        m                  m
      /   \              /   \
     e     o     ==>    e     o
    / \   / \          / \   / \
   b  g   n  p        b  g   n  p,q,r,s
可能的问题是，一个leaf内的key太多，怎么解决？
可以给leaf中的number of items设置一个cap，例如设置为3，当超出这个cap时将一个item送去parent
例如对于[p,q,r,s]，将中间的q送去parent，此时p位于o和q中间，将[p,r,s]分裂成[p]和[r,s]
现在一个parent有3个children了
           o,q
        /   |   \
       n    p   r,s
在上面这棵树找r，先首先r>o，然后r>q，所以在最右边的子节点，每个节点最多比较cap次（一个node中最多cap个item）
类似上面的思路，继续insert t,u
           o,q                          o,q                         o,q,s
        /   |   \      =>            /   |   \         =>       /   |   |   \
       n    p   r,s                 n    p   r,s,t,u           n    p   r   t,u
继续insert y,z
           m                             m,q
           |                           /     \
        o,q,s,u            =>         o       s,u
   /   |   |   |   \                 / \     / | \
  n    p   r   t   y,z              n   p   r  t  y,z

Q.如果root的item超过cap了怎么办？
           m,q,u,w
      /   |   |   |   \
     e    o   s   v   x,y
还是类似处理方式，把q提上去，生成一个新的root节点，下面正常分就行
结论：使用上面这种方式处理时，balance一直都保持得很好，split root时会将树的高度+1，split其他节点时都不会新增树的高度

上面这种树即B-tree（私底下叫splitting tree更加直观，前面的B可能代表balanced、broad、bushy）
上面用的tree其cap为3，最多有4个children，这种称为2-3-4 tree，2-3-4的意思是子节点数量为2或3或4
如果tree的cap为2，最多有3个children，那么就是2-3 tree
大M的B-tree在数据库中广泛应用，因为树的深度小，一般将每一层的节点存储在磁盘，这样可以减少磁盘的IO次数，提升性能
（实际上数据库里面用的是B+树，相比B树，其在父节点只存key，叶子节点存key和value，相当于非叶子节点只索引，可以提升范围查找的性能）

但很遗憾，B-tree实现起来相当困难，首先分成含1个key的node，2个key的node等等类型，都要根据类型来写分支逻辑，难以维护
另外节点类型会频繁变化，2-node插入1个key之后就变成了3-node，转来转去很复杂
另外node split也很难处理，因为分裂上去的时候可能父节点也满了，需要继续向上最终可能一路递归到根节点，实现也很复杂
 */
public class Demo {
}
