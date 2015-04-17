package com.trinary.aftv.persistence.dao;

import com.trinary.aftv.persistence.entity.ContestEntry;
import com.trinary.persistence.BaseDAO;
import com.trinary.persistence.Paginate;

public interface ContestEntryDAO extends BaseDAO<ContestEntry>, Paginate<ContestEntry> {}