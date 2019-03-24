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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Borrow {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    String id;
    @Condition(expression = ExpressionType.NOT_EMPTY)
    @ChineseName("状态")
    String status;
    @ChineseName("创建时间")
    Date createDate;
    @ManyToOne
    @ChineseName("资产编号")
    Asset asset;
    @ManyToOne
    @ChineseName("创建人")
    User createUser;
    @ManyToOne
    @ChineseName("修改人")
    User modifyUser;
}
