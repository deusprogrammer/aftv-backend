package com.trinary.aftv.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.persistence.GenericDAO;

public class ContestDAOImpl extends GenericDAO<Contest> implements ContestDAO {
	@Autowired SessionFactory sessionFactory;
	
	public ContestDAOImpl() {
		this.type = Contest.class;
	}
	
	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}