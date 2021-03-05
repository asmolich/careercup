/*
We are building an in-memory API to support two use cases.

1) We have users (officers) that wear a GPS device that send location
data to our system in real-time.
2) We also have users (administrators) who want to know where a specific
officer was at a specific point in time.

If there is no location at the timestamp specified, you must return a
location with the closest timestamp, if it exists

[(officer_id, ts, (lat, long))]

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OfficerLocationSearch {
    public static void main(String[] args) {
        OfficerLocationSearch sol = new OfficerLocationSearch();

        sol.receive(1L, new TimeLocation(1, new Location(2.0, 3.0)));
        sol.receive(1L, new TimeLocation(3, new Location(3.0, 3.0)));
        sol.receive(1L, new TimeLocation(5, new Location(4.0, 3.0)));
        sol.receive(1L, new TimeLocation(7, new Location(4.0, 4.0)));
        sol.receive(1L, new TimeLocation(9, new Location(4.0, 5.0)));
        sol.receive(1L, new TimeLocation(11, new Location(5.0, 6.0)));
        sol.receive(1L, new TimeLocation(15, new Location(7.0, 5.0)));


        System.out.println(sol.search(1, 7));
        System.out.println(sol.search(1, 10));
    }

    static class Location {
        double lat, lon;

        Location(double la, double lo) {
            lat = la;
            lon = lo;
        }

        public String toString() {
            return "(" + lat + "," + lon + ")";
        }
    }

    static class TimeLocation {
        long ts;
        Location loc;

        TimeLocation(long ts, Location loc) {
            this.ts = ts;
            this.loc = loc;
        }

        public String toString() {
            return "(ts=" + ts + ", loc=" + loc + ")";
        }
    }

    Map<Long, ArrayList<TimeLocation>> officerLocations = new HashMap<>();

    // receive ( (officer_id, ts, (lat, long))  )
    boolean receive(long officerId, TimeLocation loc) {
        return officerLocations.computeIfAbsent(officerId, k -> new ArrayList<>()).add(loc);
    }

    // search (officer_id, ts)
    TimeLocation search(long officerId, long ts) {
        ArrayList<TimeLocation> list = officerLocations.get(officerId);
        if (list == null || list.isEmpty()) throw new RuntimeException("No location");

        int idx = binarySearch(list, ts);

        return closest(idx, list, ts);
    }

    private TimeLocation closest(int idx, ArrayList<TimeLocation> list, long ts) {
        if (idx < 0) idx = 0;
        if (idx >= list.size()) idx = list.size() - 1;

        TimeLocation tl = list.get(idx);
        if (tl.ts == ts || idx == list.size() - 1) return tl;

        TimeLocation next = list.get(idx + 1);
        if (Math.abs(next.ts - ts) > Math.abs(ts - tl.ts)) return tl;
        else return next;
    }

    // lower bound
    int binarySearch(ArrayList<TimeLocation> list, long ts) {
        int lo = 0, hi = list.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi + 1) >>> 1;
            TimeLocation value = list.get(mid);
            if (value.ts == ts) {
                return mid;
            }

            if (value.ts <= ts) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }
}
