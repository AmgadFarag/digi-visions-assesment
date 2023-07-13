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
@Table(name="permission", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "user_email", "permission_level" })
})
@Data
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="user_email", nullable=false)
    private String userEmail;

    @Column(name="permission_level", nullable=false)
    private String permissionLevel;

    @ManyToOne
    @JoinColumn(name="group_id", nullable=true)
    private PermissionGroup permissionGroup;

}
