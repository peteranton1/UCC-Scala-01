package playground.palin;

import java.util.stream.Stream;

public class PalindromeJava {
  public boolean inner(String str, int left, int right) {
    if (left >= right) {
      return true;
    } else if (str.charAt(left) != str.charAt(right)) {
      return false;
    } else {
      return inner(str, left + 1, right - 1);
    }
  }

  public boolean isPalendrome(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    } else
      return inner(str, 0, str.length() - 1);
  }

  public static void main(String[] args) {
    String[] values = {
        "abba",
        "madam",
        "amanaplanacanalpanama",
        "banana"
    };

    PalindromeJava inst = new PalindromeJava();
    Stream.of(values).forEach(
        v -> {
          boolean result = inst.isPalendrome(v);
          System.out.printf("%s = %s%n",
              v, result);
        });
  }
}
