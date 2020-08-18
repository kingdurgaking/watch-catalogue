package com.maha.catalogue.dao;

import com.maha.catalogue.entity.WatchCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is a data repository for watches catalogue table in the database.
 * This is a standard sping data JPA repository
 * Created by durga on 8/18/2020.
 */
@Repository
public interface CatalogueRepository extends JpaRepository<WatchCatalogue,Long> {

    public WatchCatalogue findByBusinessKey(String businessKey);
}
