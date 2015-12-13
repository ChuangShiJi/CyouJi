package chsj.chanyouji.fragments.tools.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName : chsj.chanyouji.fragments.tools.entity
 * Created by : zhaoQiang
 * Email : zhaoq_hero163.com
 * On 2015/11/11 // 12:23
 */
public class City implements Serializable{

    /**
     * id : 62
     * name_zh_cn : 法国
     * name_en : France
     * image_url : http://m.chanyouji.cn/destinations/62-portrait.jpg
     * children : [{"id":62,"name_zh_cn":"法国","name_en":"France","locked":false,"destination_trips_count":1118,"image_url":"http://m.chanyouji.cn/destinations/62-landscape.jpg","items_count":0},{"id":188,"name_zh_cn":"巴黎","name_en":"Paris","locked":false,"destination_trips_count":842,"image_url":"http://m.chanyouji.cn/destinations/188-landscape.jpg","items_count":0},{"id":189,"name_zh_cn":"尼斯与蔚蓝海岸","name_en":"Nice","locked":false,"destination_trips_count":207,"image_url":"http://m.chanyouji.cn/destinations/189-landscape.jpg","items_count":0},{"id":290,"name_zh_cn":"普罗旺斯","name_en":"Provence","locked":false,"destination_trips_count":190,"image_url":"http://m.chanyouji.cn/destinations/290-landscape.jpg","items_count":0}]
     */
    private int id;
    private String name_zh_cn;
    private String name_en;
    private String image_url;

    /**
     * id : 62
     * name_zh_cn : 法国
     * name_en : France
     * locked : false
     * destination_trips_count : 1118
     * image_url : http://m.chanyouji.cn/destinations/62-landscape.jpg
     * items_count : 0
     */

    private List<ChildrenEntity> children;

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

    public void setChildren(List<ChildrenEntity> children) {
        this.children = children;
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

    public List<ChildrenEntity> getChildren() {
        return children;
    }


    public static class ChildrenEntity implements Serializable{
        private int id;
        private String name_zh_cn;
        private String name_en;
        private boolean locked;
        private int destination_trips_count;
        private String image_url;
        private int items_count;

        public void setId(int id) {
            this.id = id;
        }

        public void setName_zh_cn(String name_zh_cn) {
            this.name_zh_cn = name_zh_cn;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public void setDestination_trips_count(int destination_trips_count) {
            this.destination_trips_count = destination_trips_count;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public void setItems_count(int items_count) {
            this.items_count = items_count;
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

        public boolean isLocked() {
            return locked;
        }

        public int getDestination_trips_count() {
            return destination_trips_count;
        }

        public String getImage_url() {
            return image_url;
        }

        public int getItems_count() {
            return items_count;
        }

        @Override
        public String toString() {
            return "ChildrenEntity{" +
                    "id=" + id +
                    ", name_zh_cn='" + name_zh_cn + '\'' +
                    ", name_en='" + name_en + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name_zh_cn='" + name_zh_cn + '\'' +
                ", name_en='" + name_en + '\'' +
                ", children=" + children +
                '}';
    }
}
