package com.hrt.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrt.manage.entity.Absence;
import com.hrt.manage.mapper.AbsenceMapper;
import com.hrt.manage.query.AbsenceQuery;
import com.hrt.manage.service.AbsenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
@Service
public class AbsenceServiceImpl extends ServiceImpl<AbsenceMapper, Absence> implements AbsenceService {
    @Override
    public void pageQuery(Page<Absence> pageParam, AbsenceQuery absenceQuery) {
        QueryWrapper<Absence> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if (absenceQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String absenceState = absenceQuery.getAbsenceState();
        String absenceTeam = absenceQuery.getAbsenceTeam();
        Long userId = absenceQuery.getUserId();
        String userTeam = absenceQuery.getUserTeam();
        String userGroup = absenceQuery.getUserGroup();
        String userName = absenceQuery.getUserName();
        String begin = absenceQuery.getBegin();
        String end = absenceQuery.getEnd();


        if (!StringUtils.isEmpty(absenceState)) {
            queryWrapper.eq("absence_state", absenceState);
        }
        if (!StringUtils.isEmpty(absenceTeam)) {
            queryWrapper.eq("absence_team", absenceTeam);
        }
        if (!StringUtils.isEmpty(userId)) {
            queryWrapper.eq("user_id", userId);
        }
        if (!StringUtils.isEmpty(userGroup)) {
            queryWrapper.eq("user_group", userGroup);
        }
        if (!StringUtils.isEmpty(userTeam)) {
            queryWrapper.eq("user_team", userTeam);
        }
        if (!StringUtils.isEmpty(userName)) {
            queryWrapper.like("user_name", userName);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
