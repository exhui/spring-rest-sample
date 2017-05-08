package vip.maxhub.web.sample.api;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import vip.maxhub.web.sample.Constants;
import vip.maxhub.web.sample.exception.ValidationExceptionUtils;
import vip.maxhub.web.sample.model.Blog;
import vip.maxhub.web.sample.model.BlogForm;
import vip.maxhub.web.sample.model.PrevalentMessage;
import vip.maxhub.web.sample.service.BlogService;
import vip.maxhub.web.sample.validator.BlogFormValidator;


/**
 * blog rest服务控制器
 * Created by jinlei on 2017/4/20.
 */
@RestController
@RequestMapping(Constants.URI_API + "/blog")
public class BlogController {
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new BlogFormValidator());
    }

    @Inject
    private BlogService blogService;

    /**
     * 新增
     *
     * @param form
     * @param errResult
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public PrevalentMessage create(@RequestBody @Valid BlogForm form, BindingResult errResult) {
        if (errResult.hasErrors()) {
            throw ValidationExceptionUtils.build(errResult);
        }

        Blog blog = this.blogService.save(form);
        log.debug("created a new blog ==> " + blog);

        return new PrevalentMessage("ok");
    }

    /**
     * 查询一条
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Blog findOneById(@PathVariable("id") Long id) {

        Blog blog = this.blogService.findOneById(id);

        log.debug("created a new blog ==> " + blog);
        return blog;
    }

    /**
     * 查询全部
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> findAll() {

        List<Blog> blogs = this.blogService.findAll();
        log.debug("find all of blogs ==> " + blogs);

        return blogs;
    }

    /**
     * 修改
     *
     * @param id
     * @param form
     * @param errResult
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public PrevalentMessage update(@PathVariable("id") Long id, @RequestBody @Valid BlogForm form, BindingResult errResult) {
        if (errResult.hasErrors()) {
            throw ValidationExceptionUtils.build(errResult);
        }

        Blog blog = this.blogService.updateOneById(id, form);
        log.debug("updated a blog ==> " + blog);

        return new PrevalentMessage("ok");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public PrevalentMessage delete(@PathVariable("id") Long id) {

        Boolean success = this.blogService.deleteOneById(id);
        log.debug("deleted a blog ==> " + success);

        return new PrevalentMessage("ok");
    }
}
