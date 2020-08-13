package kr.co.test.eatgo.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.Category;
import kr.co.test.eatgo.domain.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository; 
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
		
	}
	
	
	public List<Category> getCategory() {
		List<Category> categorys = categoryRepository.findAll();
		return categorys;
	}

	public Category addCategory(String name) {
		Category category = Category.builder().name(name).build();
		
		categoryRepository.save(category);
		
		return category;
	}

}
