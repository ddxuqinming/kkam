/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ChildTableRelation
 * Author:   sun2
 * Date:     2019/8/6 20:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2019/8/6 20:46
 * Description:  
 */ //子表关系
class ChildTableRelation{
    public String parentFieldName;//如主表的FID
    public String childName;
    public String childForeginField;//如FMainID
    public String dbChildTableName;
    public String[] childKeyFields;//如 FAutoID
}
