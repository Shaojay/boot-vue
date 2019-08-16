package com.jay.boot.common.config.quartz;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ClassName TaskInfo
 *
 * @author 搬砖小能手
 * @since 1.0
 * Date 2019/8/6 23:23
 */
@Data
@Accessors(chain = true)
public class TaskInfo {
    
    private String jobName;
    
    private String jobGroup;
    
    private String jobClass;
    
    private String triggerName;
    
    private String triggerGroup;
    
    private String cronExpression;
}
