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

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/3/26 19:46
 * Description:  
 */
public class DataRowCollection {

    private List<DataRow> rowColl;


    public DataRowCollection() {
        this.rowColl = new ArrayList<DataRow>() ;

     }

    public  void add(DataRow row ){
        this.rowColl.add(row);
  }
    public  void remove(DataRow row ){
        this.rowColl.remove(row);
    }
    public  void remove(int rowIndex ){
        this.rowColl.remove(rowIndex);
    }
    public DataRow getDataRow(int  index){
        return this.rowColl.get(index);
    }
    public  int size( ){
        return rowColl.size();
    }
}
