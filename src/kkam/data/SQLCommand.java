/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SQLCommand
 * Author:   sun2
 * Date:     2018/4/3 20:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data;

import kkam.common.KeyValueListOf;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/4/3 20:15
 * Description:  
 */
public class SQLCommand {
    public  String sqlText;
    public String AutoColumn="";
    public KeyValueListOf<String,Object> FieldValues;
    public SQLCommand(){
        sqlText="";
        FieldValues=new KeyValueListOf<>();
     }
}
