package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger beautifulWordsLength3 = new AtomicInteger(0);
    private static AtomicInteger beautifulWordsLength4 = new AtomicInteger(0);
    private static AtomicInteger beautifulWordsLength5 = new AtomicInteger(0);

    public static void main(String[] args) {

        String[] texts = generateTexts(100000);

        Thread palindromeThread = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text)) {
                    if (text.length() == 3) {
                        beautifulWordsLength3.incrementAndGet();
                    } else if (text.length() == 4) {
                        beautifulWordsLength4.incrementAndGet();
                    } else if (text.length() == 5) {
                        beautifulWordsLength5.incrementAndGet();
                    }
                }
            }
        });

        Thread sameLetterThread = new Thread(() -> {
            for (String text : texts) {
                if (isSameLetterWord(text)) {
                    if (text.length() == 3) {
                        beautifulWordsLength3.incrementAndGet();
                    } else if (text.length() == 4) {
                        beautifulWordsLength4.incrementAndGet();
                    } else if (text.length() == 5) {
                        beautifulWordsLength5.incrementAndGet();
                    }
                }
            }
        });

        Thread increasingOrderThread = new Thread(() -> {
            for (String text : texts) {
                if (isIncreasingOrderWord(text)) {
                    if (text.length() == 3) {
                        beautifulWordsLength3.incrementAndGet();
                    } else if (text.length() == 4) {
                        beautifulWordsLength4.incrementAndGet();
                    } else if (text.length() == 5) {
                        beautifulWordsLength5.incrementAndGet();
                    }
                }
            }
        });

        palindromeThread.start();
        sameLetterThread.start();
        increasingOrderThread.start();

        try {
            palindromeThread.join();
            sameLetterThread.join();
            increasingOrderThread.join();

            System.out.println("Красивых слов с длиной 3: " + beautifulWordsLength3.get() + " шт");
            System.out.println("Красивых слов с длиной 4: " + beautifulWordsLength4.get() + " шт");
            System.out.println("Красивых слов с длиной 5: " + beautifulWordsLength5.get() + " шт");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String[] generateTexts(int count) {
        Random random = new Random();
        String[] texts = new String[count];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        return texts;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String text) {
        int start = 0;
        int end = text.length() - 1;
        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean isSameLetterWord(String text) {
        char firstLetter = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != firstLetter) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIncreasingOrderWord(String text) {
        for (int i =
             1; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}