/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataRow
 * Author:   sun2
 * Date:     2018/3/27 18:48
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
 * Date:     2018/3/27 18:48
 * Description:  
 */
public class DataRow {
    private KeyValueListOf<String,  Object> fieldValueColl;
    private DataRowState rowState;
    public DataTable dataTable;


    public DataRow()   {
        fieldValueColl=new KeyValueListOf<String,  Object>();
    }

    public  void Add(String columnName, Object value){
        this.fieldValueColl.add(columnName,value);
    }
    public void setValue(String columnName,Object value){
            this.fieldValueColl.setValue(columnName,value);
            if (this.rowState!=DataRowState.Added &&this.rowState!=DataRowState.Deleted)
              this.rowState=DataRowState.Modified;
    }
    public void setValue(int columnIndex,Object value){
        this.fieldValueColl.setValue(columnIndex,value);
        if (this.rowState!=DataRowState.Added &&this.rowState!=DataRowState.Deleted)
            this.rowState=DataRowState.Modified;
    }
    public void  delete(){
        if (this.rowState==DataRowState.Added)
            dataTable.rows.remove(this);
        else
            this.rowState=DataRowState.Deleted;
    }
    public Object getValue(String columnName){
      return   this.fieldValueColl.getValue(columnName);
     }
    public Object getValue(int columnIndex){
        return   this.fieldValueColl.getValue(columnIndex);
    }

    public String getAsString(String columnName){
        Object v=this.fieldValueColl.getValue(columnName);
        if (v==null) return "";
        else  return  v.toString();
     }
    public     Integer getAsInteger(String columnName){
        Object v=this.fieldValueColl.getValue(columnName);
        if (v==null) return null;
        else  return  Integer.parseInt(v.toString());
    }
    public     int getAsIntegerZ(String columnName){
        Object v=this.fieldValueColl.getValue(columnName);
        if (v==null) return 0;
        else  return  Integer.parseInt(v.toString());
    }
    public    Double getAsDouble(String columnName){
        Object v=this.fieldValueColl.getValue(columnName);
        if (v==null) return null;
        else  return  Double.parseDouble(v.toString());
    }
    public      double getAsDoubleZ(String columnName){
         Object v=this.fieldValueColl.getValue(columnName);
         if (v==null) return 0d;
         else  return  Double.parseDouble(v.toString());


    }


    public  int size(){
        return fieldValueColl.size();
    }
    public  void setDataRowState(DataRowState dataRowState){
        rowState=dataRowState;
    }
    public  DataRowState getDataRowState(){
       return  rowState;
    }

    public int getColumnIndex(String columnName){
       return   this.fieldValueColl.getKeyIndex(columnName);

    }
}
