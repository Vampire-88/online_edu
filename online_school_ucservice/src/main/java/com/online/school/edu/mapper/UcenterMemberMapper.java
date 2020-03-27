package com.online.school.edu.mapper;

import com.online.school.edu.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-03-27
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int countRegisterNum(String day);
}
