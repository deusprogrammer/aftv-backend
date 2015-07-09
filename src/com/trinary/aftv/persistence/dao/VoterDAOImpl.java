package com.trinary.aftv.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.trinary.aftv.persistence.entity.Voter;
import com.trinary.persistence.GenericDAO;

public class VoterDAOImpl extends GenericDAO<Voter> implements VoterDAO {
	@Autowired SessionFactory sessionFactory;
	
	public VoterDAOImpl() {
		this.type = Voter.class;
	}
	
	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}