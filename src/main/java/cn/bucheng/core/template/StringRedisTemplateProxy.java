package cn.bucheng.core.template;

import cn.bucheng.core.hash.DefaultBoundHashOperations;
import cn.bucheng.core.hash.DefaultHashOperations;
import cn.bucheng.core.value.DefaultValueOperations;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author buchengyin
 * @create 2019/8/7 22:27
 * @describe
 */
public class StringRedisTemplateProxy extends StringRedisTemplate {

    private volatile ValueOperations<String, String> valueOps;

    @Override
    public <HK, HV> HashOperations<String, HK, HV> opsForHash() {
        DefaultHashOperations<String, HK, HV> operations = new DefaultHashOperations<>(this);
        return operations;
    }


    @Override
    public <HK, HV> BoundHashOperations<String, HK, HV> boundHashOps(String key) {
        DefaultBoundHashOperations<String, HK, HV> operations = new DefaultBoundHashOperations<>(key, this);
        return operations;
    }


    @Override
    public ValueOperations<String, String> opsForValue() {
        if (valueOps == null) {
            valueOps = new DefaultValueOperations<>(this);
        }
        return valueOps;
    }
}
