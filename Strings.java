import java.util.ArrayList;
import java.util.Arrays;

public class Strings {
    public static void main(String[] args) {
        Strings s = new Strings();
        String test = "abc";
        System.out.println("Rank of " + test + " is " + s.rank(test));

        System.out.println(s.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("0100100\n" + s.restoreIpAddresses("0100100"));

        System.out.println("100 + 11 = " + s.addBinary("1", "10").equals("11"));
        System.out.println("is 16 a power of 2? " + s.power("16"));

        System.out.println("abb longestPalindrome = " + s.longestPalindrome("abb"));

        System.out.println(s.zigzagConvert("Helloworld", 4));
    }

    private int rank(String s) {
        int res = 0;
        if (s == null || s.isEmpty()) return res;

        int n = s.length();
        char[] ch = new char[n];
        s.getChars(0, n - 1, ch, 0);
        Arrays.sort(ch);


        return res;
    }

    public boolean isPalindrome(String a) {
        if (a == null || a.isEmpty()) return false;

        int i = 0;
        int j = a.length() - 1;
        while (i <= j) {
            char ci = a.charAt(i++);
            if (isAlphaNumeric(ci)) {
                char cj = a.charAt(j--);
                if (isAlphaNumeric(cj)) {
                    if (Character.toLowerCase(ci) != Character.toLowerCase(cj)) {
                        return false;
                    }
                } else {
                    i--;
                }
            }
        }
        return true;
    }

    private boolean isAlphaNumeric(char ch) {
        return ('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z') || ('0' <= ch && ch <= '9');
    }

    static class IP {
        int[] ip = new int[4];

        IP() {}

        static IP valueOf(String[] a) {
            if (a.length != 4) return null;
            IP res = new IP();
            for (int i = 0; i < 4; i++) {
                String s = a[i];
                if (s.length() <= 0 || s.length() > 3 || s.length() > 1 && s.startsWith("0")) return null;
                res.ip[i] = Integer.parseInt(s);
                if (res.ip[i] > 255) return null;
            }
            return res;
        }

        @Override
        public String toString() {
            return "[" + ip[0] + '.' + ip[1] + '.' + ip[2] + '.' + ip[3] + ']';
        }
    }

    public ArrayList<String> restoreIpAddresses(String a) {
        ArrayList<String> res = new ArrayList<String>();
        String[] temp = new String[4];
        String[] t = new String[3];
        t[0] = a;
        for (int i = 1; i <= 3; i++) {
            if (t[0].length() < i) break;
            temp[0] = t[0].substring(0, i);
            t[1] = t[0].substring(i);
            for (int j = 1; j <= 3; j++) {
                if (t[1].length() < j) break;
                temp[1] = t[1].substring(0, j);
                t[2] = t[1].substring(j);
                for (int k = 1; k <= 3; k++) {
                    if (t[2].length() < k) break;
                    temp[2] = t[2].substring(0, k);
                    temp[3] = t[2].substring(k);


                    IP ip = IP.valueOf(temp);
                    if (ip != null) {
                        res.add(ip.toString());
                    }
                }
            }
        }
        return res;
    }

    public String addBinary(String a, String b) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty()) return "";

        int m = a.length();
        int n = b.length();

        int lng = Math.max(m, n);
        int resLen = lng + 1;
        char[] res = new char[resLen];
        char carryOver = 0;
        for (int i = 0; i < lng; i++) {
            char ai = (m - 1 - i >= 0) ? a.charAt(m - 1 - i) : '0';
            char bi = (n - 1 - i >= 0) ? b.charAt(n - 1 - i) : '0';

            int r = (ai - '0') + (bi - '0') + carryOver;
            res[resLen - 1 - i] = (char) (r % 2 + '0');
            carryOver = (char) (r / 2);
        }
        if (carryOver == 1) {
            res[0] = '1';
            return new String(res);
        }
        return new String(res).substring(1);
    }

    public String multiply(String a, String b) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty()) return "";

        String s = a;
        String l = b;
        if (a.length() > b.length()) {
            s = b;
            l = a;
        }

        if ("0".equals(s) || "0".equals(l)) return "0";
        if ("1".equals(s)) return l;
        return "";
    }

    public int power(String a) {
        System.out.println("========================");
        if (a == null || a.isEmpty()) return 0;

        char lastDigit = a.charAt(a.length() - 1);
        if ((lastDigit - '0') % 2 != 0) return 0;

        String temp = a;
        while (temp != null && !temp.isEmpty()) {
            temp = divideBy2(temp);
            if ("1".equals(temp)) break;

            lastDigit = temp.charAt(temp.length() - 1);
            if ((lastDigit - '0') % 2 != 0) return 0;
        }
        return 1;
    }

    private String divideBy2(String s) {
        int carryOver = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int d = ch - '0';
            if (d < 0 || d > 9) return "";
            int res = (carryOver * 10 + d) / 2;
            if (!(sb.length() == 0 && res == 0)) {
                sb.append(res);
            }
            carryOver = d % 2;
        }
        return sb.toString();
    }

    private String longestPalindrome(String a) {
        if (a == null || a.isEmpty()) return "";

        String lp = "";
        int max = 0;
        for (int i = 0; i < a.length(); i++) {
            String p = findPalindrome(a, i, false);
            if (p.length() > max) {
                max = p.length();
                lp = p;
            }
            p = findPalindrome(a, i, true);
            if (p.length() > max) {
                max = p.length();
                lp = p;
            }
        }
        return lp;
    }

    private String findPalindrome(String s, int idx, boolean dbl) {
        int paliBegin = idx;
        int paliEnd = idx;
        int i = idx;
        int j = dbl ? idx + 1 : idx;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                paliBegin = i;
                paliEnd = j;
            } else break;
            i--;
            j++;
        }
        return s.substring(paliBegin, paliEnd + 1);
    }

    /**
     * Helloworld, 3
     * -------------
     * H   o   l
     * e l w r d  = Holelwrdlo
     * l   o
     * ========================
     * Helloworld, 4
     * -------------
     * H     o
     * e   w r
     * l o   l   = Hoewrlolld
     * l     d
     */
    private String zigzagConvert(String s, int rows) {
        if (rows <= 1) return s;

        StringBuilder[] builders = new StringBuilder[rows];
        for (int i = 0; i < rows; i++) {
            builders[i] = new StringBuilder();
        }
        int i = 0;
        int k = 0;
        int dir = -1;
        while (i < s.length()) {
            if (k == 0 || k == rows - 1) dir = -dir;
            builders[k].append(s.charAt(i++));
            k += dir;
        }

        for (int j = 1; j < rows; j++) {
            builders[0].append(builders[j]);
        }

        return builders[0].toString();
    }
}
