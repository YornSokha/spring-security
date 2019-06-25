package com.hrd.springsecurity.controller.RestController;

import com.hrd.springsecurity.helper.UploadImage;
import com.hrd.springsecurity.repository.model.Article;
import com.hrd.springsecurity.service.ArticleService.ArticleService;
import com.hrd.springsecurity.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ArticleRestController {
    private ArticleService articleService;
    private CategoryService categoryService;
    // this article is use to store temporary error data while adding article
    private Article article = new Article();

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/articles")
    public ResponseEntity<Map<String, Object>> index(
                                                     @RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer limit
                                                     ) {
        System.out.println("Called");
        Map<String, Object> map = new HashMap<>();
        List<Article> articles = articleService.paginate(page, limit);
        if(!articles.isEmpty()){
            map.put("data", articles);
            map.put("status", true);
            map.put("count", articles.size());
            map.put("message", "OK");
        }else{
            map.put("status", false);
            map.put("message", "NOT OK");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/create-test")
    public String createTest(ModelMap model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categoryService.findAll());
        return "/articles/create_test";
    }

    @GetMapping("/article/add")
    public String create(ModelMap model) {
        model.addAttribute("article", article);
        model.addAttribute("categories", categoryService.findAll());
        return "/articles/create";
    }

    @PostMapping("/article/add")
    public String add(@Valid @ModelAttribute Article article,
                      BindingResult bindingResult,
                      MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            model.addAttribute("categories", categoryService.findAll());
            this.article = article; // to store temporary error data
            model.addAttribute("article", article);
            return "/articles/create";
        }

        UploadImage.upload(article, file);
        articleService.add(article);

        // reset article;
        this.article = new Article();
        return "redirect:/article/add";
    }

    @DeleteMapping("/article/{id}")
    public String delete(@PathVariable int id) {
        System.out.println(id);
        articleService.remove(id);
        return "redirect:/article";
    }

    @GetMapping("/article/show/{id}")
    public String show(@PathVariable int id, Model model) {
        Article article = articleService.find(id);
        System.out.println("ID = " + id);
        System.out.println(article.toString());
        model.addAttribute("article", article);
        return "/articles/show";
    }

    @GetMapping("/article/edit/{id}")
    public String edit(ModelMap model, @PathVariable int id) {
        model.addAttribute("article", articleService.find(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("org.springframework.validation.BindingResult.article", model.get("errorObject"));
        return "/articles/edit";
    }

    @PostMapping("/article/update")
    public String update(@Valid @ModelAttribute Article article,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         MultipartFile file) {

        if (bindingResult.hasErrors()) {
            System.out.println("there is an error");
            redirectAttributes.addFlashAttribute("errorObject", bindingResult);
            return "redirect:/article/edit/" + article.getId();
        }

        String currentImage = articleService.find(article.getId()).getImage();

        if (!UploadImage.upload(article, file)) {
            article.setImage(currentImage);
        }

        articleService.update(article);
        return "redirect:/";
    }
}
