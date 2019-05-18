package it.cast.jedis.test;

import it.cast.jedis.util.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JedisTest
 */
public class JedisTest {
    /**
     * 快速入门
     */
    @Test
    public void  test1()
    {
        //1.获取连接
        Jedis jedis=new Jedis("localhost",6379);
        //2.操作
        jedis.set("username","zhangsa");
        //3.关闭连接
        jedis.close();
    }

    /**
     * string类型数据操作
     */

    @Test
    public void  test2()
    {
        //1.获取连接
        Jedis jedis=new Jedis();//默认值 "localhost",6379
        //2.1存储
        jedis.set("username","zhangsa");
       //2.2获取存储数据
        String username = jedis.get("username");
        //2.3打印到控制台
        System.out.println(username);

        jedis.setex("password",20,"123");//指定键值对的过期时间，20s后删除。

        //3.关闭连接
        jedis.close();
    }

    /**
     * hash类型数据操作
     */

    @Test
    public void  test3()
    {
        //1.获取连接
        Jedis jedis=new Jedis();//默认值 "localhost",6379
        //2.1存储
        jedis.hset("user","name","Csqin");
        jedis.hset("user","password","123");
        jedis.hset("user","gender","male");
        //2.2获取数据
        Map<String, String> user = jedis.hgetAll("user");
        for (String key : user.keySet()) {
            String value = user.get(key);
            System.out.println(key+":"+value);
        }
        //3.关闭连接
        jedis.close();
    }


    /**
     * List类型数据操作
     */

    @Test
    public void  test4()
    {
        //1.获取连接
        Jedis jedis=new Jedis();//默认值 "localhost",6379
        //2.1存储
        //从左边存
        jedis.lpush("mylist","a","b","c");
        //从右边1存
        jedis.rpush("mylist","a","b","c");
        //2.2获取数据
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);//   [c, b, a, a, b, c]

        //2.3左边弹出一个数据
        String lpop = jedis.lpop("mylist");
        System.out.println(lpop);
        //2.3右边弹出一个数据
        String rpop = jedis.rpop("mylist");
        System.out.println(rpop);

        List<String> mylist1 = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist1);//   [ b, a, a, b]

        //3.关闭连接
        jedis.close();
    }

    /**
     * Set类型数据操作
     */

    @Test
    public void  test5()
    {
        //1.获取连接
        Jedis jedis=new Jedis();//默认值 "localhost",6379
        //2.1存储
        jedis.sadd("myset","java","php","c++");
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        //3.关闭连接
        jedis.close();
    }

    /**
     * Sortedsrt类型数据操作
     */

    @Test
    public void  test6()
    {
        //1.获取连接
        Jedis jedis=new Jedis();//默认值 "localhost",6379
        //2.1存储
            jedis.zadd("mysortedsrt",1,"zhangesa");
            jedis.zadd("mysortedsrt",5,"lisi");
            jedis.zadd("mysortedsrt",3,"wangwu");
            Set<String> mysortedsrt = jedis.zrange("mysortedsrt", 0, -1);
            System.out.println(mysortedsrt);
        //3.关闭连接
        jedis.close();
    }


    /**
     * Jedis连接池使用
     */

    @Test
    public void  test7()
    {
        //0.创建配置对象进行配置
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        //1.获取连接池对象
        JedisPool  jedisPool=new JedisPool(jedisPoolConfig,"localhost",6379);

        //2.获取jedis对象
        Jedis jedis = jedisPool.getResource();

        //3.使用
        jedis.set("hh","2333");
        String value= jedis.get("hh");
        System.out.println(value);

        //4.归还jedis对象到连接池
        jedis.close();
    }

    /**
     * JedisUitl工具类连接池使用
     */

    @Test
    public void  test8()
    {
        //使用工具类获取对象
        Jedis jedis = JedisUtils.getJedis();
        //3.使用
        jedis.set("Hellow","word");
        String value= jedis.get("Hellow");
        System.out.println(value);
        //4.归还jedis对象到连接池
        jedis.close();
    }
}
