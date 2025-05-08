package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface AdminMapper {

    List<Admin> selectAll(Admin admin);

    void insert(Admin admin);

    Admin selectByUsername(String username);

    void updateById(Admin admin);

    @Delete("delete from `admin` where id =#{id}")
    void deleteById(Integer id);
}
