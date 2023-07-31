package edu.xiao.java01jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.xiao.java01jwt.model.entity.Menu;
import edu.xiao.java01jwt.service.MenuService;
import edu.xiao.java01jwt.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author xiaoning
* @description 针对表【ums_menu(菜单表)】的数据库操作Service实现
* @createDate 2023-07-21 20:18:27
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




