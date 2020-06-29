A.International translation implementation(springboot2.1.8):  
com.i18n.core.controller.I18nController (Basic CLASS of translation)  
com.i18n.core.controller.TestController (demo)  

B.Core class:  
com.i18n.core.advice.I18nResponseBodyAdvice  
com.i18n.core.service.impl.I18nServiceImpl  

C.Implementation:  
1.Use the custom annotation @ I18N to add to the corresponding field，  
use com.i18n.core.service.impl.I18nServiceImpl.translate translate to other languages.  
2.When requesting access using HTTP，Add the corresponding language to the header， 
via com.i18n.core.advice.I18nResponseBodyAdvice.Translate translate to corresponding language return.  
(reference: com.i18n.core.controller.TestController.test)  

qq email: 2791951533@qq.com  
