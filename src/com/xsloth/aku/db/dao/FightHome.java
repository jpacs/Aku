package com.xsloth.aku.db.dao;

// Generated 8/Set/2012 1:13:06 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import com.xsloth.aku.db.hibernate.Fight;
import com.xsloth.aku.db.hibernate.FightMode;
import com.xsloth.aku.db.hibernate.User;
import com.xsloth.aku.network.NetworkData;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Fight.
 * @see com.xsloth.aku.db.dao.Fight
 * @author Hibernate Tools
 */
public class FightHome extends AbstractDao {

	private static final Log log = LogFactory.getLog(FightHome.class);
	
	protected static FightHome myInstance = null;

	@Override
	public Class getReferenceClass() {
		// TODO Auto-generated method stub
		return FightHome.class;
	}
	
	public static FightHome getInstance()
	{
		if (myInstance == null)
			myInstance = new FightHome();
		return myInstance;
	}
	
	/**
	 * CRUD Methods
	 */

	public void persist(Fight transientInstance) {
		log.debug("persisting Fight instance");
		try {
			this.getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fight instance) {
		log.debug("attaching dirty Fight instance");
		try {
			this.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fight instance) {
		log.debug("attaching clean Fight instance");
		try {
			this.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fight persistentInstance) {
		log.debug("deleting Fight instance");
		try {
			this.getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fight merge(Fight detachedInstance) {
		log.debug("merging Fight instance");
		try {
			Fight result = (Fight) this.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fight findById(java.lang.Long id) {
		log.debug("getting Fight instance with id: " + id);
		try {
			Fight instance = (Fight) this.getSession().get("com.xsloth.aku.db.hibernate.Fight", id);
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

	public List<Fight> findByExample(Fight instance) {
		log.debug("finding Fight instance by example");
		try {
			List<Fight> results = (List<Fight>) this.getSession().createCriteria("com.xsloth.aku.db.hibernate.Fight").add(create(instance)).list();
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
	
	public void insertFight(User id_user, User opponent, String result, Date date, boolean ranked, FightMode id_mode, String value1, Date value2)
	{
		Session session = getSession();
		Transaction transaction = session.beginTransaction();

		Fight fight = new Fight();
		System.out.println(id_user.getFirstName());

		 fight.setUserByIdUser(id_user);//if(id_user != null){ fight.setUserByIdUser(id_user); } else { fight.setUserByIdUser(null); }
		if(opponent != null){ fight.setUserByOpponent(opponent); } else { fight.setUserByOpponent(null); }
		fight.setResult(result);
		fight.setDate(date);
		fight.setRanked(ranked);
		fight.setFightMode(id_mode);
		if(id_mode.getId() != 2){
			fight.setValue1(value1);
			fight.setValue2(null);
		}
		else{
			fight.setValue1(null);
			fight.setValue2(value2);
		}
		
		
		try{
			session.save(fight);
			session.flush();
			transaction.commit();
		}
		catch (SessionException e) {
			transaction.rollback();
		}
		catch (TransactionException e)  {
			transaction.rollback();
		}

		session.close();
	}
}
