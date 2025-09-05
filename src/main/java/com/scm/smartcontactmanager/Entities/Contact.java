package com.scm.smartcontactmanager.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {
    @Id
    private String id;
    private String name;
    private String email;
    private String phonenumber;
    private String address;
    private String picture;

    @Column(length = 1000)
    private String description;
    private boolean favorite=false;
    private String websiteLink;
    private String linkdinLink;

    @ManyToOne     // bcz any contact have only one user who set that contact
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLinks> links = new ArrayList<>();
}
