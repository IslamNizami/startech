package finalproject.startech.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "blogs",nullable = true)
    private List<Blog> blogs;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Service> services;
}
