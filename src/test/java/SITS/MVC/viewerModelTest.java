package SITS.MVC;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.MVC.Models.viewerModel;

class viewerModelTest {
	
	viewerModel model;

	@BeforeEach
	void setUp() throws Exception {
		model = new viewerModel();
	}

	@Test
	void testConnectionParts() {
		model.setConnectionParts("12.12.12.12", "9090");
		assertEquals("12.12.12.12",model.getServerIp().get());
		assertEquals("9090",model.getPort().get());
		assertNotNull(model.getClient());
		assertEquals("http://12.12.12.12:9090",model.getClient().getServer_url());
	}
	
	@Test
	void testViewerConnectionParts()
	{
		model.setViewerIp("12.1.212");
		model.setViewerPort(9090);
		
		assertEquals("12.1.212",model.getViewerIp());
		assertEquals(9090,model.getViewerPort());
		
		
		
		
	}

}
