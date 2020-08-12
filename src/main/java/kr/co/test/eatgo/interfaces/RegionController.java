package kr.co.test.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.eatgo.application.RegionService;
import kr.co.test.eatgo.domain.Region;

@RestController
public class RegionController {
	
	@Autowired
	RegionService regionService;

	@GetMapping("/regions")
	public List<Region> list(){
		List<Region> regions = regionService.getRegions();
		return regions;
	}

	@PostMapping("/regions")
	public ResponseEntity<?> create(
			@RequestBody Region reources) throws URISyntaxException{
		
		String name= reources.getName();
		
		Region region= regionService.addRegion(name);
		
		String url = "/regions/" + region.getId();
		return ResponseEntity.created(new URI(url)).body("{}");
	}
}
