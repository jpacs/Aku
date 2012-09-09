package com.xsloth.aku.db.dao;

import org.hibernate.Session;
//import Persist.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Jorge Machado
 * @date 27/Out/2008
 * @see pt.estgp.es.exemplos.servicos.dao
 */
public abstract class AbstractDao
{

    public abstract Class getReferenceClass();

    public Session getSession()
    {
       SessionFactory sf = new Configuration().configure("com/xsloth/aku/db/hibernate.cfg.xml").buildSessionFactory();
      return sf.openSession();
       //  return HibernateUtil.getCurrentSession();
    }

    public void save(Object object)
    {
        getSession().save(object);
    }

    public void update(Object object)
    {
        getSession().update(object);
    }

    public void delete(Object object)
    {
        getSession().delete(object);
    }

    public Object load(long id)
    {
       return getSession().load(getReferenceClass(),id);
    }
    
    public Object get(long id)
    {
       return getSession().get(getReferenceClass(),id);
    }


}
