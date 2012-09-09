package com.xsloth.aku.db;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.xsloth.aku.db.dao.FightHome;
import com.xsloth.aku.db.dao.FightModeHome;
import com.xsloth.aku.db.dao.RankHome;
import com.xsloth.aku.network.NetworkData;

public class DataWriter {
	
	public static void registerOffSurvivalFight(String value1){
		FightHome.getInstance().insertFight(NetworkData.getUser(), NetworkData.getOpponent(), "won", new Date(), true, FightModeHome.getInstance().getFightModeByMode("off_survival"), value1, new Date());
		RankHome.getInstance().updateRank(NetworkData.getUser(), FightModeHome.getInstance().getFightModeByMode("off_survival"), value1, null);
	}
	
//	public TrackComentario getCommentByID(int id)
//	  {
//	       Criteria criteria = getSession().createCriteria(TrackComentario.class);
//	       
//	       List <TrackComentario> commentList = criteria.add(Restrictions.like("id", id)).list();
//	       
//	       return commentList.get(0);
//	  }
//	  
//	  public List<TrackComentario> getAllComments()
//	  {
//	      Criteria criteria = getSession().createCriteria(TrackComentario.class);
//	      List <TrackComentario> commentList = criteria.list();
//	       
//	      return commentList;
//	  }
//	  
//	  public List<TrackComentario> getCommentByProcID(int id)
//	  {
//	       Criteria criteria = getSession().createCriteria(TrackComentario.class);
//	       List <TrackComentario> commentList = criteria.add(Restrictions.like("trackProcesso", TrackProcessoDao.getInstance().getProcessByID(id))).list();
//
//	       return commentList;
//	  }
}
