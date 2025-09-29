package com.backoven.catdogshelter.domain.volunteer.command.domain.aggregate.entity;

import com.backoven.catdogshelter.common.entity.ShelterHeadEntity;
import com.backoven.catdogshelter.common.entity.SigunguEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "volunteerassociation")
public class AssociationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", length = 20, nullable = false)
    private String content;

    @Column(name = "created_at", length = 20, nullable = false, updatable = false)
    private String createdAt;

    @Column(name = "time",  nullable = false )
    private Integer time;

    @Column(name = "start_date", nullable = false )
    private String startDate;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "deadline")
    private boolean deadLine;

    @Column(name = "is_end")
    private boolean isEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id", nullable = false, foreignKey = @ForeignKey(name = "fk_va_shelter_head"))
    private ShelterHeadEntity shelterHead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sigungu_id", nullable = false, foreignKey = @ForeignKey(name = "fk_va_sigungu"))
    private SigunguEntity sigungu;
}
