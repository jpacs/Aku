package com.xsloth.aku.db.dao;

// Generated 8/Set/2012 1:13:06 by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;

import com.xsloth.aku.db.hibernate.FightMode;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FightMode.
 * @see com.xsloth.aku.db.dao.FightMode
 * @author Hibernate Tools
 */
public class FightModeHome extends AbstractDao {

	private static final Log log = LogFactory.getLog(FightModeHome.class);

	protected static FightModeHome myInstance = null;

	@Override
	public Class getReferenceClass() {
		// TODO Auto-generated method stub
		return FightModeHome.class;
	}
	
	public static FightModeHome getInstance()
	{
		if (myInstance == null)
			myInstance = new FightModeHome();
		return myInstance;
	}
	
	/**
	 * CRUD Methods
	 */

	public void persist(FightMode transientInstance) {
		log.debug("persisting FightMode instance");
		try {
			this.getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FightMode instance) {
		log.debug("attaching dirty FightMode instance");
		try {
			this.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FightMode instance) {
		log.debug("attaching clean FightMode instance");
		try {
			this.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FightMode persistentInstance) {
		log.debug("deleting FightMode instance");
		try {
			this.getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FightMode merge(FightMode detachedInstance) {
		log.debug("merging FightMode instance");
		try {
			FightMode result = (FightMode) this.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FightMode findById(java.lang.Long id) {
		log.debug("getting FightMode instance with id: " + id);
		try {
			FightMode instance = (FightMode) this.getSession().get("com.xsloth.aku.db.hibernate.FightMode", id);
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

	public List<FightMode> findByExample(FightMode instance) {
		log.debug("finding FightMode instance by example");
		try {
			List<FightMode> results = (List<FightMode>) this.getSession().createCriteria("com.xsloth.aku.db.hibernate.FightMode").add(create(instance)).list();
			log.debug("find by example successful, result size: "+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * Methods
	 */

	public FightMode getFightModeByMode(String mode){
		Criteria criteria = getSession().createCriteria(FightMode.class);

		FightMode fightMode = (FightMode) criteria.add(Restrictions.like("mode", mode)).uniqueResult();

		return fightMode;
	}
}
