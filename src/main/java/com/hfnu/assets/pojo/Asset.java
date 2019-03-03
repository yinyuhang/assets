package com.hfnu.assets.pojo;

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
    String id;
    @Condition(logic = "like", expression = ExpressionType.NOT_EMPTY)
    String name;
    @Condition(expression = ExpressionType.NOT_EMPTY)
    String type;
    @Condition
    Double price;
    @Condition(logic = "greaterThanOrEqualTo", expression = ExpressionType.NOT_NULL)
    Date buyDate;
    @Condition(logic = "greaterThanOrEqualTo", expression = ExpressionType.NOT_NULL)
    Date createDate;
    @ManyToOne
    User createUser;
    @ManyToOne
    User modifyUser;
}
