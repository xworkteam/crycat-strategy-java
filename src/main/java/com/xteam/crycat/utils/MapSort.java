package com.xteam.crycat.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapSort {

    /**
     * 使用 Map按key首字母hashcode进行排序
     *
     * @param map
     * @return
     */
    private static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<>(Comparator.naturalOrder());
        sortMap.putAll(map);

        return sortMap;
    }

    public static String toStringMap(Map<String, String> map) {
        // 按map键首字母顺序进行排序
        map = MapSort.sortMapByKey(map);
        StringBuilder sbl = new StringBuilder();
        for (Map.Entry e : map.entrySet()) {
            Object o = e.getValue();
            String v = "";
            if (o == null) {
                v = "";
            } else if (o instanceof String[]) {
                String[] s = (String[]) o;
                if (s.length > 0) {
                    v = s[0];
                }
            } else {
                v = o.toString();
            }
            if (!e.getKey().equals("sign") && !e.getKey().equals("reqTime") && !e.getKey().equals("tx")) {
                sbl.append("&").append(e.getKey()).append("=").append(v);
            }
        }
        String s = sbl.toString();
        if (s.length() > 0) {
            return s.substring(1);
        }
        return "";
    }

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("Kb", "K");
        map.put("Wc", "W");
        map.put("Ab", "A");
        map.put("ad", "a");
        map.put("Fe", "N");
        map.put("Bf", "B");
        map.put("Cg", "C");
        map.put("Zh", "Z");

        Map<String, String> resultMap = sortMapByKey(map); // 按Key进行排序，根据首字母hashcode
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
