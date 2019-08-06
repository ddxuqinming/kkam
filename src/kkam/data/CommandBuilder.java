/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CommandBuilder
 * Author:   sun2
 * Date:     2018/4/3 19:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data;
import kkam.common.Func;
import kkam.common.KeyValueListOf;
import kkam.data.datatable.*;

import java.sql.*;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/4/3 19:38
 * Description:  
 */
public class CommandBuilder {
    private String tableName;
    DataAccess xudb;

    public CommandBuilder(String tableName,DataAccess xudb){
        this.tableName=tableName;
        this.xudb=xudb;
     }
     public  SQLCommand createInsertCommand(DataRow dataRow){
        try {
            ResultSet rs= xudb.getResultSet("select * from " + tableName + " where 1=0");
            ResultSetMetaData rsmd = rs.getMetaData();

            //生成sql
            String sql="INSERT INTO   " + tableName ;
            String fields="";
            String values="";
            SQLCommand cmd=new SQLCommand();
            String columnName;
            for(int i=0;i<rsmd.getColumnCount();i++) {
                columnName= rsmd.getColumnName(i + 1);

               // names.put(rsmd.getColumnName(i+1), rsmd.getColumnType(i+1)  );
                if (  rsmd.isAutoIncrement(i + 1) ) {
                    cmd.AutoColumn=columnName;
                 }else   {
                    if ( dataRow.getColumnIndex(columnName)>=0) {

                        fields += columnName + ",";
                        values+="?" + ",";
                        cmd.FieldValues.add(columnName,dataRow.getValue(columnName));
                    }

                }
              }
            rs.close();
            sql=sql   + "(" + Func.trimEnd(fields,",") + ") values (" + Func.trimEnd(values,",") + ")";
            cmd.sqlText = sql;
            return cmd;
        }catch (SQLException ex){
            throw new RuntimeException(ex);

        }


      }
     public  SQLCommand createInsertCommand(KeyValueListOf<String,Object> kv){
        try {
            ResultSet rs= xudb.getResultSet("select * from " + tableName + " where 1=0");
            ResultSetMetaData rsmd = rs.getMetaData();

            //生成sql
            String sql="INSERT INTO   " + tableName ;
            String fields="";
            String values="";
            SQLCommand cmd=new SQLCommand();
            String columnName;
            for(int i=0;i<rsmd.getColumnCount();i++) {
                columnName= rsmd.getColumnName(i + 1);

                // names.put(rsmd.getColumnName(i+1), rsmd.getColumnType(i+1)  );
                if (  rsmd.isAutoIncrement(i + 1) ) {
                    cmd.AutoColumn=columnName;
                }else   {
                    if ( kv.isExist(columnName) ) {

                        fields += columnName + ",";
                        values+="?" + ",";
                        cmd.FieldValues.add(columnName,kv.getValue(columnName));
                    }

                }
            }
            rs.close();
            sql=sql   + "(" + Func.trimEnd(fields,",") + ") values (" + Func.trimEnd(values,",") + ")";
            cmd.sqlText = sql;
            return cmd;
        }catch (SQLException ex){
            throw new RuntimeException(ex);

        }


    }
    public  SQLCommand createUpdateCommand(DataRow dataRow, String[] keyFields){
        try {
            ResultSet rs= xudb.getResultSet("select * from " + tableName + " where 1=0");
            ResultSetMetaData rsmd = rs.getMetaData();

            //生成sql
            String sql="UPDATE " + tableName ;
            String fields="";

            SQLCommand cmd=new SQLCommand();
            String columnName;

            for(int i=0;i<rsmd.getColumnCount();i++) {
                columnName= rsmd.getColumnName(i + 1);

                if (! rsmd.isAutoIncrement(i + 1)&& !Func.ArrayIsExist(keyFields,columnName) && dataRow.getColumnIndex(columnName)>=0) {

                    fields += columnName + "=?,";
                    cmd.FieldValues.add(columnName,dataRow.getValue(columnName));
                }

            }
            rs.close();
            //
            String where="";
            for(int i=0;i<keyFields.length;i++) {
                columnName=keyFields[i];
                if (i>0)  where += " and ";
                 where += columnName + "=?";
                cmd.FieldValues.add(columnName,dataRow.getValue(columnName));


            }
            sql=sql   + " set " + Func.trimEnd(fields,",") + " where " +  where  ;
            cmd.sqlText = sql;
            return cmd;
        }catch (SQLException ex){
            throw new RuntimeException(ex);

        }


    }

    public  SQLCommand createUpdateCommand(KeyValueListOf<String,Object> kv, String[] keyFields){
        try {
            ResultSet rs= xudb.getResultSet("select * from " + tableName + " where 1=0");
            ResultSetMetaData rsmd = rs.getMetaData();

            //生成sql
            String sql="UPDATE " + tableName ;
            String fields="";

            SQLCommand cmd=new SQLCommand();
            String columnName;

            for(int i=0;i<rsmd.getColumnCount();i++) {
                columnName= rsmd.getColumnName(i + 1);

                if (! rsmd.isAutoIncrement(i + 1)&& !Func.ArrayIsExist(keyFields,columnName) && kv.isExist(columnName) ) {

                    fields += columnName + "=?,";
                    cmd.FieldValues.add(columnName,kv.getValue(columnName));
                }

            }
            rs.close();
            //
            String where="";
            for(int i=0;i<keyFields.length;i++) {
                columnName=keyFields[i];
                if (i>0)  where += " and ";
                where += columnName + "=?";
                cmd.FieldValues.add(columnName,kv.getValue(columnName));


            }
            sql=sql   + " set " + Func.trimEnd(fields,",") + " where " +  where  ;
            cmd.sqlText = sql;
            return cmd;
        }catch (SQLException ex){
            throw new RuntimeException(ex);

        }


    }

    public  SQLCommand createDeleteCommand(DataRow dataRow, String[] keyFields){
        try {
              //生成sql
            String sql="DELETE " + tableName ;
            SQLCommand cmd=new SQLCommand();
            String columnName;
            String where="";
            for(int i=0;i<keyFields.length;i++) {
                columnName=keyFields[i];
                if (i>0)  where += " and ";
                where += columnName + "=?";
                cmd.FieldValues.add(columnName,dataRow.getValue(columnName));
           }
            sql=sql    + " where " +  where  ;
            cmd.sqlText = sql;
            return cmd;
        }catch (Exception ex){
            throw new RuntimeException(ex);

        }


    }

    public  SQLCommand createDeleteCommand(KeyValueListOf<String,Object> kv, String[] keyFields){
        try {
            //生成sql
            String sql="DELETE " + tableName ;
            SQLCommand cmd=new SQLCommand();
            String columnName;
            String where="";
            for(int i=0;i<keyFields.length;i++) {
                columnName=keyFields[i];
                if (i>0)  where += " and ";
                where += columnName + "=?";
                cmd.FieldValues.add(columnName,kv.getValue(columnName));
            }
            sql=sql    + " where " +  where  ;
            cmd.sqlText = sql;
            return cmd;
        }catch (Exception ex){
            throw new RuntimeException(ex);

        }


    }
}


