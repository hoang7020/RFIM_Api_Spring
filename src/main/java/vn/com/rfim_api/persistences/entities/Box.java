package vn.com.rfim_api.persistences.entities;

import javax.persistence.*;

@Entity
@Table(name = "Box")
public class Box {

    @Id
    private String boxId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "packageId", nullable = true)
    private Package aPackage;

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
