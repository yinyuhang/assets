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


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    String id;
    @Condition(expression = ExpressionType.NOT_EMPTY, logic = "like")
    String name;
    String pwd;
    String createDate;
    @Condition(expression = ExpressionType.NOT_EMPTY)
    String department;
    String role;
}
