# AlertDialog

##1.
allprojects {<br>
    repositories {<br>
        ......<br>
        maven { url 'https://jitpack.io' }<br>
    }<br>
}<br>

##2.
dependencies {<br>
    ......<br>
    implementation 'com.github.BingoJf:AlertDialog:1.0'<br>
}<br>

##3.
AlertDialog(this).setTitle("标题")<br>
                .setShowMsg("内容")<br>
                .setLeftButton("左侧按钮") {  .... }<br>
                .setRightButton("右侧按钮"){  .... }<br>
                .show()<br>