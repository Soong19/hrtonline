package com.hrt.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrt.manage.entity.Absence;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrt.manage.query.AbsenceQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
public interface AbsenceService extends IService<Absence> {
    void pageQuery(Page<Absence> pageParam, AbsenceQuery absenceQuery);
}
