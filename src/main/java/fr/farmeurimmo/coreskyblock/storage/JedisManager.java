package fr.farmeurimmo.coreskyblock.storage;

import fr.farmeurimmo.coreskyblock.purpur.CoreSkyblock;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class JedisManager {

    public static JedisManager INSTANCE;
    JedisPool pool;
    private String REDIS_PASSWORD = "tK3u6BEuiGeABAU00wVUidguBjtyzk4ffj9E3Qmb";
    private String REDIS_HOST = "tools-databases-redis-minecraft-1";

    public JedisManager() {
        INSTANCE = this;
        String tmpHost = CoreSkyblock.INSTANCE.getConfig().getString("redis.host");
        String tmpPass = CoreSkyblock.INSTANCE.getConfig().getString("redis.password");
        if (tmpHost != null && tmpPass != null) {
            REDIS_HOST = tmpHost;
            REDIS_PASSWORD = tmpPass;
        }
        pool = new JedisPool(REDIS_HOST, 6379);

        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                if (channel.equalsIgnoreCase("CoreSkyblock")) {
                    String[] args = message.split(":");
                    if (args[0].equalsIgnoreCase("island")) {
                        if (args[1].equalsIgnoreCase("create")) {

                        }
                    }
                }
            }
        };

        new Thread(() -> {
            try (Jedis jedis = pool.getResource()) {
                jedis.auth(REDIS_PASSWORD);
                jedis.subscribe(jedisPubSub, "CoreSkyblock");
            }
        }).start();

        //publishToRedis("CoreSkyblock", "test");
        //publishToRedis("CoreSkyblock", "test2");
    }

    public void onDisable() {
        try {
            pool.close();
        } catch (Exception ignored) {
        }
    }

    public void sendToRedis(String arg0, String data) {
        try (Jedis jedis = pool.getResource()) {
            jedis.auth(REDIS_PASSWORD);
            jedis.set(arg0, data);
        }
    }

    public String getFromRedis(String arg0) {
        try (Jedis jedis = pool.getResource()) {
            jedis.auth(REDIS_PASSWORD);
            return jedis.get(arg0);
        }
    }

    public void removeFromRedis(String arg0) {
        try (Jedis jedis = pool.getResource()) {
            jedis.auth(REDIS_PASSWORD);
            if (jedis.get(arg0) != null) {
                jedis.del(arg0);
            }
        }
    }

    public void publishToRedis(String arg0, String data) {
        try (Jedis jedis = pool.getResource()) {
            jedis.auth(REDIS_PASSWORD);
            jedis.publish(arg0, data);
        }
    }
}
