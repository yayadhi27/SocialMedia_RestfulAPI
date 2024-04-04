package com.example.SocialMedia.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", unique = true, nullable = false, length = 25)
    @NotNull
    private String username;

    @Column(name= "email", unique = true, nullable = false, length = 25)
    @NotNull
    private String email;

    @Column(name= "age", unique = true, nullable = false, length = 25)
    @NotNull
    private Integer age;

    @Column(name= "address", unique = true, nullable = false, length = 25)
    @NotNull
    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

}
