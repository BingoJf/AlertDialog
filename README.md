# AlertDialog
AlertDialog

1.
allprojects {
    repositories {
       ......
       maven { url 'https://jitpack.io' }
    }
}

2.
dependencies {
    ......
    implementation 'com.github.BingoJf:AlertDialog:1.0'
}

3.
AlertDialog(this).setTitle("标题")
                 .setShowMsg("内容")
                 .setLeftButton("左侧按钮") {  .... }
                 .setRightButton("右侧按钮"){  .... }
                 .show()