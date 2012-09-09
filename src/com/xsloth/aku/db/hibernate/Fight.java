package com.xsloth.aku.db.hibernate;

// Generated 7/Set/2012 4:50:24 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Fight generated by hbm2java
 */
@Entity
@Table(name = "fight", catalog = "aku")
public class Fight implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private User userByIdUser;
	private FightMode fightMode;
	private User userByOpponent;
	private String result;
	private Date date;
	private boolean ranked;
	private String value1;
	private Date value2;

	public Fight() {
	}

	public Fight(User userByIdUser, FightMode fightMode, String result,
			Date date, boolean ranked) {
		this.userByIdUser = userByIdUser;
		this.fightMode = fightMode;
		this.result = result;
		this.date = date;
		this.ranked = ranked;
	}

	public Fight(User userByIdUser, FightMode fightMode, User userByOpponent,
			String result, Date date, boolean ranked, String value1, Date value2) {
		this.userByIdUser = userByIdUser;
		this.fightMode = fightMode;
		this.userByOpponent = userByOpponent;
		this.result = result;
		this.date = date;
		this.ranked = ranked;
		this.value1 = value1;
		this.value2 = value2;
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
	public User getUserByIdUser() {
		return this.userByIdUser;
	}

	public void setUserByIdUser(User userByIdUser) {
		this.userByIdUser = userByIdUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mode", nullable = false)
	public FightMode getFightMode() {
		return this.fightMode;
	}

	public void setFightMode(FightMode fightMode) {
		this.fightMode = fightMode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opponent")
	public User getUserByOpponent() {
		return this.userByOpponent;
	}

	public void setUserByOpponent(User userByOpponent) {
		this.userByOpponent = userByOpponent;
	}

	@Column(name = "result", nullable = false, length = 4)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "ranked", nullable = false)
	public boolean isRanked() {
		return this.ranked;
	}

	public void setRanked(boolean ranked) {
		this.ranked = ranked;
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

}
