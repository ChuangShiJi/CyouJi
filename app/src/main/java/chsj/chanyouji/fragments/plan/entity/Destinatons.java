package chsj.chanyouji.fragments.plan.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class Destinatons {

    /**
     * id : 55
     * name_zh_cn : 日本
     * name_en : Japan
     * poi_count : 1006
     * lat : 36.2048
     * lng : 138.253
     * image_url : http://m.chanyouji.cn/destinations/55-portrait.jpg
     * updated_at : 1435961025
     */

    private int id;
    private String name_zh_cn;
    private String name_en;
    private int poi_count;
    private double lat;
    private double lng;
    private String image_url;
    private long updated_at;

    public void setId(int id) {
        this.id = id;
    }

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            id=jsonObject.getInt("id");
            name_zh_cn=jsonObject.getString("name_zh_cn");
            name_en=jsonObject.getString("name_en");
            poi_count=jsonObject.getInt("poi_count");
            lat=jsonObject.getDouble("lat");
            lng=jsonObject.getDouble("lng");
            image_url=jsonObject.getString("image_url");
            updated_at=jsonObject.getLong("updated_at");
        }


    }

    public void setName_zh_cn(String name_zh_cn) {
        this.name_zh_cn = name_zh_cn;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setPoi_count(int poi_count) {
        this.poi_count = poi_count;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public String getName_zh_cn() {
        return name_zh_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public int getPoi_count() {
        return poi_count;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getImage_url() {
        return image_url;
    }

    public long getUpdated_at() {
        return updated_at;
    }
}
