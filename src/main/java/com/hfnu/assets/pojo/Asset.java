package com.hfnu.assets.pojo;

import com.shark.generator.ChineseName;
import com.shark.generator.Condition;
import com.shark.generator.ExpressionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Asset {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @ChineseName("资产编号")
    String id;
    @Condition(logic = "like", expression = ExpressionType.NOT_EMPTY)
    @ChineseName("名称")
    String name;
    @Condition
    @ChineseName("价格")
    Double price;
    @Condition(expression = ExpressionType.NOT_EMPTY)
    @ChineseName("状态")
    String status;
    @Condition(logic = "greaterThanOrEqualTo")
    @ChineseName("采购日期")
    Date buyDate;
    @Condition(logic = "greaterThanOrEqualTo")
    @ChineseName("录入日期")
    Date createDate;
    @ManyToOne
    @ChineseName("录入人")
    User createUser;
    @ManyToOne
    @ChineseName("修改人")
    User modifyUser;
    @ManyToOne
    @Condition
    @ChineseName("类型")
    AssetType type;
}
