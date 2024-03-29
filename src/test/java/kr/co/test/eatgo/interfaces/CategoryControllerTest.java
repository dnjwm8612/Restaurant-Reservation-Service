package kr.co.test.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.test.eatgo.application.CategoryService;
import kr.co.test.eatgo.domain.Category;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)

class CategoryControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CategoryService categoryService;
	
	@Test
	public void list() throws Exception{
		List<Category> categorys = new ArrayList<Category>();
		categorys.add(Category.builder().name("Korean Food").build());
		
		given(categoryService.getCategory()).willReturn(categorys);
		
		mvc.perform(get("/categories"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Korean Food")));
	}
	
	@Test
	public void create() throws Exception {
		Category category = Category.builder().name("Korean Food").build();
		given(categoryService.addCategory("Korean Food")).willReturn(category);
		
		mvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Korean Food\"}"))
		.andExpect(status().isCreated())
		.andExpect(content().string(containsString("{}")));
		
		verify(categoryService).addCategory("Korean Food");
		
	}

}
