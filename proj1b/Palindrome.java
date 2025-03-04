public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        for (int index = 0; index < word.length(); index++) {
            char charAtIndex = word.charAt(index);
            result.addLast(charAtIndex);
        }
        return result;
    }

    private boolean isPalindromeV1(String word) {
        for (int index = 0; index < (word.length() / 2); index++) {
            if (word.charAt(index) != word.charAt(word.length() - 1 - index)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindromeV2(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            if (wordDeque.removeFirst() != wordDeque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }
        if (deque.removeFirst() != deque.removeLast()) {
            return false;
        }
        return isPalindromeHelper(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
