package com.example.project.demo.config.mbatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用字段填充
 * </p>
 *
 * @author lcm
 */
@Slf4j
@Component
public class CommonFieldHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonFieldHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ....");
        this.setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setInsertFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ....");
        this.setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
