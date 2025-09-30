package com.backoven.catdogshelter.domain.adoption.command.domain.aggregate.entity.AdoptionPost;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "adoptionPostFiles")
public class AdoptionPostFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_rename", nullable = false, length = 100)
    private String fileRename;

    @Column(name = "file_path", nullable = false, length = 255)
    private String filePath;

    @Column(name = "uploaded_at", nullable = false, length = 20)
    private String uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, foreignKey = @ForeignKey(name = "fk_apf_post"))
    private AdoptionPostEntity adoptionPost;
}
