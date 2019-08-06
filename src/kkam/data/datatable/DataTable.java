/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataTable
 * Author:   sun2
 * Date:     2018/3/17 18:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data.datatable;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/3/17 18:15
 * Description: 数据表
 */
public class DataTable {


    private String tableName;
    public DataRowCollection rows;
    public DataColumnCollection columns;

    public  DataTable(){
        rows=new DataRowCollection();
        columns=new DataColumnCollection();
     }
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DataRow rows(int index){
        return  rows.getDataRow(index);
    }

    public DataColumn columns(int index){
        return  columns.getDataColumn(index);
    }
    public DataColumn columns(String columnName){
        return  columns.getDataColumn(columnName);
    }
    public DataRow newRow(){
        DataRow row=new DataRow();
        row.dataTable=this;
        row.setDataRowState(DataRowState.Added);
        int size=columns.size();
        for (int i=0;i<size;i++){
            row.Add(columns.getDataColumn(i).getColumnName(),null);
        }
        this.rows.add(row);
        return row;
     }
    public void acceptChanges(){
        for (int i=0;i<this.rows.size();i++)
            this.rows(i).setDataRowState(DataRowState.Unchanged);

     }
}

