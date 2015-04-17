package com.trinary.aftv.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.persistence.GenericDAO;

public class ContestEntryDAOImpl extends GenericDAO<ContestEntry> implements ContestEntryDAO {
	@Autowired SessionFactory sessionFactory;
	
	public ContestEntryDAOImpl() {
		this.type = ContestEntry.class;
	}
	
	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
