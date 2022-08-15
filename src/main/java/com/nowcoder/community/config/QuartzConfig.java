package com.nowcoder.community.config;

import com.nowcoder.community.quartz.AlphaJob;
import com.nowcoder.community.quartz.PostScoreRefreshJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

// 配置的作用：配置不是每次都有用的，仅仅在第一次被读取到，将封装的信息初始化到数据库里，
// 然后以后quartz访问数据库调度任务，而不再访问配置文件。
// 配置 -> 数据库 -> 调用
@Configuration
public class QuartzConfig {

    // BeanFactory：表示它是一个工厂类(接口)，它负责生产和管理bean的一个工厂。
    // 在Spring中，BeanFactory是IOC容器的核心接口。

    // FactoryBean可简化Bean的实例化过程:
        // 1.通过FactoryBean封装Bean的实例化过程.
        // 2.将FactoryBean装配到Spring容器里.
        // 3.将FactoryBean注入给其他的Bean.
        // 4.该Bean得到的是FactoryBean所管理的对象实例.
//    @Bean
    // JobDetail配置Job
    public JobDetailFactoryBean alphaJobDetail(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(AlphaJob.class); // 管理的类型是什么
        factoryBean.setName("alphaJob"); // 设置任务的名字
        factoryBean.setGroup("alphaJobGroup"); // 设置任务的组名
        factoryBean.setDurability(true); // 任务持久的保存
        factoryBean.setRequestsRecovery(true); // 任务是否可恢复
        return factoryBean;
    }

    // Trigger触发器，配置Job什么时候运行，什么频率运行
    // 配置Trigger(SimpleTriggerFactoryBean, CronTriggerFactoryBean)
//    @Bean
    public SimpleTriggerFactoryBean alphaTrigger(JobDetail alphaJobDetail){
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(alphaJobDetail);
        factoryBean.setName("alphaTrigger");
        factoryBean.setGroup("alphaTriggerGroup");
        factoryBean.setRepeatInterval(3000);
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }

    // 刷新帖子分数任务
    @Bean
    public JobDetailFactoryBean postScoreRefreshJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(PostScoreRefreshJob.class);
        factoryBean.setName("postScoreRefreshJob");
        factoryBean.setGroup("communityJobGroup");
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean postScoreRefreshTrigger(JobDetail postScoreRefreshJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(postScoreRefreshJobDetail);
        factoryBean.setName("postScoreRefreshTrigger");
        factoryBean.setGroup("communityTriggerGroup");
        factoryBean.setRepeatInterval(1000 * 60 * 5);
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }


}
