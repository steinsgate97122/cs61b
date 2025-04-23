public class AltListTest {
    public static void main(String[] args) {
        AltList<Integer, String> list = new AltList<Integer, String>(5,
                new AltList<String, Integer>("cat",
                        new AltList<Integer, String>(10,
                                new AltList<String, Integer>("dog", null))));
        AltList<String, Integer> nList = list.pairsSwapped();
    }
}
