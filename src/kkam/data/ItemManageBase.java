/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ItemManageBase
 * Author:   sun2
 * Date:     2019/7/19 20:49
 * Description: 业务管理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data;

import kkam.data.CommandBuilder;
import kkam.data.DataAccess;
import kkam.data.SQLCommand;
import kkam.data.datatable.DataRow;
import kkam.data.datatable.DataRowState;
import kkam.data.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2019/7/19 20:49
 * Description:  业务管理类
 */
public class ItemManageBase {
    public DataAccess xudb=null;
    private String  tableName;
    private String  primaryKey ;
    private List<ChildTableRelation> childTableRelations;
    public  boolean autoUseingTransaction =true;

    public  ItemManageBase(String tableName,String keyField){
       this.tableName=tableName;
       this.primaryKey=keyField;


   }
    public  ItemBase getNewItem(){
        String sql= "select * from " + tableName + " where 1=0"  ;
        DataTable  dtb = this.xudb.getDataTable(sql);
        ItemBase item =new ItemBase();
        for (int i=0;i<dtb.columns.size();i++){
            item.FieldValues().add(dtb.columns(i).getColumnName(),null);
        }
        //添加子表
        if (childTableRelations!=null )  {
            DataTable dtbChild;
            for (int i=0;i<childTableRelations.size();i++){
                ChildTableRelation child=childTableRelations.get(i);
                dtbChild =  onLoadChildDataTable(item,child.childName,child.dbChildTableName, "1=0");
                item.ChildTables().add(child.childName,dtbChild);
            }
        }
        return item;
    }
    public void addChildDBTable(String childName,String childForeginField,String dbChildTableName,String[] childKeyFields){
          ChildTableRelation child=new ChildTableRelation();
          child.parentFieldName=this.primaryKey;
          child.childName=childName;
          child.childForeginField =childForeginField;
          child.dbChildTableName=dbChildTableName;
          child.childKeyFields=childKeyFields;
          if (childTableRelations==null)
              childTableRelations= new ArrayList() ;
        childTableRelations.add(child);

    }
    public   DataTable onLoadChildDataTable(ItemBase item,String childName, String dbChildTableName,String where){
        String sql= "select * from " + dbChildTableName + " where "  +  where;
        DataTable dtb= this.xudb.getDataTable(sql);
        dtb.setTableName(childName);
        return  dtb;
    }
    public  ItemBase getItem(Integer   Key){
       String where= primaryKey + "=" + Key;
       String sql= "select * from " + tableName + " where " +  where;
        DataTable  dtb = this.xudb.getDataTable(sql);
        if ( dtb.rows.size()==0 ) return null;
        ItemBase item =new ItemBase();
        DataRow drwMain = dtb.rows(0);
        for (int i=0;i<dtb.columns.size();i++){
            item.FieldValues().add(dtb.columns(i).getColumnName(),drwMain.getValue(i));
        }
        //添加子表
        if (childTableRelations!=null )  {
            DataTable dtbChild;
            for (int i=0;i<childTableRelations.size();i++){
                ChildTableRelation child=childTableRelations.get(i);
                dtbChild =  onLoadChildDataTable(item,child.childName,child.dbChildTableName,  child.childForeginField + "=" + item.getValue(child.parentFieldName));
                 item.ChildTables().add(child.childName,dtbChild);
            }
        }


       return item;
    }



