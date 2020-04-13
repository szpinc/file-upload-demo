package me.szp.demo.file.upload.strategy.annotation;

import me.szp.demo.file.upload.strategy.enu.UploadModeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Component
@Inherited
public @interface UploadMode {

    UploadModeEnum mode();

}
