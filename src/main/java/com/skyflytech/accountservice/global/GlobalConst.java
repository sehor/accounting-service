package com.skyflytech.accountservice.global;

import com.skyflytech.accountservice.domain.account.TransferAccountType;
import com.skyflytech.accountservice.domain.account.AccountType;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class GlobalConst {

    // 防止实例化
    private GlobalConst() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    //科目编号(id)结构XXXX-XXXX-XXX-XXX
    public static final int[] ACCOUNT_Code_LENGTH = {4, 4, 3, 3};

    public static final String ACCOUNT_SET_ID_FOR_TEST = "accountSetIdForTest";

    // 本年利润科目代码
    public static final String CURRENT_YEAR_PROFIT_CODE = "3103";

    // 未分配利润科目代码
    public static final String UNDISTRIBUTED_PROFIT_CODE = "31040015";

    // 存储需要在期末自动结转的账户类型
    public static final Map<TransferAccountType, List<AccountType>> AUTO_TRANSFER_ACCOUNTS;

    public static final String Current_AccountSet_Id_Test = "accountSetId_for_test";

    static {
        Map<TransferAccountType, List<AccountType>> tempMap = new EnumMap<>(TransferAccountType.class);
        tempMap.put(TransferAccountType.INCOME, Arrays.asList(AccountType.OPERATING_REVENUE, AccountType.OTHER_INCOME));
        tempMap.put(TransferAccountType.COST_AND_EXPENSE, Arrays.asList(AccountType.COST, AccountType.OPERATING_COST_TAX, AccountType.PERIOD_EXPENSE));
        tempMap.put(TransferAccountType.NON_OPERATING_EXPENSE, Arrays.asList(AccountType.OTHER_EXPENSE));
        tempMap.put(TransferAccountType.PRIOR_YEAR_ADJUSTMENT, Arrays.asList(AccountType.PRIOR_YEAR_ADJUSTMENT));

        AUTO_TRANSFER_ACCOUNTS = Collections.unmodifiableMap(tempMap);
    }
}