  public boolean add(ItemBase item){
        String sql= "select * from " + tableName + " where 1=0"  ;
        DataTable  dtb = this.xudb.getDataTable(sql);
        DataRow drwNew = dtb.newRow();
        for (int i=0;i<item.FieldValues().size() ;i++){
            drwNew.setValue(item.FieldValues().getKey(i),item.FieldValues().getValue(i));
        }

       //1插入到数据库
        SQLCommand insertCommand;
        CommandBuilder commandBuilder=new CommandBuilder(this.tableName,this.xudb);
        insertCommand=commandBuilder.createInsertCommand(drwNew);
        if (this.autoUseingTransaction )
            xudb.beginTrans();

       int autoid = this.xudb.insert(insertCommand);
        //2返回自动递增列
        if (insertCommand.AutoColumn!="")
            item.FieldValues().setValue(insertCommand.AutoColumn,autoid);

       //3保存子表
       if (childTableRelations!=null )  {
          DataTable dtbChild;
          Integer parentID = Integer.parseInt( item.getValue(this.primaryKey).toString());
          if (parentID==0) throw new RuntimeException("主表" + primaryKey + "值为空,不能保存子表记录");
          for (int i=0;i<childTableRelations.size();i++){
              ChildTableRelation child=childTableRelations.get(i);
               this.saveChildTable(item.ChildTables().getValue(child.childName),child.dbChildTableName, child.childForeginField,parentID,child.childKeyFields);
          }
      }
      if (this.autoUseingTransaction)
          xudb.commitTrans();
        return  true;
    }

    public boolean update(ItemBase item){
        String where= primaryKey + "=" + item.FieldValues().getValue (primaryKey);
        String sql= "select * from " + tableName + " where " +  where;
        DataTable  dtb = this.xudb.getDataTable(sql);
        if ( dtb.rows.size()==0 ) throw new RuntimeException("update:数据库中找不到对象值");
        DataRow drwItem = dtb.rows(0);
        for (int i=0;i<item.FieldValues().size() ;i++){
            drwItem.setValue(item.FieldValues().getKey(i),item.FieldValues().getValue(i));
        }

        //1保存到数据库
        SQLCommand updateCommand;
        CommandBuilder commandBuilder=new CommandBuilder(this.tableName,this.xudb);
        String[] keyFields = new String[1];
        keyFields[0]=primaryKey  ;

        updateCommand=commandBuilder.createUpdateCommand(drwItem,keyFields);
        if (this.autoUseingTransaction)
            xudb.beginTrans();
        this.xudb.exeSql (updateCommand);


        //3保存子表
        if (childTableRelations!=null )  {
            DataTable dtbChild;
            Integer parentID = Integer.parseInt( item.getValue(this.primaryKey).toString());
            for (int i=0;i<childTableRelations.size();i++){
                ChildTableRelation child=childTableRelations.get(i);
                this.saveChildTable(item.ChildTables().getValue(child.childName),child.dbChildTableName, child.childForeginField,parentID,child.childKeyFields);
            }
        }

        if (this.autoUseingTransaction)
            xudb.commitTrans();
         return  true;
    }

    private  void  saveChildTable(DataTable dtb, String dbtableName, String foreginField, int parentID,String[] childKeyFields){
        for (int i=0;i<dtb.rows.size();i++){
            if (dtb.rows(i).getDataRowState()== DataRowState.Added )
              dtb.rows(i).setValue(foreginField,parentID);
        }
        this.xudb.saveTable(dtb,dbtableName,childKeyFields);
    }
    private  void  deleteChildTable(DataTable dtb, String dbtableName, String foreginField, int parentID,String[] childKeyFields){
        for (int i=0;i<dtb.rows.size();i++){
            if (dtb.rows(i).getDataRowState()!= DataRowState.Added )
                dtb.rows(i).delete();
        }
        this.xudb.saveTable(dtb,dbtableName,childKeyFields);
    }
    public boolean delete(ItemBase item){
        if (this.autoUseingTransaction)
            xudb.beginTrans();
        //1删除子表
        if (childTableRelations!=null )  {
            DataTable dtbChild;
            Integer parentID = Integer.parseInt( item.getValue(this.primaryKey).toString());
            for (int i=0;i<childTableRelations.size();i++){
                ChildTableRelation child=childTableRelations.get(i);
                this.deleteChildTable(item.ChildTables().getValue(child.childName),child.dbChildTableName, child.childForeginField,parentID,child.childKeyFields);
            }
        }
        //2删除主表
        String where= primaryKey + "=" + item.FieldValues().getValue (primaryKey);
        String sql= "delete from " + tableName + " where " +  where;
        this.xudb.exeSql(sql);
        if (this.autoUseingTransaction)
            xudb.commitTrans();
         return  true;
    }
}

