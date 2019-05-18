package it.mode.service;

import it.mode.domain.Province;

import java.util.List;

public interface ProvinceService {

    /**
     * 查询所有的省份
     * @return
     */
    public List<Province> findAll();

    /**
     * 使用redis数据库进行缓存优化
     * @return
     */
    public String findAll_Json();
}
