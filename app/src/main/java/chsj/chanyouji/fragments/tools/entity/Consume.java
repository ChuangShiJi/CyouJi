package chsj.chanyouji.fragments.tools.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * ProjectName : chsj.chanyouji.fragments.tools.entity
 * Created by : zhaoQiang
 * Email : zhaoq_hero163.com
 * On 2015/11/12 // 00:03
 */
@Table(name="consume")
public class Consume {

    @Column(name = "id",isId = true)
    private int id;

    @Column(name = "money")
    private Float money;   //金额

    @Column(name = "pattern")
    private String pattern; //消费方式

    @Column(name = "moUnit")
    private String moneyUnit;  //金钱的  单位

    @Column(name = "moCondition")
    private String moneyCondition; //消费的 详细情况

    @Column(name ="date")
    private String date;  //保存日期

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getMoneyCondition() {
        return moneyCondition;
    }

    public void setMoneyCondition(String moneyCondition) {
        this.moneyCondition = moneyCondition;
    }

    public String getMoneyUnit() {
        return moneyUnit;
    }

    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Consume{" +
                "id=" + id +
                ", money=" + money +
                ", pattern='" + pattern + '\'' +
                ", moneyUnit='" + moneyUnit + '\'' +
                ", moneyCondition='" + moneyCondition + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
