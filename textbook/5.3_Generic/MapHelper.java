import java.util.List;

public class MapHelper {
    /*
    public static V get(Map61B<K, V> map, K key)
    直接像上面这么写无法通过编译，提示cannot resolve symbol
    如果类比前面Map61B，可以在类上面加上泛型，形如 MapHelper<K, V>，但类维度我们不希望加上这个
    因此将泛型定义放在方法维度上，形如：public static <K, V> V get(Map61B<K, V> map, K key)
    在返回类型前面定义好泛型即可
     */

    /* Returns the value corresponding to the given key in the map if it exists, otherwise null.
    在ArrayMap中，调用get时如果key不存在，会报异常
     */
    public static <K, V> V get(Map61B<K, V> map, K key) {
        List<K> keys = map.keys();
        for (int i = 0; i < map.size(); i++) {
            if (keys.get(i).equals(key)) {
                return map.get(key);
            }
        }
        return null;
    }

    /* Returns the maximum of all keys in the given ArrayMap. Works only if keys can be compared.
    K这个泛型需要实现Comparable接口，在定义泛型的时候要实现Comparable接口
    由于不确定K是一个接口还是类，因此用extends，只有确定是类的情况才能用implements
    K extends Comparable<K> 这一段表示K类必须继承comparable接口，即需要能调用compareTo方法

    PS：K extends Comparable 与 Dog extends Animal 的含义不太一致
    当Dog extends Animal时，可以认为将Animal的所有方法赋予了Dog
    当K extends Comparable时，只是说K必须是Comparable
    可以认为在泛型中使用extends的功能只是加了约束，而没有赋予新功能，与类继承时的extends不太一样
     */
    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {
        List<K> keys = map.keys();
        if (keys.isEmpty()) {
            return null;
        }
        K result = keys.get(0);
        for (K key : keys) {
            if (key.compareTo(result) > 0) {
                result = key;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map61B<String, String> map61B = new ArrayMap<>();
        map61B.put("k1", "v1");
        map61B.put("k2", "v2");
        map61B.put("k3", "v3");
        map61B.put("k0", "v0");
        System.out.println(MapHelper.get(map61B, "k1"));
        System.out.println(MapHelper.get(map61B, "a1"));
        System.out.println(MapHelper.maxKey(map61B));
    }
}
