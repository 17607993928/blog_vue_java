package com.xjs.mapper;

import com.xjs.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiejs
 * @since 2020-08-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
