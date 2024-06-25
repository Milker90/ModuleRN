## 一、介绍
ModuleRN是包含React Native 0.66.0的SDK库，iOS和安卓原生项目可以通过它以SDK的方式集成到已经存在的原生项目中，实现项目的组件化

## 二、目录结构
android：包含android sdk工程，原生项目通过依赖它实现渲染和执行js代码
ios：包含ios sdk源代码
patches: react native源码和react native三方库的修改补丁
src: js代码
ModuleRN.podspec: ios pod spec，原生项目通过它集成ios sdk


## 三、iOS集成

### 1. 克隆ModuleRN项目，ModuleRN目录可以和主工程同级，也可以是主工程的子目录

### 2. 在原生项目中的Podfile中添加对ModuleRN的依赖
```
// 引入ModuleRN的ruby文件
require_relative '../ModuleRN/react_native_pods'

platform :ios, '13.0'

target 'IOSHostProject' do
  # Comment the next line if you don't want to use dynamic frameworks
  use_frameworks!

  // 安装React Native依赖的库
  installReactNativePods(module_path: "../ModuleRN", app_path: ".")
  // 安装ModuleRN
  pod 'ModuleRN', :path => '../ModuleRN'
  # Pods for IOSHostProject

  post_install do |installer|
    installer.generated_projects.each do |project|
      project.targets.each do |target|
        target.build_configurations.each do |config|
          config.build_settings["DEVELOPMENT_TEAM"] = "GW65VAGT7V"
          config.build_settings['IPHONEOS_DEPLOYMENT_TARGET'] = '13.0'
        end
      end
    end
    __apply_Xcode_12_5_M1_post_install_workaround(installer)
  end  
end
```

### 3. 使用ModuleRN提供的类

```
@import ModuleRN;

- (IBAction)openRNPage:sender {
    RRTestNativeViewController *vc = [RRTestNativeViewController new];
    [self.navigationController pushViewController:vc animated:YES];
}
```


## 四、Android集成

### 1. 克隆ModuleRN项目，ModuleRN目录可以和主工程同级，也可以是主工程的子目录

### 2. settings.gradle中引入ModuleRN工程
```
rootProject.name = "AndroidHostProject"
include ':app', ':ModuleRN'
project(':ModuleRN').projectDir = new File(rootDir, '../ModuleRN/android')
```

### 3.在主工程根目录下的build.gradle增加自定义maven仓库
```
allprojects {
    repositories {
        google()
        mavenCentral()

        maven {
            url new File(rootDir, '../ModuleRN/node_modules/react-native/android').toURI()
        }

        maven {
            // Android JSC is installed from npm
            url new File(rootDir, '../ModuleRN/node_modules/jsc-android/dist').toURI()
        }
    }
}
```

### 4. 在app目录下的build.gradle增加ModuleRN的依赖
```
dependencies {
    implementation project(':ModuleRN')
    //...
}
```

### 5.使用ModuleRN提供的类

```
import com.library.modulern.RRTestNativeActivity;

Intent intent = new Intent(MainActivity.this, RRTestNativeActivity.class);
startActivity(intent);
```

### 运行项目
通常RN项目开发期间是通过Metro服务器来加载jsbundle文件，只需要在ModuleRN根目录启动metro服务器就可以实时更新代码了

```
# 安装rn开发环境
# 启动Metro服务
npm run start
```