/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataColumnCollection
 * Author:   sun2
 * Date:     2018/3/26 19:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data.datatable;

import kkam.common.KeyValueListOf;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/3/26 19:46
 * Description:  
 */
public class DataColumnCollection {

    private KeyValueListOf<String, DataColumn> fieldColl;


    public DataColumnCollection() {
        this.fieldColl = new KeyValueListOf<String, DataColumn>() ;

     }

    public  void add(String columnName,DataColumn column){
        this.fieldColl.add(columnName,column);
    }
    public  void add(String columnName,String caption,int  sqlType){
        DataColumn dcl=new  DataColumn();
        dcl.setColumnName(columnName);
        dcl.setCaption(caption);
        dcl.setType(sqlType);

        this.fieldColl.add(columnName,dcl);
     }
    public DataColumn getDataColumn(String columnName){
       return this.fieldColl.getValue(columnName);
     }
    public DataColumn getDataColumn(int  index){
        return this.fieldColl.getValue(index);
    }
    public  int size( ){
        return fieldColl.size();
    }
}
