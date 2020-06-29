package com.i18n.core.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.i18n.core.model.entity.I18n;
import com.i18n.core.model.form.CreateForm;
import com.i18n.core.model.form.UpdateForm;
import com.i18n.core.model.vo.I18nVo;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-17 13:50
 */
public interface I18nService extends IService<I18n> {

    /**
     * 获取刷新后的map
     * 
     * @return
     */
    Map<String, String> getRefreshI18nMap();

    /**
     * 翻译
     * 
     * @param language
     * @param in
     * @param <T>
     * @return
     */
    <T> T translate(String language, T in);

    /**
     * 新增翻译
     *
     * @param createForm
     */
    void create(CreateForm createForm);

    /**
     * 编辑翻译
     *
     * @param updateForm
     */
    void update(UpdateForm updateForm);

    /**
     * 删除翻译
     *
     * @param refId
     * @param refType
     */
    void delete(Integer refId, String refType);

    /**
     * 翻译后的列表
     *
     * @return
     */
    List<I18nVo> listVo();

    /**
     * 翻译详情
     *
     * @param refId
     * @param refType
     */
    List<I18nVo> info(Integer refId, String refType);
}
