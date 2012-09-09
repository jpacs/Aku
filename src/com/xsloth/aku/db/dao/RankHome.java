package com.xsloth.aku.db.dao;

// Generated 8/Set/2012 1:13:06 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.xsloth.aku.db.hibernate.FightMode;
import com.xsloth.aku.db.hibernate.LeagueRank;
import com.xsloth.aku.db.hibernate.Rank;
import com.xsloth.aku.db.hibernate.User;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Rank.
 * @see com.xsloth.aku.db.dao.Rank
 * @author Hibernate Tools
 */
public class RankHome extends AbstractDao {

	private static final Log log = LogFactory.getLog(RankHome.class);

	protected static RankHome myInstance = null;

	@Override
	public Class getReferenceClass() {
		// TODO Auto-generated method stub
		return RankHome.class;
	}
	
	public static RankHome getInstance()
	{
		if (myInstance == null)
			myInstance = new RankHome();
		return myInstance;
	}
	
	/**
	 * CRUD Methods
	 */

	public void persist(RankHome transientInstance) {
		log.debug("persisting FightMode instance");
		try {
			this.getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RankHome instance) {
		log.debug("attaching dirty FightMode instance");
		try {
			this.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RankHome instance) {
		log.debug("attaching clean FightMode instance");
		try {
			this.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RankHome persistentInstance) {
		log.debug("deleting FightMode instance");
		try {
			this.getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RankHome merge(RankHome detachedInstance) {
		log.debug("merging FightMode instance");
		try {
			RankHome result = (RankHome) this.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RankHome findById(java.lang.Long id) {
		log.debug("getting FightMode instance with id: " + id);
		try {
			RankHome instance = (RankHome) this.getSession().get("com.xsloth.aku.db.hibernate.Rank", id);
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

	public List<RankHome> findByExample(RankHome instance) {
		log.debug("finding FightMode instance by example");
		try {
			List<RankHome> results = (List<RankHome>) this.getSession().createCriteria("com.xsloth.aku.db.hibernate.Rank").add(create(instance)).list();
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
	
	public String getValue1ByUserAndMode(User user, FightMode fightMode){
		Criteria criteria = getSession().createCriteria(Rank.class);

		criteria.add(Restrictions.like("user", user));
		criteria.add(Restrictions.like("fightMode", fightMode));

		List <Rank> rank = criteria.list();

		return rank.get(0).getValue1();
	}
	
	public Date getValue2ByUserAndMode(User user, FightMode fightMode){
		Criteria criteria = getSession().createCriteria(Rank.class);

		criteria.add(Restrictions.like("user", user));
		criteria.add(Restrictions.like("fightMode", fightMode));

		List <Rank> rank = criteria.list();

		return rank.get(0).getValue2();
	}
	
	public void updateRank(User user, FightMode fightMode, String value1, Date value2){
		Session session = getSession();
		
		Criteria criteria = session.createCriteria(Rank.class);

		System.out.println(user.getId());
		criteria.add(Restrictions.like("user", user));
		criteria.add(Restrictions.like("fightMode", fightMode));
		
		Rank rank = (Rank) criteria.list().get(0);
		System.out.println(rank.getValue1() + " " + value1);
		session.close();
		
		if(rank.getId() != 2l){
			int rankValue = Integer.parseInt(rank.getValue1()), attempt = Integer.parseInt(value1);
			System.out.println("Here and alive " + rankValue + " " + attempt);
			if(rankValue < attempt){
				session = getSession();
				System.out.println("Here and alive " + rank.getValue1());
				rank.setValue1(value1);
				Transaction transaction = session.beginTransaction();

				session.update(rank);
				session.flush();
				transaction.commit();
			}
		}
		else{
			
		}
			
	}
}
