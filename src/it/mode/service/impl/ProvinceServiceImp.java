package it.mode.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.cast.jedis.util.JedisUtils;
import it.mode.dao.ProvinceDao;
import it.mode.dao.impl.ProvinceDaoImpl;
import it.mode.domain.Province;
import it.mode.service.ProvinceService;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImp implements ProvinceService {
    private  ProvinceDao dao=new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    @Override
    public String findAll_Json() {
        //使用缓存技术降低数据库io操作
        Jedis jedis = JedisUtils.getJedis();
        String province_json = jedis.get("province");
        if (province_json ==null || province_json.length() ==0)
        {
            //redis没有数据，查询数据库
            System.out.println("redis没有数据，查询数据库");
            List<Province> list = dao.findAll();
            //list序列化json
            ObjectMapper mapper=new ObjectMapper();
            try {
              province_json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //添加到redis数据库中
            jedis.set("province",province_json);
            jedis.close();
        }
        else{
            System.out.println("redis有数据，使用缓存");
        }
        //返回json数据
        return province_json;
    }
}
