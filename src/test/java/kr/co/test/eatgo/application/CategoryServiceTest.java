package kr.co.test.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.Category;
import kr.co.test.eatgo.domain.CategoryRepository;

class CategoryServiceTest {

	private CategoryService categoryService;

	@Mock
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	
		categoryService = new CategoryService(categoryRepository);
	}
	
	
	@Test
	public void getCategory() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(Category.builder().name("Korean Food").build());
		
		given(categoryService.getCategory()).willReturn(categories);
		
		Category category = categories.get(0);
		assertThat(category.getName(), is("Korean Food"));
	}
	
	@Test
	public void addCategory() {
		Category category = categoryService.addCategory("Korean Food");
		
		categoryRepository.save(any());
		
		assertThat(category.getName(), is("Korean Food"));
		
	}

}
