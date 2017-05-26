import java.util.*;
public class VersionComp {
    static class Version implements Comparable<Version>{
        int major = 0;
        int minor = 0;
        int rev = 0;
        int build = 0;
        @Override
        public int compareTo(Version v) {
            int res = Integer.compare(major, v.major);
            if (res == 0) res = Integer.compare(minor, v.minor);
            if (res == 0) res = Integer.compare(rev, v.rev);
            if (res == 0) res = Integer.compare(build, v.build);
            return res;
        }
        @Override
        public String toString() {
            return "Version[" + major + ", " + minor + ", " + rev + ", " + build + "]";
        }

        public static Version valueOf(String s) {
            System.out.println("s = " + s);
            String[] parts = s.trim().split("[.]");
            System.out.println(Arrays.toString(parts));
            int n = parts.length;
            Version v = new Version();
            if (n > 0 && parts[0] != null && parts[0].length() > 0) v.major = Integer.parseInt(parts[0]);
            if (n > 1 && parts[1] != null && parts[1].length() > 0) v.minor = Integer.parseInt(parts[1]);
            if (n > 2 && parts[2] != null && parts[2].length() > 0) v.rev = Integer.parseInt(parts[2]);
            if (n > 3 && parts[3] != null && parts[3].length() > 0) v.build = Integer.parseInt(parts[3]);
            System.out.println(v);
            return v;
        }
    }
    public int compareVersion(String a, String b) {
        int res = (a == null || a.isEmpty()) ? 1 : 0;
        res += (b == null || b.isEmpty()) ? -1 : 0;
        if (res != 0) return res;

        return Version.valueOf(a).compareTo(Version.valueOf(b));
    }
    public static void main (String[] args) {
        VersionComp vc = new VersionComp();
        System.out.println(vc.compareVersion("13.0", "13.0.8"));
    }
}

