package finalproject.startech.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subTitle;
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;
    private String photoUrl;
    private Date createdDate;
    private Date updatedDate;
    private Boolean isDeleted = false;
    private String seoUrl;
    private String author;


    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(name = "blog_tags", joinColumns = @JoinColumn(name = "blogs", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tags",referencedColumnName = "id"))
    private Set<Tag> tag = new HashSet<>();




}
