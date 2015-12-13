package chsj.chanyouji.fragments.plan.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/11
 */
//游记日程实体类
public class TripNotePlanDaysEntity {


    /**
     * id : 183261
     * entry_id : 154578
     * position : 0
     * candidate : false
     * tips : #国内到东京# 从国内前往东京的航班都降落在成田国际机场，搭机场巴士从机场到东京市区单程约1.5小时，票价1000日元。
     * lat : 35.773297
     * lng : 140.387817
     * distance : 0
     * image_url : http://m.chanyouji.cn/attractions/154578.jpg
     * entry_name : 成田国际机场
     * entry_type : Attraction
     * attraction_type : transport
     * user_entry : false
     */

    private int id;
    private int entry_id;
    private String tips;
    private String lat;
    private String lng;
    private String image_url;
    private String entry_name;


    public void parseJSON(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        entry_id = jsonObject.getInt("entry_id");
        tips = jsonObject.getString("tips");
        lat = jsonObject.getString("lat");
        lng = jsonObject.getString("lng");
        image_url = jsonObject.getString("image_url");
        entry_name = jsonObject.getString("entry_name");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setEntry_name(String entry_name) {
        this.entry_name = entry_name;
    }

    public int getId() {
        return id;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public String getTips() {
        return tips;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getEntry_name() {
        return entry_name;
    }
}
