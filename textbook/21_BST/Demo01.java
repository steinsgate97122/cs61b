public class Demo01 {
    /*
    对于在5.3中实现的ArrayMap，这里我们只关心get containsKey put操作，都调用了keyIndex，其时间复杂度为θ(N)
    即针对key的操作需要花费线性时间，怎么让这种操作加速？假定元素是排序后的
    1）加入一些随机的express lanes，详见skip list（这里不讨论）
    2）将起始指针放在list中间？然后将左半边的指针逆转，search time就减半了
       在上面基础上可以进一步提速，对于左右两边的arr，同样从中间分开，执行上面同样的操作，这就是tree的数据结构
     */
}
