package chsj.chanyouji.fragments.plan.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/11
 */
public class TripNoteEntity {
    private int id;
    private String memo;
    private List<TripNotePlanDaysEntity> tripNotePlanDaysEntityList;

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        memo = jsonObject.getString("memo");
        JSONArray array = jsonObject.getJSONArray("plan_nodes");
        tripNotePlanDaysEntityList = new LinkedList<TripNotePlanDaysEntity>();
        TripNotePlanDaysEntity tripNotePlanDaysEntity;
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject1 = array.getJSONObject(i);
            tripNotePlanDaysEntity = new TripNotePlanDaysEntity();
            tripNotePlanDaysEntity.parseJSON(jsonObject1);
            tripNotePlanDaysEntityList.add(tripNotePlanDaysEntity);

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<TripNotePlanDaysEntity> getTripNotePlanDaysEntityList() {
        return tripNotePlanDaysEntityList;
    }

    public void setTripNotePlanDaysEntityList(List<TripNotePlanDaysEntity> tripNotePlanDaysEntityList) {
        this.tripNotePlanDaysEntityList = tripNotePlanDaysEntityList;
    }
}
