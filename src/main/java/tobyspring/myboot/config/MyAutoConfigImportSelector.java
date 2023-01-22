package tobyspring.myboot.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "tobyspring.myboot.config.autoconfig.DispatcherServletConfig",
                "tobyspring.myboot.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
