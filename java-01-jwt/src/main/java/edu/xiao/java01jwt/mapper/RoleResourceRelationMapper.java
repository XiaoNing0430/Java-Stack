package edu.xiao.java01jwt.mapper;

import edu.xiao.java01jwt.model.entity.RoleResourceRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoning
 * @description 针对表【ums_role_resource_relation(角色资源关系表)】的数据库操作Mapper
 * @createDate 2023-07-21 20:18:27
 * @Entity edu.xiao.java01jwt.entity.RoleResourceRelation
 */
@Mapper
public interface RoleResourceRelationMapper extends BaseMapper<RoleResourceRelation> {

}