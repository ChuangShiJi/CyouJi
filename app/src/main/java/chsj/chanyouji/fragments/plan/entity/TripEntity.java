package chsj.chanyouji.fragments.plan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class TripEntity implements Parcelable{

    /**
     * id : 1693
     * name : 日本全景7日游
     * budget : 0
     * start_date : null
     * description : 京都的优雅，东京大阪的时尚，富士山的秀美，北海道的浪漫一次体验，7天最全面的日本游线路。
     * plan_days_count : 7
     * plan_nodes_count : 38
     * user_name :
     * image_url : http://m.chanyouji.cn/plans/1693.jpg
     * updated_at : 1410504676
     */

    private int id;
    private String name;
    private int budget;
    private String description;
    private int plan_days_count;
    private int plan_nodes_count;
    //    private String user_name;
    private String image_url;
    private long updated_at;
    public TripEntity(){}

    protected TripEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        budget = in.readInt();
        description = in.readString();
        plan_days_count = in.readInt();
        plan_nodes_count = in.readInt();
        image_url = in.readString();
        updated_at = in.readLong();
    }

    public static final Creator<TripEntity> CREATOR = new Creator<TripEntity>() {
        @Override
        public TripEntity createFromParcel(Parcel in) {

            return new TripEntity(in);
        }

        @Override
        public TripEntity[] newArray(int size) {
            return new TripEntity[size];
        }
    };

    public void parseJSON(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        name = jsonObject.getString("name");
        budget = jsonObject.getInt("budget");
        description = jsonObject.getString("description");
        plan_days_count = jsonObject.getInt("plan_days_count");
        plan_nodes_count = jsonObject.getInt("plan_nodes_count");
        image_url = jsonObject.getString("image_url");
        updated_at = jsonObject.getLong("updated_at");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlan_days_count(int plan_days_count) {
        this.plan_days_count = plan_days_count;
    }

    public void setPlan_nodes_count(int plan_nodes_count) {
        this.plan_nodes_count = plan_nodes_count;
    }

//    public void setUser_name(String user_name) {
//        this.user_name = user_name;
//    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
        return budget;
    }


    public String getDescription() {
        return description;
    }

    public int getPlan_days_count() {
        return plan_days_count;
    }

    public int getPlan_nodes_count() {
        return plan_nodes_count;
    }

//    public String getUser_name() {
//        return user_name;
//    }

    public String getImage_url() {
        return image_url;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(budget);
        dest.writeString(description);
        dest.writeInt(plan_days_count);
        dest.writeInt(plan_nodes_count);
        dest.writeString(image_url);
        dest.writeLong(updated_at);
    }
}
