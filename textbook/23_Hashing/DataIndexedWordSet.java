/*
相比DataIndexedIntegerSet，现在可以存储单词了
但是可能存在两个单词，其convertToInt后对应的Int值一样
例如hothead和pothead，其后32位都是00 01111 10100 01000 00101 00001 00100
java中的最大array包含2billion个入口，所以如果存在超过2*10^9个项，那么一定会有多个item放在同一个box内
 */
public class DataIndexedWordSet {
    boolean[] present;

    public DataIndexedWordSet() {
        present = new boolean[100000];
    }

    public void insert(String s) {
        int intRep = convertToInt(s);
        present[intRep] = true;
    }

    public boolean contains(String s) {
        int intRep = convertToInt(s);
        return present[intRep];
    }

    /** Converts ith character of String to a letter number.
     * e.g. 'a' -> 1, 'b' -> 2, 'z' -> 26 */
    public static int letterNum(String s, int i) {
        int ithChar = s.charAt(i);
        if ((ithChar < 'a') || (ithChar > 'z')) {
            throw new IllegalArgumentException();
        }
        return ithChar - 'a' + 1;
    }

    /*
    下面这个函数实现其实不太好，因为最前面的bits都被忽略了
    “potato” and “give me a potato” have same hashCode
    要写一个比较好的hashCode函数并不简单
    如果要改进的话，首先letterNum里面-'a'没什么必要可以去掉
    另外针对TopBits被完全忽略的问题，原因是一直在<<5，那么马上最开始几个字符就被挪没了
    解决方法很简单，只要把*32改成*31就行了，这样可以让旧字符的贡献持续保留
    使用32的时候，每5个bit表示一个字符，很好理解，但这样太容易出现collision了，所以还是用31的幂好一点
    当然，在按 31 的幂展开时，需要保证buckets的数量不要是31的倍数，否则mod M会极度不均匀，大量数据集中在少量桶内
     */
    public static int convertToInt(String s) {
        int intRep = 0;
        for (int i = 0; i < s.length(); i++) {
            intRep = intRep << 5; // same as intRep * 32;
            intRep = intRep + letterNum(s, i);
        }
        return intRep;
    }
}
/*
假定有n个项的hashcode相同都为h，原先就是在index h这里存一个true，现在可以在这里存一个n元素的list
假设就使用LinkedList，如果最长list的长度为Q，那么此时insert/contains操作的时间复杂度就是BigTheta(Q)
上面这个方式称为external chaining
实际上也用不到2 billion buckets，假设只用10个bucket，使用hash后的值%10，之后放进对应的bucket内也可以
此时时间复杂度取决于bucket内的item数量，假如N个item分布在M个bucket内，平均时间为N/M记为L，L称为load factor
（当然只看平均时间还不够，希望buckets也能balance，这个和对BST进行balance差不多，这里先不讨论）
显然，L越小速度越快，那么如何保证L保持较小的值？
A.只要L到达某个阈值，就增大M进行扩容
下一个问题，如果M=4，某个东西的hashCode为-1，那么将其放到哪个bucket比较合适？理论上应该是-1+4=3
但是在java中，-1%4=-1，改为使用Math.floorMod(-1, 4)就得到3了
上面的这个数据结构将其称为hash table
一般计算hashFunc分成以下两步：先计算hashCode，从-2^31到2^31-1，然后计算这个值%M就是index
 */

/*
PS:
List的hashCode与String的类似，分别对每个元素求hashCode，也是展开成31的幂次，如果要节约时间可以只计算最前面的若干项
接下来考虑递归的数据结构，例如binary tree，计算时使用 this.value.hashCode() + 31*this.left.hashCode() + 31*31*this.right.hashCode()
所有对象都有hashCode()函数，默认返回this，即当前对象的地址，当然可以自行override，为什么所有对象都需要hashCode，因为HashSet HashMap在java中太常用了
 */

/*
再PS：
1）HashSet或者HashMap内禁止放mutable objects
例如Person类有name属性，计算hashCode时是返回了name.hashCode()
那么将p放入HashMap后，如果p.name变了，那么hashCode就变了，那么再也找不到这个元素了
所以建议只放String、Integer这种不可变的元素进去，如果一定要放自定义类，需要保证其不会改变！
2）override equals方法后，hashCode方法也要override掉；如果两个对象通过equals相等了，那么它们的hashCode也必须相等
对于HashMap或者HashSet，insert或者contains调用时，先调用hashCode，然后在这个bucket内用equals逐个比较是否已经存在
那么如果只重写了equals()，还是以上面的Person为例，name equal的时候认为两个对象也equal
那么对两个name相同的对象p1 p2，p1.equals(p2)为true，但set.add(p1); set.contains(p2);为false
因为虽然两个对象equals是成立了，但hashCode不一样，所以还是会进入不同的bucket，这种情况下HashMap/HashSet的表现就不正常了
 */