package com.glimps.glimpsserver.perfume.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "brands")
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "brand_name_kor")
	private String brandNameKor;

	@Column(name = "brand_name_eng")
	private String brandNameEng;

	public static Brand createBrand(String brandName, String brandNameKor) {
		return Brand.builder()
			.brandNameEng(brandName)
			.brandNameKor(brandNameKor)
			.build();
	}
}
