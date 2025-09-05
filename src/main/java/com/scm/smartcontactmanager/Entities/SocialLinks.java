package com.scm.smartcontactmanager.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SocialLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String link;
    private String title;

    @ManyToOne
    private Contact contact;


//---------------------------------------------------------------------------------
//-----------------------------------------------------------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public SocialLinks(long id, String link, String title, Contact contact) {
        this.id = id;
        this.link = link;
        this.title = title;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "SocialLinks [id=" + id + ", link=" + link + ", title=" + title + ", contact=" + contact + "]";
    }

  
}
