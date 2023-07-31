package edu.xiao.java01jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.xiao.java01jwt.model.entity.Role;
import edu.xiao.java01jwt.service.RoleService;
import edu.xiao.java01jwt.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author xiaoning
* @description 针对表【ums_role(角色表)】的数据库操作Service实现
* @createDate 2023-07-21 20:18:27
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




