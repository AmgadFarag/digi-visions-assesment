package amgadfarag.digivisions.filetree.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="item", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "type", "name", "parent" })
})
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="type", nullable=false)
    private String type;
    
    @Column(name="name", nullable=false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="permission_group_id", nullable=false)
    private PermissionGroup permissionGroup;

    @Column(name="parent", nullable=true)
    private String parent;
    
}
