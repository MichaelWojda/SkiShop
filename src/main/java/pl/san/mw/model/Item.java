package pl.san.mw.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
//autor Michał Wojda indeks:23512
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @Min(1)
    private double price;
    @NotNull
    private boolean isRent;
    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser personWhoHasIt;

    public Item() {
    }
    //Gettery, Settery,toString....
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRent() {
        return isRent;
    }

    public void setRent(boolean rent) {
        isRent = rent;
    }

    public AppUser getPersonWhoHasIt() {
        return personWhoHasIt;
    }

    public void setPersonWhoHasIt(AppUser personWhoHasIt) {
        this.personWhoHasIt = personWhoHasIt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isRent=" + isRent +
                //", personWhoHasIt=" + personWhoHasIt +
                '}';
    }
}
