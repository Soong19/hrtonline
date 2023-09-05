package com.hrt.manage.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrt.commonutils.R;
import com.hrt.manage.entity.Absence;
import com.hrt.manage.query.AbsenceQuery;
import com.hrt.manage.service.AbsenceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hrt
 * @since 2021-08-12
 */
@RestController
@RequestMapping("/manage/absence")
public class AbsenceController {
    @Autowired
    private AbsenceService absenceService;

    //添加
    @ApiOperation(value = "新增请假记录")
    @PostMapping("addAbsence")
    public R addAbsence(
            @RequestBody Absence absence
    ) {
        absenceService.save(absence);
        return R.ok();
    }

    //删除
    @ApiOperation(value = "根据id删除请假记录")
    @DeleteMapping("removeAbsence/{id}")
    public R removeAbsence(
            @PathVariable Long id
    ){
        boolean b = absenceService.removeById(id);
        return b? R.ok():R.error();
    }

    //更新
    @ApiOperation(value = "根据id更新请假记录")
    @PostMapping("updateAbsence/{id}")
    public R updateAbsence(
            @PathVariable Long id,
            @RequestBody Absence absence
    ){
        Absence byId = absenceService.getById(id);
        absence.setVersion(byId.getVersion());
        absence.setId(id);
        boolean b = absenceService.updateById(absence);
        return b? R.ok():R.error();
    }

    @ApiOperation(value = "根据条件查询并分页")
    @PostMapping("getAbsencePage/{current}/{limit}")
    public R getAbsencePage(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "absenceQuery", value = "查询对象", required = false)
            @RequestBody AbsenceQuery absenceQuery
            ) {
        Page<Absence> leavePage = new Page<>(current, limit);

        absenceService.pageQuery(leavePage, absenceQuery);

        List<Absence> records = leavePage.getRecords();
        long total =leavePage.getTotal();

        return R.ok().data("total", total).data("rows", records);
    }



}

