package com.xsloth.aku.db.hibernate;

// Generated 7/Set/2012 4:50:24 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FightMode generated by hbm2java
 */
@Entity
@Table(name = "fight_mode", catalog = "aku")
public class FightMode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String mode;
	private boolean online;
	private Set<Fight> fights = new HashSet<Fight>(0);
	private Set<Rank> ranks = new HashSet<Rank>(0);

	public FightMode() {
	}

	public FightMode(String mode, boolean online) {
		this.mode = mode;
		this.online = online;
	}

	public FightMode(String mode, boolean online, Set<Fight> fights,
			Set<Rank> ranks) {
		this.mode = mode;
		this.online = online;
		this.fights = fights;
		this.ranks = ranks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "mode", nullable = false, length = 250)
	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Column(name = "online", nullable = false)
	public boolean isOnline() {
		return this.online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fightMode")
	public Set<Fight> getFights() {
		return this.fights;
	}

	public void setFights(Set<Fight> fights) {
		this.fights = fights;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fightMode")
	public Set<Rank> getRanks() {
		return this.ranks;
	}

	public void setRanks(Set<Rank> ranks) {
		this.ranks = ranks;
	}

}
