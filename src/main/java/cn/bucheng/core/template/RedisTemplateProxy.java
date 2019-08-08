package cn.bucheng.core.template;

import cn.bucheng.core.hash.DefaultBoundHashOperations;
import cn.bucheng.core.hash.DefaultHashOperations;
import org.springframework.data.redis.core.*;
import cn.bucheng.core.value.DefaultBoundValueOperations;
import org.springframework.lang.Nullable;
import cn.bucheng.core.value.DefaultValueOperations;

/**
 * @author buchengyin
 * @create 2019/8/7 22:26
 * @describe
 */
public class RedisTemplateProxy<K, V> extends RedisTemplate<K, V> {
    private volatile @Nullable
    ValueOperations<K, V> valueOps;

    @Override
    public <HK, HV> BoundHashOperations<K, HK, HV> boundHashOps(K key) {
        return new DefaultBoundHashOperations<>(key, this);
    }

    @Override
    public <HK, HV> HashOperations<K, HK, HV> opsForHash() {
        return new DefaultHashOperations<>(this);
    }

    @Override
    public BoundValueOperations<K, V> boundValueOps(K key) {
        return new DefaultBoundValueOperations<>(key, this);
    }

    @Override
    public ValueOperations<K, V> opsForValue() {
        if (valueOps == null) {
            valueOps = new DefaultValueOperations<>(this);
        }
        return valueOps;
    }
}
