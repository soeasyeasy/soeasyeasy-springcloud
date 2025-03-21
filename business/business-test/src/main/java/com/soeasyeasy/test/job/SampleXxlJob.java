//package com.soeasyeasy.test.job;
//
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.context.BaseJob;
//import com.xxl.job.core.handler.annotation.XxlJob;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * XxlJob开发示例（Bean模式）
// * <p>
// * 开发步骤：
// * 1、在Spring Bean实例中，开发Job方法，方式格式要求为 "public ReturnT<String> execute(String param)"
// * 2、为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
// * 3、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
// *
// * @author xuxueli 2019-12-11 21:52:51
// */
//@Component
//public class SampleXxlJob extends BaseJob {
//    private static Logger logger = LoggerFactory.getLogger(SampleXxlJob.class);
//
//
//    /**
//     * 1、简单任务示例（Bean模式）
//     */
//    @XxlJob(value = "demoJobHandler", init = "init", destroy = "destroy")
//    public ReturnT<String> demoJobHandler(String param) throws Exception {
//        for (int i = 0; i < 1; i++) {
//            logger.info("beat at:" + i);
//            TimeUnit.SECONDS.sleep(1);
//        }
//        return ReturnT.SUCCESS;
//    }
//
//
//    @Override
//    public void init() {
//        super.init();
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy();
//    }
//
//}
