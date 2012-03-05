package com.innowhite.mp4converter.dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.innowhite.mp4converter.model.PlayBackPlayList;

public class MP4ConverterDAO {

	private static final Logger log = LoggerFactory.getLogger(MP4ConverterDAO.class);
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void updateMp4FilePath(PlayBackPlayList playBackObj){
		log.debug("entered updateMp4FilePath");
		int returnVal=0;
		try {
			Session session = sessionFactory.getCurrentSession();

			String query = "update PlayBackPlayList pp set pp.downloadUrl=:mp4FilePath  where pp.id=:fileId";
			returnVal = session.createQuery(query).setString("mp4FilePath", playBackObj.getDownloadUrl())
					.setString("fileId", playBackObj.getId()).executeUpdate();
			session.clear();
			session.flush();
			log.debug("retuned updateMp4FilePath val " + returnVal);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
}
