package amgadfarag.digivisions.filetree.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="file_binary")
@Data
@NoArgsConstructor
public class FileBinary {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name="binary", nullable=false)
    private byte[] binary;
    
    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item item;
    
}
