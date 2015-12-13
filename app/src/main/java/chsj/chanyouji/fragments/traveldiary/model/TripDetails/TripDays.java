package chsj.chanyouji.fragments.traveldiary.model.TripDetails;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/11
 */
public class TripDays {

    private String trip_date;
    private List<Nodes> nodes;
    private int day;

    public void parseJSON(JSONObject jsonObject) throws JSONException {


        if (jsonObject != null) {


            Log.d("tripdays", jsonObject.toString());

            trip_date = jsonObject.getString("trip_date");

            day = jsonObject.getInt("day");
            //解析Nodes
            nodes = new ArrayList<Nodes>();
            JSONArray nodesArray = jsonObject.getJSONArray("nodes");
            int len = nodesArray.length();
            for (int i = 0; i < len; i++) {
                Nodes n = new Nodes(day);
                n.parseJSON(nodesArray.getJSONObject(i));
                nodes.add(n);
            }
        }

    }


    public String getTrip_date() {
        return trip_date;
    }

    public void setTrip_date(String trip_date) {
        this.trip_date = trip_date;
    }

    public List<Nodes> getNodes() {
        return nodes;
    }

    public void setNodes(List<Nodes> nodes) {
        this.nodes = nodes;
    }


}
