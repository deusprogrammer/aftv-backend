package com.trinary.aftv.persistence.dao;

import com.trinary.aftv.persistence.entity.Voter;
import com.trinary.persistence.BaseDAO;
import com.trinary.persistence.Paginate;

public interface VoterDAO extends BaseDAO<Voter>, Paginate<Voter> {}