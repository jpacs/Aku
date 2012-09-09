package com.xsloth.aku.db.dao;

// Generated 8/Set/2012 1:13:06 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.xsloth.aku.db.hibernate.League;
import com.xsloth.aku.db.hibernate.LeagueRank;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class LeagueRank.
 * @see com.xsloth.aku.db.dao.LeagueRank
 * @author Hibernate Tools
 */
public class LeagueRankHome extends AbstractDao {

	private static final Log log = LogFactory.getLog(LeagueRankHome.class);

	protected static LeagueRankHome myInstance = null;

	@Override
	public Class getReferenceClass() {
		// TODO Auto-generated method stub
		return LeagueRankHome.class;
	}
	
	public static LeagueRankHome getInstance()
	{
		if (myInstance == null)
			myInstance = new LeagueRankHome();
		return myInstance;
	}
	
	/**
	 * CRUD Methods
	 */

	public void persist(LeagueRank transientInstance) {
		log.debug("persisting FightMode instance");
		try {
			this.getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(LeagueRank instance) {
		log.debug("attaching dirty FightMode instance");
		try {
			this.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LeagueRank instance) {
		log.debug("attaching clean FightMode instance");
		try {
			this.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(LeagueRank persistentInstance) {
		log.debug("deleting FightMode instance");
		try {
			this.getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LeagueRank merge(LeagueRank detachedInstance) {
		log.debug("merging FightMode instance");
		try {
			LeagueRank result = (LeagueRank) this.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LeagueRank findById(java.lang.Long id) {
		log.debug("getting FightMode instance with id: " + id);
		try {
			LeagueRank instance = (LeagueRank) this.getSession().get("com.xsloth.aku.db.hibernate.LeagueRank", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<LeagueRank> findByExample(LeagueRank instance) {
		log.debug("finding FightMode instance by example");
		try {
			List<LeagueRank> results = (List<LeagueRank>) this.getSession().createCriteria("com.xsloth.aku.db.hibernate.LeagueRank").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * Methods
	 */
}
