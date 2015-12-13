package chsj.chanyouji.fragments.plan.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class PlanEntity {
    String category;
    List<Destinatons> destinatonses;

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            category = jsonObject.getString("category");
            destinatonses = new LinkedList<Destinatons>();
            JSONArray array = jsonObject.getJSONArray("destinations");
            Destinatons destion;
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                destion = new Destinatons();
                destion.parseJSON(json);
                destinatonses.add(destion);

            }
        }

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Destinatons> getDestinatonses() {
        return destinatonses;
    }

    public void setDestinatonses(List<Destinatons> destinatonses) {
        this.destinatonses = destinatonses;
    }
}
