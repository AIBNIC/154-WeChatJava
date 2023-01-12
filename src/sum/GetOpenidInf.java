package sum;

import com.alibaba.fastjson.JSONObject;

import service.Getopenid_stuInfoService;

public class GetOpenidInf {
	public JSONObject getopenid(String code) throws Exception {
		Getopenid_stuInfoService service = new Getopenid_stuInfoService();
		JSONObject StuInfo =  service.getStuinfo(code);
//		System.out.println("getopenid执行完成,"+StuInfo);
		if(StuInfo == null) {
			return null;
		}
		return StuInfo;
	}
}
