package com.hrt.manage.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrt.manage.entity.Finance;
import com.hrt.manage.entity.User;
import com.hrt.manage.entity.excel.FinanceData;
import com.hrt.manage.mapper.FinanceMapper;
import com.hrt.manage.query.FinanceQuery;
import com.hrt.manage.query.UserQuery;
import com.hrt.manage.service.FinanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements FinanceService {

    @Override
    public void pageQuery(Page<Finance> pageParam, FinanceQuery financeQuery)
    {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if (financeQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        Long userId=financeQuery.getUserId();
        String userTeam = financeQuery.getUserTeam();
        String userGroup = financeQuery.getUserGroup();
        String userName = financeQuery.getUserName();

        BigDecimal amountBegin=financeQuery.getAmountBegin();
        BigDecimal amountEnd=financeQuery.getAmountEnd();

        String financeState = financeQuery.getFinanceState();
        String financeType = financeQuery.getFinanceType();
        String invoiceType = financeQuery.getInvoiceType();
        String purchaseType = financeQuery.getPurchaseType();
        String advanceType = financeQuery.getAdvanceType();
        String begin = financeQuery.getBegin();
        String end = financeQuery.getEnd();



        if (!StringUtils.isEmpty(amountBegin)) {
            queryWrapper.ge("invoice_amount", amountBegin);
        }
        if (!StringUtils.isEmpty(amountEnd)) {
            queryWrapper.le("invoice_amount", amountEnd);
        }
        if (!StringUtils.isEmpty(financeState)) {
            queryWrapper.eq("finance_state", financeState);
        }
        if (!StringUtils.isEmpty(financeType)) {
            queryWrapper.eq("finance_type", financeType);
        }
        if (!StringUtils.isEmpty(invoiceType)) {
            queryWrapper.eq("invoice_type", invoiceType);
        }
        if (!StringUtils.isEmpty(purchaseType)) {
            queryWrapper.eq("purchase_type", purchaseType);
        }
        if (!StringUtils.isEmpty(advanceType)) {
            queryWrapper.eq("invoice_amount", advanceType);
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

    @Override
    public List<Finance> pageQuery(FinanceQuery financeQuery) {
        QueryWrapper<Finance> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (financeQuery == null){
            return baseMapper.selectList(null);
        }

        Long userId=financeQuery.getUserId();
        String userTeam = financeQuery.getUserTeam();
        String userGroup = financeQuery.getUserGroup();
        String userName = financeQuery.getUserName();
        BigDecimal amountBegin=financeQuery.getAmountBegin();
        BigDecimal amountEnd=financeQuery.getAmountEnd();
        String financeState = financeQuery.getFinanceState();
        String financeType = financeQuery.getFinanceType();
        String invoiceType = financeQuery.getInvoiceType();
        String purchaseType = financeQuery.getPurchaseType();
        String advanceType = financeQuery.getAdvanceType();
        String begin = financeQuery.getBegin();
        String end = financeQuery.getEnd();

        //定义筛选条件
        if (!StringUtils.isEmpty(amountBegin)) {
            queryWrapper.ge("invoice_amount", amountBegin);
        }
        if (!StringUtils.isEmpty(amountEnd)) {
            queryWrapper.le("invoice_amount", amountEnd);
        }
        if (!StringUtils.isEmpty(financeState)) {
            queryWrapper.eq("finance_state", financeState);
        }
        if (!StringUtils.isEmpty(financeType)) {
            queryWrapper.eq("finance_type", financeType);
        }
        if (!StringUtils.isEmpty(invoiceType)) {
            queryWrapper.eq("invoice_type", invoiceType);
        }
        if (!StringUtils.isEmpty(purchaseType)) {
            queryWrapper.eq("purchase_type", purchaseType);
        }
        if (!StringUtils.isEmpty(advanceType)) {
            queryWrapper.eq("invoice_amount", advanceType);
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
        List<Finance> financeList = baseMapper.selectList(queryWrapper);
        return financeList;
    }

    @Override
    public List<FinanceData> toFinanceDataList(List<Finance> financeList) {
        List<FinanceData> listData = new ArrayList<>();
        //将对象在循环外声明会导致最后的add覆盖所有数据，导致所有数据一样
        for (Finance finance:financeList
        ) {
            FinanceData financeData = new FinanceData();
            financeData.setId(finance.getId());
            financeData.setGmtCreate(finance.getGmtCreate());
            financeData.setUserId(finance.getUserId());
            financeData.setUserName(finance.getUserName());
            financeData.setUserTeam(finance.getUserTeam());
            financeData.setUserGroup(finance.getUserGroup());
            financeData.setInvoiceType(finance.getInvoiceType());
            financeData.setInvoiceAmount(finance.getInvoiceAmount());
            financeData.setPruchaseType(finance.getPurchaseType());
            financeData.setPurchaseDesc(finance.getPurchaseDesc());
            financeData.setFinanceType(finance.getFinanceType());
            financeData.setFinanceState(finance.getFinanceState());
            financeData.setAdvanceType(finance.getAdvanceType());
            financeData.setRemark(finance.getRemark());
            financeData.setFinanceFile(finance.getFinanceFile());

            listData.add(financeData);
        }
        return listData;
    }

}
