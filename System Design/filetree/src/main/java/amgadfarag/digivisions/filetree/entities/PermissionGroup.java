package amgadfarag.digivisions.filetree.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="permission_group", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "group_name" })
})
@Data
@NoArgsConstructor
public class PermissionGroup {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="group_name", nullable=false, unique=true)
    private String groupName;
    
}
