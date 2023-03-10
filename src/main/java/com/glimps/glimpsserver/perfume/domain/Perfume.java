package com.glimps.glimpsserver.perfume.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "perfume")
@Entity
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String perfumeName;

    private double overallRatings;
    private double longevityRatings;
    private double sillageRatings;

    private int reviewCnt;

    @Builder
    public Perfume(String brand, String perfumeName, double overallRatings, double longevityRatings,
                   double sillageRatings, int reviewCnt) {
        this.brand = brand;
        this.perfumeName = perfumeName;
        this.overallRatings = overallRatings;
        this.longevityRatings = longevityRatings;
        this.sillageRatings = sillageRatings;
        this.reviewCnt = reviewCnt;
    }
}
