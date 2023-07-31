package edu.xiao.java01jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.xiao.java01jwt.model.entity.Resource;
import edu.xiao.java01jwt.service.ResourceService;
import edu.xiao.java01jwt.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

/**
* @author xiaoning
* @description 针对表【ums_resource(资源（接口）表)】的数据库操作Service实现
* @createDate 2023-07-21 20:18:27
*/
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource>
    implements ResourceService{

}




