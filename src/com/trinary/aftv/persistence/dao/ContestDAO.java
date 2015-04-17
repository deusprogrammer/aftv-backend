package com.trinary.aftv.persistence.dao;

import com.trinary.aftv.persistence.entity.Contest;
import com.trinary.persistence.BaseDAO;
import com.trinary.persistence.Paginate;

public interface ContestDAO extends BaseDAO<Contest>, Paginate<Contest> {}
