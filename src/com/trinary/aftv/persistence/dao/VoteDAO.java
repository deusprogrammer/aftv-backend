package com.trinary.aftv.persistence.dao;

import com.trinary.aftv.persistence.entity.Vote;
import com.trinary.persistence.BaseDAO;
import com.trinary.persistence.Paginate;

public interface VoteDAO extends BaseDAO<Vote>, Paginate<Vote> {}