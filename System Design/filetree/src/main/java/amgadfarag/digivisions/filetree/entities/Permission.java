package amgadfarag.digivisions.filetree.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="permission")
@Data
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="user_email", nullable=false, unique=true)
    private String userEmail;

    @Column(name="permission_level", nullable=false, unique=true)
    private String permissionLevel;

    @ManyToOne
    @JoinColumn(name="group_id", nullable=false, unique=false)
    private PermissionGroup permissionGroup;

}
