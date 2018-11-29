package az.com.newazhong.utilsclass.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证
 */

public class RegularUtils {
    //验证车牌号
    public static boolean isCarNumber(String mobiles){
        Pattern p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]{1}[A-Z0-9]{5}[A-Z0-9挂学警港澳]{1}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
