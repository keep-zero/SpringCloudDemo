package com.springcloud.demo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description 代码生成工具演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 * @auther: lai.guanfu
 * @date: 2019-05-23 15:06
 */
public class CodeGenerator {
        /**
         * 读取控制台内容
         */
        public static String scanner(String tip) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder help = new StringBuilder();
            help.append("请输入" + tip + "：");
            System.out.println(help.toString());
            if (scanner.hasNext()) {
                String ipt = scanner.next();
                if (StringUtils.isNotEmpty(ipt)) {
                    return ipt;
                }
            }
            throw new MybatisPlusException("请输入正确的" + tip + "！");
        }

        public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            //设置模板引擎为FreeMarker,默认引擎为Velocity
//            mpg.setTemplateEngine(new FreemarkerTemplateEngine());

            /** ======================================全局策略配置start=============================== **/

            String moudleName = scanner("模块名");
            String tableName = scanner("表名，多个英文逗号分割");
            String parentModleName = scanner("父模块名");
            String isExtends = scanner("是否继承BaseEntity(Y/N)");
//            String moudleName = "springcloud-client2";
//            String tableName = "client_total";

            //整体路径，默认所有自动生成代码归属于业务模块moudle总目录下的目标模块
            String projectPath = System.getProperty("user.dir")+StringPool.SLASH+parentModleName+StringPool.SLASH+moudleName;

            //核验项目目录，如果项目不存在，不予创建生成代码
            File file = new File(projectPath);
            if(!file.exists()){
                throw new MybatisPlusException("请核验正确目录，当前项目不存在/n");
            }

            GlobalConfig gc = new GlobalConfig();

            //设置输出文件位置
            gc.setOutputDir(projectPath+ "/src/main/java");
            gc.setAuthor("lai.guanfu"); //作者
            gc.setOpen(false); //是否打开输出目录,默认为true
            gc.setFileOverride(false);// 是否覆盖同名文件，默认是false
            gc.setEnableCache(false);// XML 二级缓存
            gc.setSwagger2(true); //是否生成Swagger2的Api注解
            gc.setIdType(IdType.AUTO);//ID自增

            // 自定义文件命名，注意 %s 会自动填充表实体属性！
            //自定义Service的名称，如果不设置，默认Freemarker模板生成的Service类格式是：I%sService
             gc.setServiceName("%sService");

             gc.setBaseResultMap(true);

            mpg.setGlobalConfig(gc);

            /** ======================================全局策略配置end=============================== **/

            /** ======================================数据源配置【通过该配置，指定需要生成代码的具体数据库】start=============================== **/

            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=true&useSSL=false&characterEncoding=utf8");
            dsc.setDriverName("com.mysql.jdbc.Driver");
            dsc.setUsername("spring");
            dsc.setPassword("spring");
            dsc.setDbType(DbType.MYSQL);
            mpg.setDataSource(dsc);

            /** ======================================数据源配置end=============================== **/

            /** ======================================数据库表配置【通过该配置，可指定需要生成哪些表或者排除哪些表】start=============================== **/

            StrategyConfig strategy = new StrategyConfig();

            //数据库表映射到实体的命名策略，此处指定下划线转小驼峰
            strategy.setNaming(NamingStrategy.underline_to_camel);
            //数据库表字段映射到实体的命名策略,此处指定下划线转小驼峰
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            if("Y".equals(isExtends)){
                //自定义继承的Entity类全称，带包名
                strategy.setSuperEntityClass("com.springcloud.demo.entity.BaseEntity");
                strategy.setSuperEntityColumns("create_by","create_time","update_by","update_time");
            }
            //是否为lombok模型
            strategy.setEntityLombokModel(true);
            //生成 @RestController 控制器
            strategy.setRestControllerStyle(true);

            //需要包含的表名，允许正则表达式（与exclude二选一配置），多个表格以英文逗号分隔
            strategy.setInclude(tableName.split(","));
//            strategy.setSuperEntityColumns("id");
            //驼峰转连字符
            strategy.setControllerMappingHyphenStyle(true);
            //是否生成实体时，生成字段注解
            strategy.setEntityTableFieldAnnotationEnable(true);
            //逻辑删除字段
            strategy.setLogicDeleteFieldName("del_flag");

//            strategy.setTablePrefix(pc.getModuleName() + "_");

            mpg.setStrategy(strategy);
            /** ======================================数据库表配置end=============================== **/

            /** ======================================包名配置【通过该配置，指定生成代码的包路径】start=============================== **/
            PackageConfig pc = new PackageConfig();
            //父模块名,会拼接到父包名后面作为包
//            pc.setModuleName(scanner("模块名"));
            //父包名，如果为空，将下面子包名必须写全部， 否则就只需写子包名
            pc.setParent("com.springcloud.demo");
            mpg.setPackageInfo(pc);

            /** ======================================包名配置end=============================== **/

            /** ======================================注入配置【通过该配置，可注入自定义参数等操作以实现个性化操作】start=============================== **/

            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };

            // 模板路径，如果模板引擎是 freemarker
//            String templatePath = "/templates/mapper.xml.ftl";
            // 模板路径，如果模板引擎是 velocity
             String templatePath = "/templates/mapper.xml.vm";

            // 自定义输出配置文件(mapper.xml)
            List<FileOutConfig> focList = new ArrayList<>();

            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/src/main/resources/mapper/"
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
            /** ======================================注入配置end=============================== **/

            /** ======================================模板配置【可自定义代码生成的模板，实现个性化操作】start=============================== **/
            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig();

            // 配置自定义输出模板
            //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别,此处使用自带Freemarker模板即可
            // templateConfig.setEntity("templates/entity2.java");
            // templateConfig.setService();
            // templateConfig.setController();
            templateConfig.setEntity("/template/Entity.java");
            templateConfig.setMapper("/template/Mapper.java");
            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);

            /** ======================================模板配置end=============================== **/
            mpg.execute();
        }
}
