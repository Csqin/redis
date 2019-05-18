package it.mode.dao.impl;
import it.mode.dao.ProvinceDao;
import it.mode.domain.Province;
import it.mode.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    //声名成员变量
    private  JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findAll() {
        String sql="select * from province";
        List<Province> provinces = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return provinces;
    }
}
