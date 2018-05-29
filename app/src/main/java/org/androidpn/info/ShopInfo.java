package org.androidpn.info;

import org.androidpn.demoapp.R;

import java.io.Serializable;

/**
 * ���̵�ʵ����
 * */
public class ShopInfo implements Serializable {

	private String sid;	//id
	private String sname;	//名字
	private String stype;
	private String saddress;	//地址
	private String snear;
	private String stel;	//电话
	private String stime;
	private String szhekou;	//折扣
	private String smembercard;	//
	private String sper;
	private String smoney;
	private String snum;
	private String slevel;
	private String sflag_tuan;
	private String sflag_quan;
	private String sflag_ding;
	private String sflag_ka;
	private double longitude;	//经度
	private double latitude;	//纬度
	private String sintroduction;
	private String sdetails;
	private String stips;
	private String sflag_promise;
	private String iname;

	private String sholder;
	private String startTime;
	private String endTime;
	private String feature;



	public ShopInfo() {
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public String getSnear() {
		return snear;
	}
	public void setSnear(String snear) {
		this.snear = snear;
	}
	public String getStel() {
		return stel;
	}
	public void setStel(String stel) {
		this.stel = stel;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getSzhekou() {
		return szhekou;
	}
	public void setSzhekou(String szhekou) {
		this.szhekou = szhekou;
	}
	public String getSmembercard() {
		return smembercard;
	}
	public void setSmembercard(String smembercard) {
		this.smembercard = smembercard;
	}
	public String getSper() {
		return sper;
	}
	public void setSper(String sper) {
		this.sper = sper;
	}
	public String getSmoney() {
		return smoney;
	}
	public void setSmoney(String smoney) {
		this.smoney = smoney;
	}
	public String getSnum() {
		return snum;
	}
	public void setSnum(String snum) {
		this.snum = snum;
	}
	public String getSlevel() {
		return slevel;
	}
	public void setSlevel(String slevel) {
		this.slevel = slevel;
	}
	public String getSflag_tuan() {
		return sflag_tuan;
	}
	public void setSflag_tuan(String sflag_tuan) {
		this.sflag_tuan = sflag_tuan;
	}
	public String getSflag_quan() {
		return sflag_quan;
	}
	public void setSflag_quan(String sflag_quan) {
		this.sflag_quan = sflag_quan;
	}
	public String getSflag_ding() {
		return sflag_ding;
	}
	public void setSflag_ding(String sflag_ding) {
		this.sflag_ding = sflag_ding;
	}
	public String getSflag_ka() {
		return sflag_ka;
	}
	public void setSflag_ka(String sflag_ka) {
		this.sflag_ka = sflag_ka;
	}
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getSintroduction() {
		return sintroduction;
	}
	public void setSintroduction(String sintroduction) {
		this.sintroduction = sintroduction;
	}
	public String getSdetails() {
		return sdetails;
	}
	public void setSdetails(String sdetails) {
		this.sdetails = sdetails;
	}
	public String getStips() {
		return stips;
	}
	public void setStips(String stips) {
		this.stips = stips;
	}
	public String getSflag_promise() {
		return sflag_promise;
	}
	public void setSflag_promise(String sflag_promise) {
		this.sflag_promise = sflag_promise;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getSholder() {
		return sholder;
	}

	public void setSholder(String sholder) {
		this.sholder = sholder;
	}

	public String getStarSrc() {
		return "@drawable/star"+this.getSlevel();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public int getStarImageResource() {
		if ("0".equals(this.getSlevel())) {
			return R.drawable.star0;
		} else if ("1".equals(this.getSlevel())) {
			return R.drawable.star1;
		} else if ("2".equals(this.getSlevel())) {
			return R.drawable.star2;
		} else if ("3".equals(this.getSlevel())) {
			return R.drawable.star3;
		} else if ("4".equals(this.getSlevel())) {
			return R.drawable.star4;
		} else if ("5".equals(this.getSlevel())) {
			return R.drawable.star5;
		}
		return R.drawable.star0;
	}
}
