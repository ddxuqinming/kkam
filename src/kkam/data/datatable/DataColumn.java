/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataColumn
 * Author:   sun2
 * Date:     2018/3/17 19:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.data.datatable;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/3/17 19:09
 * Description:  
 */
public class DataColumn {
    private String columnName;
    private String caption;
    private  int type;
    public DataColumn(){
      }
    public DataColumn( String columnName,String caption,int type){
        this.columnName=columnName;
        this.caption=caption;
        this.type=type;
    }
    public int getType() {
        return type;
    }

    public void setType(int sqlType) {
        this.type = sqlType;
    }


    public String getColumnName() {
        return columnName;
    }


    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
  public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


}
