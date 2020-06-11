package pl.san.mw.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//autor Michał Wojda indeks:23512
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@UniqueName
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="user_and_roles", joinColumns = {@JoinColumn(name="id_user",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="id_role",referencedColumnName = "id")})
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "personWhoHasIt",fetch = FetchType.EAGER)
    private List<Item> rentedItems = new ArrayList<>();

    public AppUser() {
    }
    //Gettery, Settery,toString....
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Item> getRentedItems() {
        return rentedItems;
    }

    public void setRentedItems(List<Item> rentedItems) {
        this.rentedItems = rentedItems;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
                //", rentedItems=" + rentedItems +
                '}';
    }
}
