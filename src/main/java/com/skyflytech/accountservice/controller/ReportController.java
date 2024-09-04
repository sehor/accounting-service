package com.skyflytech.accountservice.controller;

import com.skyflytech.accountservice.report.IncomeStatement;
import com.skyflytech.accountservice.service.ReportService;
import com.skyflytech.accountservice.domain.AccountingPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/income-statement")
    public ResponseEntity<IncomeStatement> getIncomeStatement(
            @RequestParam String accountSetId,
            @RequestParam String beginPeriod,
            @RequestParam String endPeriod) {
        
        // 创建 AccountingPeriod 对象
        AccountingPeriod begin = new AccountingPeriod(beginPeriod);
        AccountingPeriod end = new AccountingPeriod(endPeriod);

        // 创建 IncomeStatement 实例
        IncomeStatement incomeStatement = new IncomeStatement(accountSetId, begin, end);

        // 初始化默认项目
        reportService.initializeDefaultIncomeStatementItems(incomeStatement);

        // 这里应该添加从数据库或其他数据源获取实际数据的逻辑
        // 为了演示，我们只是设置一些示例数据
        incomeStatement.getOperatingRevenue().setAmount(new java.math.BigDecimal("100000"));
        incomeStatement.getOperatingCost().setAmount(new java.math.BigDecimal("60000"));
        incomeStatement.getTaxesAndSurcharges().setAmount(new java.math.BigDecimal("5000"));
        // ... 设置其他项目的金额 ...

        // 计算利润表
        reportService.calculateIncomeStatement(incomeStatement);

        return ResponseEntity.ok(incomeStatement);
    }
}