package com.glimps.glimpsserver.perfume.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "perfume_photos")
@Entity
public class PerfumePhoto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfume_id")
	private Perfume perfume;

	private String url;

	@Column(columnDefinition = "TINYINT")
	private Integer orderNum;

	@Builder
	public PerfumePhoto(Perfume perfume, String url, Integer orderNum) {
		this.perfume = perfume;
		this.url = url;
		this.orderNum = orderNum;
	}

	public void mapTo(Perfume perfume) {
		this.perfume = perfume;
	}
}
