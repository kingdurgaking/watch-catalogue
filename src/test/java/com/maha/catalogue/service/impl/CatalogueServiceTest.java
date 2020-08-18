package com.maha.catalogue.service.impl;

import com.maha.catalogue.dao.CatalogueRepository;
import com.maha.catalogue.entity.WatchCatalogue;
import com.maha.catalogue.service.CatalogueService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by durga on 8/18/2020.
 */

@RunWith(MockitoJUnitRunner.class)
public class CatalogueServiceTest {


    @Mock
    private CatalogueRepository repository;

    private CatalogueService catalogueService;

    @Before
    public void init(){

        List<WatchCatalogue> catalogues = Arrays.asList(
                new WatchCatalogue(0L,"001", "Rolex", 100d , 3 , 200d),
                new WatchCatalogue(1L,"002", "Michael Kors", 80d , 2 , 120d),
                new WatchCatalogue(2L,"003", "Swatch", 50d , 0 , 0),
                new WatchCatalogue(3L,"004", "Casio", 30d , 0 , 0)

        );

        Mockito.when(repository.findAll()).thenReturn(catalogues);
        catalogueService = new CatalogueServiceImpl(repository);
    }

    @Test
    public void defaultCaseForRepository() {
        Assert.assertEquals(4, repository.findAll().size());
    }

    @Test
    public void defaultCaseForTestInput() {
        Assert.assertEquals(4, catalogueService.getAll().size());
    }

    @Test
    public void userGivenTesttCase(){
        List<String> keys = Arrays.asList("001", "002", "001" , "004", "003");
        Assert.assertEquals(360d, catalogueService.getPriceForSelectedItems(keys),360);

        keys = Arrays.asList("001", "001", "001" , "001", "001");
        Assert.assertEquals(500d, catalogueService.getPriceForSelectedItems(keys),500);

        keys = Arrays.asList("001a", "001a", "001a" , "001a", "001a");
        Assert.assertEquals(0d, catalogueService.getPriceForSelectedItems(keys),0);
    }

}
