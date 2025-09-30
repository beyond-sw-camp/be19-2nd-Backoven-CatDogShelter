package com.backoven.catdogshelter.domain.volunteer.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

// NOTE: Empty child entity skeleton; add fields, JPA annotations, and behavior.
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "volunteerassociationapplicationdetails")
public class ApplicationDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "time", nullable = false)
    private Integer time;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "volunteer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_vaad_volunteerAssociation"))
    private AssociationEntity association;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_vaad_user"))
    private UserEntity user;

}
