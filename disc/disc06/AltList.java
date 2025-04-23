public class AltList<X, Y> {
    private X item;
    private AltList<Y, X> next;

    public AltList(X item, AltList<Y, X> next) {
        this.item = item;
        this.next = next;
    }

    public AltList<Y, X> pairsSwapped() {
        AltList<Y, X> restSwapped = null;
        if (next.next != null) {
            restSwapped = next.next.pairsSwapped();
        }
        return new AltList<Y, X>(next.item,
                new AltList<X, Y>(item, restSwapped));
    }
}
