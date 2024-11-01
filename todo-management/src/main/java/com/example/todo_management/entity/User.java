package com.example.todo_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private  String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //cascadeType.All -> any users creaated under the user table will be reflected in the userRole table as wel
    @JoinTable(name = "user_Roles",
    joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_Id",referencedColumnName = "roleId")
    )
    private Set<Role> roles;

}
