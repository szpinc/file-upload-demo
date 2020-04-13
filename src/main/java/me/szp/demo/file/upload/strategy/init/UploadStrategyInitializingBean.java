package me.szp.demo.file.upload.strategy.init;

import lombok.extern.slf4j.Slf4j;
import me.szp.demo.file.upload.strategy.context.UploadContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UploadStrategyInitializingBean implements InitializingBean {

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("init uploadStrategy ...");
    UploadContext.INSTANCE.init();
  }
}
