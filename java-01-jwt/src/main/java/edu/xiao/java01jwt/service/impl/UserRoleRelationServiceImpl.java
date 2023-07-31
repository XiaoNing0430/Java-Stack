package edu.xiao.java01jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.xiao.java01jwt.model.entity.UserRoleRelation;
import edu.xiao.java01jwt.service.UserRoleRelationService;
import edu.xiao.java01jwt.mapper.UserRoleRelationMapper;
import org.springframework.stereotype.Service;

/**
* @author xiaoning
* @description 针对表【ums_user_role_relation(用户角色关联表)】的数据库操作Service实现
* @createDate 2023-07-21 20:18:27
*/
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation>
    implements UserRoleRelationService{

}




