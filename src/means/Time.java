package means;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记  
        Date date = new Date();// 获取当前时间
        return sdf.format(date);
	}
}
