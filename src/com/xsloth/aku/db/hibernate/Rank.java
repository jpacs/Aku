package com.xsloth.aku.db.hibernate;

// Generated 7/Set/2012 4:50:24 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Rank generated by hbm2java
 */
@Entity
@Table(name = "rank", catalog = "aku", uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_user", "id_mode" }))
public class Rank implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private User user;
	private FightMode fightMode;
	private long rank;
	private String value1;
	private Date value2;
	private Set<LeagueRank> leagueRanks = new HashSet<LeagueRank>(0);

	public Rank() {
	}

	public Rank(User user, FightMode fightMode, long rank) {
		this.user = user;
		this.fightMode = fightMode;
		this.rank = rank;
	}

	public Rank(User user, FightMode fightMode, long rank, String value1,
			Date value2, Set<LeagueRank> leagueRanks) {
		this.user = user;
		this.fightMode = fightMode;
		this.rank = rank;
		this.value1 = value1;
		this.value2 = value2;
		this.leagueRanks = leagueRanks;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mode", nullable = false)
	public FightMode getFightMode() {
		return this.fightMode;
	}

	public void setFightMode(FightMode fightMode) {
		this.fightMode = fightMode;
	}

	@Column(name = "rank", nullable = false)
	public long getRank() {
		return this.rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	@Column(name = "value1", length = 250)
	public String getValue1() {
		return this.value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "value2", length = 8)
	public Date getValue2() {
		return this.value2;
	}

	public void setValue2(Date value2) {
		this.value2 = value2;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rank")
	public Set<LeagueRank> getLeagueRanks() {
		return this.leagueRanks;
	}

	public void setLeagueRanks(Set<LeagueRank> leagueRanks) {
		this.leagueRanks = leagueRanks;
	}

}
