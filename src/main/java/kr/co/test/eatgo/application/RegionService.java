package kr.co.test.eatgo.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.Region;
import kr.co.test.eatgo.domain.RegionRepository;

@Service
@Transactional
public class RegionService {

	@Autowired
	private RegionRepository regionRepository;
	
	public RegionService(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}
	
	public List<Region> getRegions() {
		List<Region> regions = regionRepository.findAll();
		return regions;
	}

	public Region addRegion(String name) {
		Region region = Region.builder().name(name).build();
		
		regionRepository.save(region);
		
		return region;
	}

}
