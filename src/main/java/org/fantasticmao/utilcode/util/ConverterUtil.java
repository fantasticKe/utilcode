package org.fantasticmao.utilcode.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author maokeluo
 * @desc
 * @create 18-2-28
 */
public class ConverterUtil {

    public static ConcurrentHashMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<String, BeanCopier>();

    public static void copyProperties(Object source, Object target){
        String beanKey = generateKey(source.getClass(),target.getClass());
        BeanCopier copier = null;
        copier = BeanCopier.create(source.getClass(), target.getClass(),false);
        beanCopierMap.putIfAbsent(beanKey,copier);
        copier = beanCopierMap.get(beanKey);
        copier.copy(source,target,null);
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}
