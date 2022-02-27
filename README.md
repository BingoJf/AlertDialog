[![](https://jitpack.io/v/BingoZzzzzz/AlertDialog.svg)](https://jitpack.io/#BingoZzzzzz/AlertDialog)

### 项目层级&&build.gradle
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
 ```

### APP&&build.gradle
```
dependencies {
    implementation 'com.github.BingoZzzzzz:AlertDialog:1.0'
}
```

### Code
```
AlertDialog(this).setTitle("标题")
                 .setShowMsg("内容")
                 .setLeftButton("左侧按钮") {
                   //todo
                  }
                 .setRightButton("右侧按钮"){
                   //todo
                 }
                 .show()
```