package com.xsloth.aku.db.dao;

// Generated 8/Set/2012 1:13:06 by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.xsloth.aku.db.hibernate.User;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class User.
 * @see com.xsloth.aku.db.dao.User
 * @author Hibernate Tools
 */
public class UserHome extends AbstractDao {

	private static final Log log = LogFactory.getLog(UserHome.class);

	protected static UserHome myInstance = null;

	@Override
	public Class getReferenceClass() {
		// TODO Auto-generated method stub
		return UserHome.class;
	}
	
	public static UserHome getInstance()
	{
		if (myInstance == null)
			myInstance = new UserHome();
		return myInstance;
	}
	
	/**
	 * CRUD Methods
	 */

	public void persist(User transientInstance) {
		log.debug("persisting FightMode instance");
		try {
			this.getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty FightMode instance");
		try {
			this.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean FightMode instance");
		try {
			this.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting FightMode instance");
		try {
			this.getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging FightMode instance");
		try {
			User result = (User) this.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Long id) {
		log.debug("getting FightMode instance with id: " + id);
		try {
			User instance = (User) this.getSession().get("com.xsloth.aku.db.hibernate.User", id);
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

	public List<User> findByExample(User instance) {
		log.debug("finding FightMode instance by example");
		try {
			List<User> results = (List<User>) this.getSession().createCriteria("com.xsloth.aku.db.hibernate.User").add(create(instance)).list();
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
	
	public boolean authenticate(String username, String password)
	{
		Criteria criteria = getSession().createCriteria(User.class);

		criteria.add(Restrictions.like("nick", username));
		criteria.add(Restrictions.like("password", password));

		List <User> userList = criteria.list();

		if(userList.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public List<User> getAllUsers()
	{
		Criteria criteria = getSession().createCriteria(User.class);
		List <User> userList = criteria.list();
		System.out.println("Here");
		return userList;
	}

	public void insertUser(String nick, String password, String firstName, String lastName, String email)
	{
		Session session = getSession();
		Transaction transaction = session.beginTransaction();

		User user = new User();

		user.setNick(nick);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);

		session.save(user);
		session.flush();
		transaction.commit();

		session.close();

		/*try {
	        Thread.sleep(500);
	    } catch (InterruptedException ex) {
	        Logger.getLogger(ServiceWrite.class.getName()).log(Level.SEVERE, null, ex);
	    }*/
	}
}
