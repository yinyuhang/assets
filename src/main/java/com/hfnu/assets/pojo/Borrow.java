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
    String status;
    Date createDate;
    @ManyToOne
    Asset asset;
    @ManyToOne
    User createUser;
}
