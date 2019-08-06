/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: test
 * Author:   sun2
 * Date:     2018/3/28 19:54
 * Description: 测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data;
import kkam.data.ItemBase;
import kkam.data.ItemManageBase;
import kkam.data.DataAccess;
import kkam.data.datatable.*;


/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/3/28 19:54
 * Description:  测试类
 */
public class test {


    public static void main(String[] args)  {
       // System.out.println("loading... kkdao");
         new test().testItembase();


    }

    private   void  testDataTable(){
      // DataTable dtb=new DataTable();
       // dtb.columns.add("FID","ID", Types.TINYINT );
       // dtb.columns.add("FName","姓名", Types.VARCHAR );
      //  DataRow drw=dtb.newRow();
      //  drw.Add("FID",1);
       // drw.Add("FName","xqm");

        System.out.println("hello xqm"  );


    }

    private   void  testDataAccess() {

        DataAccess xudb=new DataAccess();
       // xudb.createMySqlUrl("localhost",3306,"xusoft","root","111",null);
         xudb.createSqlServerUrl("localhost",1433,"a","sa","sina",null);
        System.out.println(xudb.getUrl());
        xudb.beginTrans();
        int i= xudb.exeSql("update uemployee set FName='AA7' where  FID=?",  new Object[]{"2"});
        xudb.rollbackTrans();
        DataTable  dtb= xudb.getDataTable("select  FID,FName,FAge from uEmployee where FID=1");
        dtb.rows(0).setValue("FName","这271");
        // dtb.rows(1).setValue("FName","这2");
        dtb.rows(0).delete();
       DataRow drw=  dtb.newRow();
        drw.setValue("FName","这3444");
      drw.setValue(2,"444");

       xudb.saveTable(dtb,"uEmployee",new String[]{"FID"});
        System.out.println("AutoID=" + drw.getValue("FID"));


    }
    private   void  testItembase() {

        DataAccess xudb=new DataAccess();
       //  xudb.createMySqlUrl("localhost",3306,"xusoft","root","111",null);
        xudb.createSqlServerUrl("localhost",1433,"a","sa","sina",null);
        System.out.println(xudb.getUrl());
        ItemManageBase M=new ItemManageBase("uEmployee","FID");
        M.addChildDBTable("address","FMainId","uAddress",new String[]{"FAutoID","FMainID"});
        M.xudb=xudb;
        xudb.printSql=true;
       ;
        ItemBase item = M.getItem(35);
        item.setValue("FName","喜地方new");
        item.setValue("FAge","1");
        //子表测试
        DataTable dtb= item.getChildTable(0);

        DataRow drw = dtb.rows(0);
        drw.setValue("FAdress","上海市嘉定区123号3ffff");
        drw.setValue("FZip","2300813");
        DataRow drw3 = dtb.newRow();
         drw3.setValue("FAdress","北京朝阳大道99号3");
         drw3.setValue("FZip","10010013");

        M.update(item);


        System.out.println("FAutoID=" + drw3.getValue("FAutoID"));

    }

    private  void  testClass(){

    }


}


