public class TwoDigitHashing {
    /*
    a) 给定哈希算法：对于一个字符串，只看最前和最后的Character，假定所有字符都是小写单词，拼出一个base-26 number
    例如，algebra -> aa -> 0，add -> ad -> 3，bad -> bd -> 1*26+3=29
    每个hash bucket表示为一个linked list，假定上面这个hash func能让key均匀分布
    求insert操作的worst-case time？table内最多有多少个key？

    假定hashtable内已经维护了N个key，最坏情况就是所有key都在一个bucket内，那么worst time就是O(N)
    这个hash算法就是 a*26+b，a和b都在0到25之间，那就是 0 ~ 25*26+25=675，最多676个？
     */

    /*
    b) How does your answer to (a) change if we change the hash function so that it considers up to the first
       log(26)_(N) characters (or the length of the key, whichever is smaller),
       keeping other assumptions the same? ( Ignore the time to compute log(26)_(N) )

    worst time还是O(N)，但是计算hash时的字符从2变成了log(26)_(N)，26^(log(26)_(N))=N，看上去最多N个key
     */
}
