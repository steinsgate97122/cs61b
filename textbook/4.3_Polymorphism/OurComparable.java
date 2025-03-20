public interface OurComparable {
    /*
    Return -1 if this < o.
    Return 0 if this equals o.
    Return 1 if this > o.
    可进一步简化代码，当this < o时返回负数，this > o时返回正数
     */
    public int compareTo(Object o);
}
/*
上面的OurComparable使用时存在以下问题：
1、接收的是Object，实际比较时需要进行对象的强制转换
2、任何内置类都没有实现OurComparable
实际应用中使用Comparable接口，这个是java中预定义的，基本上和上面的OurComparable一致，不过用上了泛型
 */
