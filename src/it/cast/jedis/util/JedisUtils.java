package it.cast.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtils {
       static   JedisPool jedisPool;

        static {
            //加载配置文件
            InputStream is = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
            Properties properties=new Properties();
            try {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //创建配置对象进行配置
            JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
            jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
            //创建JedisPool对象并且加载设置配置
            jedisPool=new JedisPool(jedisPoolConfig,properties.getProperty("host"),(Integer.parseInt(properties.getProperty("port"))));
        }

        public  static Jedis getJedis()
        {
            return jedisPool.getResource();
        }
}
