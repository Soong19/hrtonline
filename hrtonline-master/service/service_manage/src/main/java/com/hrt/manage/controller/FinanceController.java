package com.hrt.manage.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrt.commonutils.R;
import com.hrt.manage.client.OssClient;
import com.hrt.manage.entity.Finance;
import com.hrt.manage.entity.excel.FinanceData;
import com.hrt.manage.query.FinanceQuery;
import com.hrt.manage.service.FinanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/manage/finance")
public class FinanceController {
    @Autowired
    private FinanceService financeService;

    @Autowired
    private OssClient ossClient;


    //根据id查询
    @PostMapping("getById/{id}")
    public R getById(
            @PathVariable Long id
    ){
        Finance byId = financeService.getById(id);
        return R.ok().data("data",byId);
    }

    //添加
    @ApiOperation(value = "新增财务记录")
    @PostMapping(name="addFinance")
    public R addFinance(
            @ApiParam(name = "finance", value = "财务对象", required = true)
             @RequestBody Finance finance
    ) {
        boolean b = financeService.save(finance);
        return b?R.ok():R.error();
    }

    //根据id单号删除记录
    @ApiOperation(value = "根据id删除财务记录")
    @DeleteMapping("removeFinance/{id}")
    public R removeById(
            @PathVariable Long id
    ) {
        boolean b = financeService.removeById(id);
        return b ? R.ok() : R.error();
    }


    //根据id单号更新记录
    @ApiOperation(value = "根据id更新财务记录")
    @PostMapping("updateFinance/{id}")
    public R updateById(
            @PathVariable Long id,
            @RequestBody Finance finance
    ) {
        Finance byId = financeService.getById(id);
        finance.setId(id);
        finance.setVersion(byId.getVersion());

        boolean b = financeService.updateById(finance);
        return b ? R.ok() : R.error();
    }

    // 根据条件查询并分页
//    @Secured(value = "ROLE_admin")
    @PreAuthorize("hasRole('admin')")
    @ApiOperation(value = "根据条件查询并分页")
    @PostMapping("getFinancePage/{current}/{limit}")
    public R getFinancePage(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "userQuery", value = "查询对象", required = false)
            @RequestBody FinanceQuery financeQuery
    ) {
        Page<Finance> financePage = new Page<>(current, limit);

        financeService.pageQuery(financePage, financeQuery);

        List<Finance> records = financePage.getRecords();
        long total =financePage.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    //查询和财务数据导出
    @PostMapping("exportFinanceData")
    public R exportFinanceData(
            @RequestBody FinanceQuery financeQuery
    ){
        //获取
        List<FinanceData> listData =financeService
                .toFinanceDataList(financeService.pageQuery(financeQuery));

        //设置日期格式，方便命名
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String time = df.format(new Date());
        //新建xlsx文件，当前项目路径
        String fileName= time+".xlsx";
        File file=new File(fileName);

        //数据写入excel文件
        EasyExcel.write(file,FinanceData.class)
                .sheet("财务数据")
                .doWrite(listData);

        //获取url，内置于data中
        R r = ossClient.uploadOssFile(fileName);

        boolean b = file.delete();
        return b? r:R.error();
    }


}

