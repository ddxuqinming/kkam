/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ItemBase
 * Author:   sun2
 * Date:     2019/7/19 20:38
 * Description: 业务对象基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data;

import kkam.data.datatable.DataTable;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2019/7/19 20:38
 * Description:  业务对象基类
 */
public class ItemBase {

    private  FieldValueCollection fieldValues;
    private  ChildTableCollection childTables;
    public FieldValueCollection  FieldValues() {
        if (fieldValues==null ) fieldValues=new  FieldValueCollection();

        return fieldValues;
    }
    public ChildTableCollection  ChildTables() {
        if (childTables==null ) childTables=new  ChildTableCollection();
        return childTables;
    }

    public DataTable getChildTable(int index) {
        return ChildTables().getValue(index);
    }
    public void setValue(String field,Object value){
        this.FieldValues().setValue(field,value);
    }
    public Object getValue(String field ){
       return this.FieldValues().getValue(field);
    }
    public String toJson(){
        String strr="{";
        for(int i=0;i<fieldValues.size();i++) {
            if (i>0)  strr += ",";
            strr += fieldValues.getKey(i) + ":'" + fieldValues.getValue(i) + "'";
        }
        strr += "}";
        return  strr;
    }


}
