package com.backoven.catdogshelter.domain.sighting.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sightingPostFiles")
public class SightingPostFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "file_rename")
    private String fileRename;

    @Column(name = "file_path")
    private String filePath  = "Sighting";

    @Column(name = "uploaded_at")
    private String uploadedAt;

    @Column(name = "post_id")
    private int postId;



}
