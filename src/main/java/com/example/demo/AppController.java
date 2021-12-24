package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//Đánh dấu AppController là một Controller - tiếp nhận các thông tin request từ phía người dùng và chuyển cho @Service xử lý logic
@Controller
public class AppController {

	@Autowired
	private ProductService service;

	// handler methods...

	@RequestMapping("/") 						 // nơi hiển thị khi bắt đầu chạy chương trình
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();							// gọi tới xử lí logic bên service
		model.addAttribute("listProducts", listProducts);						// gán tham số listProduct vào model

		return "index";														// chuyển sang trang index.html
	}

	@RequestMapping("/new")							// địa chỉ trang - ex: localhost:8080/new
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);

		return "new_product";							// trả về trang new_product.html
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)				 // hàm chạy khi phương thức POST được gọi
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);

		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {			// lấy id từ địa chỉ
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);

		return mav;
	}

	@RequestMapping("/delete/{id}")
			public String deleteProduct(@PathVariable(name = "id") int id) {				// lấy id từ địa chỉ
				service.delete(id);
		return "redirect:/";
	}

}