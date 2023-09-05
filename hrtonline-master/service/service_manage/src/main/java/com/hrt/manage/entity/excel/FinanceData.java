package com.hrt.manage.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceData {
    @Override
    public String toString() {
        return "FinanceData{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userTeam='" + userTeam + '\'' +
                ", userGroup='" + userGroup + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", pruchaseType='" + pruchaseType + '\'' +
                ", purchaseDesc='" + purchaseDesc + '\'' +
                ", financeType='" + financeType + '\'' +
                ", advanceType='" + advanceType + '\'' +
                ", financeState='" + financeState + '\'' +
                ", remark='" + remark + '\'' +
                ", financeFile='" + financeFile + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(String userTeam) {
        this.userTeam = userTeam;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getPruchaseType() {
        return pruchaseType;
    }

    public void setPruchaseType(String pruchaseType) {
        this.pruchaseType = pruchaseType;
    }

    public String getPurchaseDesc() {
        return purchaseDesc;
    }

    public void setPurchaseDesc(String purchaseDesc) {
        this.purchaseDesc = purchaseDesc;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    public String getAdvanceType() {
        return advanceType;
    }

    public void setAdvanceType(String advanceType) {
        this.advanceType = advanceType;
    }

    public String getFinanceState() {
        return financeState;
    }

    public void setFinanceState(String financeState) {
        this.financeState = financeState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFinanceFile() {
        return financeFile;
    }

    public void setFinanceFile(String financeFile) {
        this.financeFile = financeFile;
    }

    @ExcelProperty("单号")
    private Long id;

    @ExcelProperty("上交时间")
    private Date gmtCreate;

    @ExcelProperty("车队id")
    private Long userId;

    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty("队伍")
    private String userTeam;

    @ExcelProperty("组别")
    private String userGroup;

    @ExcelProperty("发票类型")
    private String invoiceType;

    @ExcelProperty("发票金额")
    private BigDecimal invoiceAmount;

    @ExcelProperty("购买物品类型")
    private String pruchaseType;

    @ExcelProperty("购买物品描述")
    private String purchaseDesc;

    @ExcelProperty("报销类型")
    private String financeType;

    @ExcelProperty("垫付类型")
    private String advanceType;

    @ExcelProperty("报销状态")
    private String financeState;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("文件地址")
    private String financeFile;

}
