package com.oauth.test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhouhuiyi.
 * @Description:
 * @Date:Created in 2018/2/6 20:38.
 */
@Slf4j
public class MyBatisGeneratorUtil {

    public static void main(String[] args) throws InterruptedException {
        //如果是已经存在公用字段属性的表
        final boolean isBaseEntityFiledTable = false;
        final String moduleName = "oa";
        //修改相关的生成参数

        //uc OA UC数据模块
        final String parentPackage = "com.longfor.oa." + moduleName;
        final String dbTables ="视图_客服系统_标准组织,视图_客服系统_标准角色,视图_客服系统_组织,视图_客服系统_角色,视图_客服系统_人员,视图_客服系统_人员与角色关系";


        final String fileDir ="C:/MyBatisPlus/" + moduleName;
        final String author = "zhouhuiyi";

        //数据库连接信息

        final DbType dbType = DbType.SQL_SERVER;
        final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        final String dbUrl ="jdbc:sqlserver://192.168.10.12:1433; DatabaseName=Elane.X.longfor.uc";
        final String dbUserName ="vanceinfo";
        final String dbPassword = "vanceinfo";

        log.info("开始生成文件,文件路径：" + fileDir);
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(fileDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setAuthor(author);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImp");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(dbType);
        dsc.setDriverName(driverName);
        dsc.setUrl(dbUrl);
        dsc.setUsername(dbUserName);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(dbTables.split(","));
        if(isBaseEntityFiledTable) {
            // 自定义实体父类
            strategy.setSuperEntityClass("com.longfor.stone.common.base.BaseIoEntity");
            // 自定义 mapper 父类
            strategy.setSuperMapperClass("com.longfor.stone.common.base.BaseIoMapper");
            // 自定义 service 父类
            strategy.setSuperServiceClass("com.longfor.stone.common.base.IBaseIoService");
            // 自定义 service 实现类父类
            strategy.setSuperServiceImplClass("com.longfor.stone.common.base.BaseIoServiceImpl");
            // 自定义 controller 父类
            strategy.setSuperControllerClass("com.longfor.stone.common.base.BaseController");
            // 自定义实体，公共字段
            strategy.setSuperEntityColumns(new String[]{"io_id", "io_tenant", "io_created_batch", "io_created_message", "io_created_date", "io_created_app", "io_process_status", "io_process_batch", "io_process_message", "io_process_date", "io_process_app"});
        }
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setCapitalMode(true);
        strategy.setDbColumnUnderline(true);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);
        pc.setController("controller");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        // 调整 xml 生成目录演示
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //创建xml文件目录
                String xmlFileDir = fileDir + "/resources/mapper/" + moduleName + "/";
                File dir = new File(xmlFileDir);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                return xmlFileDir +tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }
}