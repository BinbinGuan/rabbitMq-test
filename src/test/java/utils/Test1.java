package utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: GuanBin
 * @date: Created in 下午4:56 2020/2/11
 */
public enum Test1 {
    WX_TOKEN,
    DING_TALK_TOKEN;

    private static Map<String,String> cache = new ConcurrentHashMap();

    public void set(String tenantId, String token){
        cache.put(key(tenantId), token);
    }

    public String get(String tenantId){
        return cache.get(key(tenantId));
    }

    private String key(String tenantId){
        return tenantId + "_" + this.name();
    }

    public boolean exist(){
        return cache.containsKey(name());
    }

    public static void clear(){
        cache.clear();
    }
}
