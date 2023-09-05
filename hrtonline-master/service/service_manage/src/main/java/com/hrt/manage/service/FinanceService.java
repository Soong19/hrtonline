package com.hrt.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrt.manage.entity.Finance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrt.manage.entity.excel.FinanceData;
import com.hrt.manage.query.FinanceQuery;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
public interface FinanceService extends IService<Finance> {
    void pageQuery(Page<Finance> pageParam, FinanceQuery financeQuery);
    List<Finance> pageQuery(FinanceQuery financeQuery);
    List<FinanceData> toFinanceDataList(List<Finance> financeList);
}
