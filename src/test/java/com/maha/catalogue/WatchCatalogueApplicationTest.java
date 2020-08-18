package com.maha.catalogue;

import com.maha.catalogue.controller.CatalogueController;
import com.maha.catalogue.entity.WatchCatalogue;
import com.maha.catalogue.service.CatalogueService;
import com.maha.catalogue.service.impl.CatalogueServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class WatchCatalogueApplicationTest {

	private CatalogueController catalogueController;


	@Before
	public void init() {


	}

	@Test
	public void doDefaultTest(){
		CatalogueService catalogueService = Mockito.mock(CatalogueServiceImpl.class);

		catalogueController	=	new CatalogueController(catalogueService);
		Mockito.when(catalogueService.findByBusinessKey("001")).thenReturn(
				new WatchCatalogue(0L,"001", "Rolex" , 200d , 3, 100d )
		);

		Mockito.when(catalogueService.getAll()).thenReturn(
				Arrays.asList(new WatchCatalogue(0L,"001", "Rolex" , 200d , 3, 100d ),
						new WatchCatalogue(1L,"002", "Watch 2" , 100d , 3, 100d ),
						new WatchCatalogue(2L,"003", "Watch 1" , 50d , 0, 0d ),
						new WatchCatalogue(3L,"004", "Casio" , 30d , 0, 0d ))
		);

		Mockito.when(catalogueService.getPriceForSelectedItems(Arrays.asList("001", "002"))).thenReturn(
				100d
		);
		Assert.assertTrue(catalogueController.getCatalogue("001").getStatusCode()== HttpStatus.OK);
		Assert.assertTrue(catalogueController.getCatalogue("001a").getStatusCode()== HttpStatus.NOT_FOUND);

		Assert.assertTrue(catalogueController.getAllCatalogues().getStatusCode()== HttpStatus.OK);
		Assert.assertTrue(catalogueController.checkOut(Arrays.asList("001", "002")).getStatusCode()== HttpStatus.OK);
		Assert.assertTrue(catalogueController.checkOut(Arrays.asList("001a", "002a")).getStatusCode()== HttpStatus.NOT_ACCEPTABLE);
	}



}