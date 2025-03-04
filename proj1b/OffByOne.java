/*
Character用单引号包裹，String用双引号
说明：java中的Character都可以当成Integer处理，例如 int x = '&'; 这个时候x打印出来就是38
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (Math.abs(diff) == 1);
    }
}
