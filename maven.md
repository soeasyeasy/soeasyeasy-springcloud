# 一、认识flatten-maven-plugin插件

flatten-maven-plugin 是一个由 MojoHaus 组织维护的 Maven 插件，主要用于解决 Maven 多模块项目中 POM
文件继承导致的复杂依赖问题。以下是该插件的详细介绍和功能：

## 核心功能：

### 扁平化 POM 文件：

插件会生成一个简化版的 pom.xml 文件（通常命名为 .flattened-pom.xml），去除不必要的继承和依赖管理。
在安装（install）和部署（deploy）阶段，Maven 会使用这个扁平化的 POM 文件，而不是原始的 POM 文件。

### 解决占位符问题：

在多模块项目中，父模块的版本号通常通过占位符（如 ${revision}）定义，子模块继承父模块的版本号。但在打包时，这些占位符不会被替换为实际值，导致远程仓库无法正确解析。
flatten-maven-plugin 可以在构建过程中将这些占位符替换为实际值，确保生成的 POM 文件可以被正确解析。

### 统一版本管理：

通过在父模块中定义版本号（如 ${revision}），所有子模块可以继承该版本号，从而实现版本的统一管理。
插件支持在构建过程中动态替换版本号，减少手动修改版本号的工作量。

## 使用场景：

多模块项目：在多模块项目中，父模块定义了版本号和依赖管理，子模块继承父模块的配置。flatten-maven-plugin 可以确保在构建过程中正确处理版本号和依赖。
CI/CD 环境：在持续集成和持续部署环境中，插件可以确保生成的 POM 文件在远程仓库中可以被正确解析。

定义 flatten-maven-plugin 的版本号

<maven.flatten.version>1.2.5</maven.flatten.version>

```
<build>
    <plugins>
        <plugin>
            <!-- 插件的 GroupId，标识插件所属的组织 -->
            <groupId>org.codehaus.mojo</groupId>
            <!-- 插件的 ArtifactId，标识插件的名称 -->
            <artifactId>flatten-maven-plugin</artifactId>
            <!-- 插件的版本号，这里通过变量引用上面定义的版本号 -->
            <version>${maven.flatten.version}</version>
            <configuration>
                <!-- 配置扁平化后的 POM 文件的名称 -->
                <flattenedPomFilename>pom-xml-flattened</flattenedPomFilename>
                <!-- 是否更新原始 POM 文件 -->
                <updatePomFile>true</updatePomFile>
                <!-- 指定扁平化模式，resolveCiFriendliesOnly 表示仅解析 CI 友好的占位符 -->
                <flattenMode>resolveCiFriendliesOnly</flattenMode>
            </configuration>
            <executions>
                <!-- 定义插件的执行配置 -->
                <execution>
                    <!-- 执行的唯一标识 -->
                    <id>flatten</id>
                    <!-- 指定插件绑定的生命周期阶段 -->
                    <phase>process-resources</phase>
                    <!-- 定义执行的目标 -->
                    <goals>
                        <!-- 执行扁平化操作 -->
                        <goal>flatten</goal>
                    </goals>
                </execution>
                <!-- 定义清理阶段的执行配置 -->
                <execution>
                    <!-- 清理阶段的唯一标识 -->
                    <id>flatten.clean</id>
                    <!-- 指定清理阶段绑定的生命周期阶段 -->
                    <phase>clean</phase>
                    <!-- 定义清理阶段执行的目标 -->
                    <goals>
                        <!-- 执行清理操作 -->
                        <goal>clean</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
