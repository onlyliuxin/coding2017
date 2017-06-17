package yangsongbao;

/**
 * Created by songbao.yang on 2017/6/17.
 */
public enum NoticeConditionEnum {
    PROMOTION(1, "促销");

    private int id;
    private String desc;

    NoticeConditionEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
