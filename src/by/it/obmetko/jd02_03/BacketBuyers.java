package by.it.obmetko.jd02_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BacketBuyers {

    static Map<Integer, ArrayList> backetBuyer = new HashMap<>();

    static void backetByuer(int key, ArrayList good) {
        backetBuyer.put(key, good);
    }
}