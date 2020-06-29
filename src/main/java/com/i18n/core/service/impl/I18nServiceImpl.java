package com.i18n.core.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.benmanes.caffeine.cache.Cache;
import com.i18n.core.config.CacheConfig;
import com.i18n.core.enums.RefTypeEnum;
import com.i18n.core.mapper.I18nMapper;
import com.i18n.core.model.entity.I18n;
import com.i18n.core.model.form.CreateForm;
import com.i18n.core.model.form.UpdateForm;
import com.i18n.core.model.vo.I18nVo;
import com.i18n.core.service.I18nService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-16 14:02
 */
@Service
@Slf4j
public class I18nServiceImpl extends ServiceImpl<I18nMapper, I18n> implements I18nService {

    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    @Qualifier("i18nMapCache")
    private Cache<String, Map<String, String>> i18nMapCache;

    private Map<String, String> getI18nMap() {
        return i18nMapCache.get(CacheConfig.I18N_KEY, key -> getRefreshI18nMap());
    }

    @Override
    public Map<String, String> getRefreshI18nMap() {
        List<I18n> i18nList = i18nMapper.selectList(new QueryWrapper<I18n>().lambda().select(I18n::getRefId,
            I18n::getLanguageType, I18n::getRefType, I18n::getTranslateText));
        return i18nList.stream()
            .collect(Collectors.toMap(
                translate -> translate.getRefId() + ":" + translate.getRefType() + ":" + translate.getLanguageType(),
                I18n::getTranslateText, (e1, e2) -> e1));
    }

    @Override
    public <T> T translate(String language, T in) {
        Map<String, Integer> refIdMap = new HashMap<>();
        try {
            Field[] fields = in.getClass().getDeclaredFields();

            // 获取refId的分组refType
            for (Field field : fields) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(com.i18n.core.annotation.I18n.class)) {
                    com.i18n.core.annotation.I18n i18nAnnotation =
                        field.getAnnotation(com.i18n.core.annotation.I18n.class);
                    String refIdAlias = i18nAnnotation.refIdAlias();
                    if (StringUtils.isEmpty(refIdAlias)) {
                        continue;
                    }

                    Field refField = in.getClass().getDeclaredField(refIdAlias);
                    refField.setAccessible(true);
                    Integer refId = (Integer)refField.get(in);
                    refIdMap.put(refIdAlias, refId);
                }
            }

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(com.i18n.core.annotation.I18n.class)) {
                    com.i18n.core.annotation.I18n i18nAnnotation =
                        field.getAnnotation(com.i18n.core.annotation.I18n.class);
                    String refIdAlias = i18nAnnotation.refIdAlias();
                    // refId和被翻译的数据必须属于同一个refType
                    Integer refId = refIdMap.get(refIdAlias);
                    if (refId != null) {
                        // 获取refType,languageType
                        RefTypeEnum[] refTypeEnums = i18nAnnotation.refType();
                        List<String> refTypeList =
                            Arrays.stream(refTypeEnums).map(RefTypeEnum::getCode).collect(Collectors.toList());

                        Map<String, String> i18nMap = getI18nMap();

                        // 被翻译值的原始值
                        String defaultText = String.valueOf(field.get(in));
                        List<String> translateTextList = new ArrayList<>();
                        // 从翻译map中取出匹配的值
                        refTypeList.forEach(refType -> {
                            String translateKey = refId + ":" + refType + ":" + language;
                            String translateValue = i18nMap.get(translateKey);
                            if (!StringUtils.isEmpty(translateValue)) {
                                translateTextList.add(translateValue);
                            }
                        });

                        // 复制给翻译的值
                        String translateText = translateTextList.stream().findFirst().orElse(defaultText);

                        // 翻译后重新设置
                        field.set(in, translateText);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("多语言序列化时异常,被翻译的对象:{},i18n:{}", in, e.toString(), e);
        }
        return in;
    }

    /**
     * 新增翻译
     * 
     * @param createForm
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(CreateForm createForm) {
        List<I18n> i18ns =
            createForm.getI18nList().stream().map(form -> new I18n().buildI18n(form)).collect(Collectors.toList());
        this.saveBatch(i18ns);
    }

    /**
     * 编辑翻译
     * 
     * @param updateForm
     */
    @Override
    public void update(UpdateForm updateForm) {
        List<I18n> i18ns =
            updateForm.getI18nList().stream().map(form -> new I18n().buildI18n(form)).collect(Collectors.toList());
        this.saveOrUpdateBatch(i18ns);
    }

    /**
     * 删除翻译
     * 
     * @param refId
     * @param refType
     */
    @Override
    public void delete(Integer refId, String refType) {
        this.remove(new QueryWrapper<I18n>().lambda().eq(I18n::getRefId, refId).eq(I18n::getRefType, refType));
    }

    /**
     * 翻译后的列表
     * 
     * @return
     */
    @Override
    public List<I18nVo> listVo() {
        return this.list().stream().map(I18nVo::new).collect(Collectors.toList());
    }

    /**
     * 翻译详情
     *
     * @param refId
     * @param refType
     */
    @Override
    public List<I18nVo> info(Integer refId, String refType) {
        return this.list(new QueryWrapper<I18n>().lambda().eq(I18n::getRefId, refId).eq(I18n::getRefType, refType))
            .stream().map(I18nVo::new).collect(Collectors.toList());
    }
}
