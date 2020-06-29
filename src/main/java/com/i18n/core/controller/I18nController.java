package com.i18n.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i18n.core.model.form.CreateForm;
import com.i18n.core.model.form.UpdateForm;
import com.i18n.core.model.vo.I18nVo;
import com.i18n.core.service.I18nService;
import com.i18n.core.validate.ValidationGroups;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-22 09:38
 */
@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Autowired
    private I18nService i18nService;

    @PostMapping("/create")
    public void create(@RequestBody @Validated(ValidationGroups.Insert.class) CreateForm form) {
        i18nService.create(form);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Validated(ValidationGroups.Update.class) UpdateForm form) {
        i18nService.update(form);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("refId") Integer refId, @RequestParam("refType") String refType) {
        i18nService.delete(refId, refType);
    }

    @GetMapping("/list")
    public List<I18nVo> list() {
        return i18nService.listVo();
    }

    @GetMapping("/info")
    public List<I18nVo> info(@RequestParam("refId") Integer refId, @RequestParam("refType") String refType) {
        return i18nService.info(refId, refType);
    }
}
