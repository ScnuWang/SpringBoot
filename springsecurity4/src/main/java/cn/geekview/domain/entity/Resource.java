package cn.geekview.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "t_resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String resource;

    @ManyToMany(mappedBy = "resources" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

}
