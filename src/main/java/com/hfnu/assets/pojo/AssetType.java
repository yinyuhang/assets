package com.hfnu.assets.pojo;

import com.shark.generator.ChineseName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AssetType {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    String id;
    @ChineseName("名称")
    String name;
    @ChineseName("创建时间")
    Date createDate;
    @ManyToOne
    @ChineseName("创建人")
    User createUser;
    @ManyToOne
    @ChineseName("修改人")
    User modifyUser;
    @OneToMany
    List<Asset> assets;
}
