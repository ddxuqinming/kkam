/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataRowState
 * Author:   sun2
 * Date:     2018/3/27 19:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data.datatable;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/3/27 19:05
 * Description:  
 */

public enum DataRowState {
    Unchanged(2),Added(4),Deleted(8),Modified(16);
    private final int value;
    //构造方法必须是private或者默认
    private DataRowState(int value) {
        this.value = value;
    }

    public DataRowState valueOf(int value) {
        switch (value) {
            case 2:
                return DataRowState.Unchanged;
            case 4:
                return DataRowState.Added;
            case 8:
                return DataRowState.Deleted;
            case 16:
                return DataRowState.Modified;
            default:
                return null;
        }
    }
}