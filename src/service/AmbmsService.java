package service;

import java.rmi.RemoteException;
import java.util.ArrayList;

import cn.edu.gdngs.ambms.CardChargeProxy;

public class AmbmsService {
	
	public String[] EndTime(String id) throws Exception{
		ArrayList items = new ArrayList();
		CardChargeProxy proxy = new CardChargeProxy();
		String stuinfos = proxy.getUserInfo(id);
		String[] stuinfo = stuinfos.split("#");
		for(String item : stuinfo) {
			if(!item.equals("")) {
				items.add(item);
			}
		}
		String states = (String) items.get(10);
		String state = states.replace("套餐","");
		String end_time = (String)items.get(13);
		String[] info = {state,end_time};
		return info;
	}
}
        	