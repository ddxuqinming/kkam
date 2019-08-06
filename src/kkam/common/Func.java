/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Func
 * Author:   sun2
 * Date:     2018/4/3 19:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kkam.common;

/**
 * Copyright (C), 2015-2018,sum
 * Author:   xqm
 * Date:     2018/4/3 19:56
 * Description:  
 */
 public   class Func {

     public  static String trimEnd(String text, String s){
         if (text==null|| text.isEmpty()) return  text;

         if (text.substring(text.length()-s.length(),text.length()).equals(s))
           return text.substring(0,text.length()-s.length());
         else return text;

     }
    public  static boolean ArrayIsExist(String[] ary, String value){
        for(int i=0;i<ary.length;i++) {
            if (ary[i].toString().equalsIgnoreCase(value))
                return  true;
        }
        return  false;
    }
}
