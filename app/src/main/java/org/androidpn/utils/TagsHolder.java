package org.androidpn.utils;

public class TagsHolder {

    private static String[] tags0 = {"川菜", "快餐", "西餐"};
    private static String[] tags1 = {};
    private static String[] tags2 = {};
    private static String[] tags3 = {};
    private static String[] tags4 = {};
    private static String[] tags5 = {};
    private static String[] tags6 = {};

    public static String[] getTags(String classification) {
        if ("美食".equals(classification)) {
            return tags0;
        } else if ("景点".equals(classification)) {
            return tags1;
        } else if ("酒店".equals(classification)) {
            return tags2;
        } else if ("酒吧".equals(classification)) {
            return tags3;
        } else if ("电影".equals(classification)) {
            return tags4;
        } else if ("外卖".equals(classification)) {
            return tags5;
        }
        return tags6;
    }
}
