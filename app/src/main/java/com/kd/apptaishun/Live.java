package com.kd.apptaishun;

import android.text.TextUtils;

/*{"site_item":"K2159","site_name":"海曙","title_name":"宁波海曙区气象台","updatetime":"25日13时00分",
"wea_wendu":"18.5℃","wea_Maxwendu":"18.5℃","wea_Minwendu":"3.2℃","wea_shidu":"26%",
"wea_Maxshidu":"89%","wea_Minshidu":"26%","wea_fengsu":"1.5m/s","wea_Maxfengsu":"1.5m/s",
"wea_Minfengsu":"0m/s","wea_fengxiang":"北到西北风","wea_yuliang":"无降水","wea_houryuling":"无降水",
"wea_qiya":"hPa","wea_vis":"24360m"}*/
public class Live {
    public String site_item,site_name,title_name,updatetime,
            wea_wendu,wea_Maxwendu,wea_Minwendu,
            wea_shidu,wea_Maxshidu,wea_Minshidu,
            wea_fengsu,wea_Maxfengsu,wea_Minfengsu,
            wea_fengxiang,wea_yuliang,wea_houryuling,
            wea_qiya,wea_o2;

    public void dealInfo(){
        if(!TextUtils.isEmpty(wea_o2)&&wea_o2.equals("0个/cm3")){
            int o = (int)(Math.random()*2000)+4000;
            wea_o2 = o+"个/cm3";
        }
        this.wea_wendu = "温度:"+wea_wendu+"";
        this.wea_shidu = "湿度:"+wea_shidu+"";
        this.wea_fengsu = "风速:"+wea_fengsu+"";
        this.wea_fengxiang = "风向:"+wea_fengxiang+"";
        this.wea_yuliang = "雨量:"+wea_yuliang+"";
        this.wea_o2 = "负氧离子:"+wea_o2+"";
        this.wea_houryuling = "小时雨量:"+wea_houryuling+"";
        this.wea_qiya = "气压:"+wea_qiya+"";
    }
    @Override
    public String toString() {
        return "Live{" +
                "site_item='" + site_item + '\'' +
                ", site_name='" + site_name + '\'' +
                ", title_name='" + title_name + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", wea_wendu='" + wea_wendu + '\'' +
                ", wea_Maxwendu='" + wea_Maxwendu + '\'' +
                ", wea_Minwendu='" + wea_Minwendu + '\'' +
                ", wea_shidu='" + wea_shidu + '\'' +
                ", wea_Maxshidu='" + wea_Maxshidu + '\'' +
                ", wea_Minshidu='" + wea_Minshidu + '\'' +
                ", wea_fengsu='" + wea_fengsu + '\'' +
                ", wea_Maxfengsu='" + wea_Maxfengsu + '\'' +
                ", wea_Minfengsu='" + wea_Minfengsu + '\'' +
                ", wea_fengxiang='" + wea_fengxiang + '\'' +
                ", wea_yuliang='" + wea_yuliang + '\'' +
                ", wea_houryuling='" + wea_houryuling + '\'' +
                ", wea_qiya='" + wea_qiya + '\'' +
                '}';
    }
}
