package com.hfnu.assets.pojo;

import com.shark.generator.ChineseName;
import com.shark.generator.Condition;
import com.shark.generator.ExpressionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


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
    @ChineseName("姓名")
    @Column(unique = true)
    String name;
    String pwd;
    @ChineseName("创建时间")
    Date createDate;
    @Condition(expression = ExpressionType.NOT_EMPTY)
    @ChineseName("部门")
    String department;
    @ChineseName("角色")
    String role;
    boolean beDeleted;
}
