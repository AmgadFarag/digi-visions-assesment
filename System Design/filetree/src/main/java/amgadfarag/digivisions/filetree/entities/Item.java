package amgadfarag.digivisions.filetree.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="item")
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="type", nullable=false, unique=false)
    private String type;
    
    @Column(name="name", nullable=false, unique=false)
    private String name;
    
    @ManyToOne
    // @Column(name="permission_group_id", nullable=false, unique=false)
    private PermissionGroup permissionGroup;
}