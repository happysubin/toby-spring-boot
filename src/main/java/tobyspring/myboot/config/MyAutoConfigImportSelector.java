package tobyspring.myboot.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

//BeanClassLoaderAware 인터페이스를 구현하면 세터에서 꽂아줌.

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();
        // META-INF/spirng 아래에 imports가 붙은 파일에서 컨테이너에 등록시킬 클래스 목록을 가져와서 등록한다.
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        //return autoConfigs.toArray(new String[0]); 이렇게도 가능.
        return autoConfigs.toArray(String[]::new);
    }
}
