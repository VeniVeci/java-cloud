package com.zukxu.common.log.schedule;

import com.alibaba.fastjson2.JSON;
import com.zukxu.common.log.model.SysLogOpRecord;
import com.zukxu.common.log.service.ISysLogOpRecordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class LogScheduleTask implements Serializable {

    //@formatter:off
    private final ISysLogOpRecordService sysLogOpRecordService;
    private final LogQueue logQueue;
    //@formatter:on

    @Scheduled(fixedRate = 500)
    public void consume() {
        if(logQueue.size() > 0) {
            String logStr = logQueue.take();
            Optional.ofNullable(logStr).ifPresent(t -> {
                SysLogOpRecord sysLogOpRecord = JSON.parseObject(t, SysLogOpRecord.class);
                sysLogOpRecord.setOpReqContent(JSON.toJSONString(sysLogOpRecord.getReqContent()));
                sysLogOpRecordService.save(sysLogOpRecord);
            });
        } else {
            try {
                Thread.sleep(5000);
            } catch(InterruptedException ite) {
                log.error("线程休眠失败", ite);
            }
        }
    }

}
