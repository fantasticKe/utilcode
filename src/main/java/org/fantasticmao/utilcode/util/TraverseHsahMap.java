package org.fantasticmao.utilcode.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author maokeluo
 * @desc 遍历hashMap性能比较
 * @create 17-9-2
 */
public class TraverseHsahMap {

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new HashMap<String, String>();
        int num = 10_0000;
        Thread thread = new Thread(() -> {
            for (int i = 0; i < num; i++){
                String temp = String.valueOf(i);
                map.put(temp,temp);
            }
        });
        thread.start();
        thread.join();

        //forEach map.entrySet
        long start1 = System.currentTimeMillis();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        long end1 = System.currentTimeMillis() - start1;
        System.out.println("forEach map.entrySet遍历HashMap使用时间："+end1);

        //显式调用map.entrySet()的集合迭代器
        long start2 = System.currentTimeMillis();
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            entry.getKey();
            entry.getValue();
        }
        long end2 = System.currentTimeMillis() - start2;
        System.out.println("显式调用map.entrySet()的集合迭代器遍历HashMap使用时间："+end2);

        //forEach map.keySet在调用get取值
        long start3 = System.currentTimeMillis();
        for (String key : map.keySet()) {
            map.get(key);
        }
        long end3 = System.currentTimeMillis() - start3;
        System.out.println("forEach map.keySet在调用get取值遍历HashMap使用时间："+end3);

        //forEach map.entrySet()，用临时变量保存map.entrySet()
        long start4 = System.currentTimeMillis();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            entry.getKey();
            entry.getValue();
        }
        long end4 = System.currentTimeMillis() - start4;
        System.out.println("forEach map.entrySet()，用临时变量保存map.entrySet()使用时间："+end4);
    }
}
