package com.scm.smartcontactmanager.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;

@Entity(name = "user")
@Table(name = "users")

// user implements user detail for security congif
public class User implements UserDetails{
    @Id
    private String userId;
    
    @Column(name = "user_name",nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    
    @Column(length = 1000)
    private String about;

    @Column(length = 1000)
    private String profilePic;

    private String phoneNo;

    @Getter(value= AccessLevel.NONE)    // to disable default getter so that we can implements method of that interface
    private boolean enabled = true;

    private boolean phoneVerified = false;

    private boolean emailVerified = false;

    @Enumerated(value = EnumType.STRING)
    // self, google, github, linkdin, facebook 
    private Providers provider=Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)          // bcz one user can save many contacts
    // cascade means if a user is delete then its all contact will be deleted
    private List<Contact> contacts = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER) // to store as a collection element
    private List<String> roleList = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of roles[admin,user] convert it in
        // collection of SimpleGrantedAuthority[role[admin,user]]
        Collection <SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public String getPassword(){
        return this.password;
    }
//------------------------------------------------------------------------
//------------------------------------------------------------------------
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public String getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean isPhoneVerified() {
        return phoneVerified;
    }
    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }
    public boolean isEmailVerified() {
        return emailVerified;
    }
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    public Providers getProvider() {
        return provider;
    }
    public void setProvider(Providers provider) {
        this.provider = provider;
    }
    public String getProviderUserId() {
        return providerUserId;
    }
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
    public User(String userId, String name, String email, String password, String about, String profilePic,
            String phoneNo, boolean enabled, boolean phoneVerified, boolean emailVerified, Providers provider,
            String providerUserId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.profilePic = profilePic;
        this.phoneNo = phoneNo;
        this.enabled = enabled;
        this.phoneVerified = phoneVerified;
        this.emailVerified = emailVerified;
        this.provider = provider;
        this.providerUserId = providerUserId;
    }

    public User(String userId, String name, String email, String password, String about, String profilePic,
            String phoneNo, boolean enabled, boolean phoneVerified, boolean emailVerified, Providers provider,
            String providerUserId, List<Contact> contacts) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.profilePic = profilePic;
        this.phoneNo = phoneNo;
        this.enabled = enabled;
        this.phoneVerified = phoneVerified;
        this.emailVerified = emailVerified;
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.contacts = contacts;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
                + about + ", profilePic=" + profilePic + ", phoneNo=" + phoneNo + ", enabled=" + enabled
                + ", phoneVerified=" + phoneVerified + ", emailVerified=" + emailVerified + ", provider=" + provider
                + ", providerUserId=" + providerUserId + "]";
    }
    public User() {
    }

    public void setRoleList(List<String> roleList) {
    this.roleList = roleList;
}

    public List<String> getRoleList() {
        return roleList;
    }


}
