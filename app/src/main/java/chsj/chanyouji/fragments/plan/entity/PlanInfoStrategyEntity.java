package chsj.chanyouji.fragments.plan.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class PlanInfoStrategyEntity {


    /**
     * id : 55
     * name_zh_cn : 日本
     * name_en : Japan
     * poi_count : 1006
     * plans_count : 6
     * articles_count : 6
     * contents_count : 2463
     * destination_trips_count : 3364
     * locked : false
     * wiki_app_publish : true
     * updated_at : 1435961025
     * image_url : http://m.chanyouji.cn/destinations/55-landscape.jpg
     * guides_count : 7
     */

    private int id;
    private String name_zh_cn;
    private String name_en;
    private String image_url;

    public void parseJSON(JSONObject jsonObject) {
        try {
            id = jsonObject.getInt("id");
            name_zh_cn = jsonObject.getString("name_zh_cn");
            name_en = jsonObject.getString("name_en");
            image_url = jsonObject.getString("image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_zh_cn(String name_zh_cn) {
        this.name_zh_cn = name_zh_cn;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public String getImage_url() {
        return image_url;
    }
}
