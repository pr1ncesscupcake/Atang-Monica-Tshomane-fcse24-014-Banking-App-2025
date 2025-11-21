package util;

import java.security.SecureRandom;

public final class PasswordUtil {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%&*";
    private static final String ALL = UPPER + LOWER + DIGITS + SYMBOLS;
    private static final SecureRandom rnd = new SecureRandom();

    private PasswordUtil(){}

    public static String generateTemporaryPassword(int length) {
        if (length < 6) length = 6;
        StringBuilder sb = new StringBuilder(length);
        sb.append(UPPER.charAt(rnd.nextInt(UPPER.length())));
        sb.append(LOWER.charAt(rnd.nextInt(LOWER.length())));
        sb.append(DIGITS.charAt(rnd.nextInt(DIGITS.length())));
        sb.append(SYMBOLS.charAt(rnd.nextInt(SYMBOLS.length())));
        for (int i = 4; i < length; i++) sb.append(ALL.charAt(rnd.nextInt(ALL.length())));
        // shuffle
        char[] arr = sb.toString().toCharArray();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            char tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
        return new String(arr);
    }
}
