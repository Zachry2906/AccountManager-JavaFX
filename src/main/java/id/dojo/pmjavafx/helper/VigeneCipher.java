package id.dojo.pmjavafx.helper;

public class VigeneCipher {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 !@#$%^&*()_+=-`~\\|;:'\",.<>?/";

    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = CHARACTERS.indexOf(c);
            if (index == -1) {
                result.append(c);
            } else {
                int keyIndex = CHARACTERS.indexOf(key.charAt(j));
                int encryptedIndex = (index + keyIndex) % CHARACTERS.length();
                result.append(CHARACTERS.charAt(encryptedIndex));
                j = ++j % key.length();
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = CHARACTERS.indexOf(c);
            if (index == -1) {
                result.append(c);
            } else {
                int keyIndex = CHARACTERS.indexOf(key.charAt(j));
                int decryptedIndex = (index - keyIndex + CHARACTERS.length()) % CHARACTERS.length();
                result.append(CHARACTERS.charAt(decryptedIndex));
                j = ++j % key.length();
            }
        }
        return result.toString();
    }
}

