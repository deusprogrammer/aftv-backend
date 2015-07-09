package com.trinary.aftv.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.persistence.GenericDAO;

public class VoteDAOImpl extends GenericDAO<Vote> implements VoteDAO {
	@Autowired SessionFactory sessionFactory;
	
	public VoteDAOImpl() {
		super();
		this.type = Vote.class;
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}