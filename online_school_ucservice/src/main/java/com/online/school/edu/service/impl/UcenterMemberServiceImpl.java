package com.online.school.edu.service.impl;

import com.online.school.edu.entity.UcenterMember;
import com.online.school.edu.mapper.UcenterMemberMapper;
import com.online.school.edu.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Resource
    private UcenterMemberMapper ucenterMemberMapper;

    @Override
    public Integer countRegisterNum(String day) {
        return ucenterMemberMapper.countRegisterNum(day);
    }
}
