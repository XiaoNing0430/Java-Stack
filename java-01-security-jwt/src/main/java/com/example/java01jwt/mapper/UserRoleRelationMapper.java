package com.example.java01jwt.mapper;

import com.example.java01jwt.model.entity.Resource;
import com.example.java01jwt.model.entity.UserRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xiaoning
 * @description 针对表【ums_user_role_relation(用户角色关联表)】的数据库操作Mapper
 * @createDate 2023-07-21 20:18:27
 * @Entity edu.xiao.java01jwt.entity.UserRoleRelation
 */
@Mapper
public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {

    /**
     * 根据用户id查询列表
     *
     * @param id
     * @return
     */
    List<Resource> listResourceByUserId(Long id);
}