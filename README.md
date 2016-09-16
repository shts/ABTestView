ABTestView
====================
View for AB test of Android.

[![](https://jitpack.io/v/shts/ABTestView.svg)](https://jitpack.io/#shts/ABTestView)


<img src=https://github.com/shts/ABTestView/blob/master/images/device-2016-09-16-123716.png width="300" />

How to Use
=====

To see how the ABTestView are added to your xml layouts, check the sample project.

```xml
<jp.shts.android.library.abtestview.ABTestView
    android:id="@+id/each"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layoutA="@layout/pattern_a"
    app:layoutB="@layout/pattern_b"
    app:inflatedId="@+id/inflated_each"
    app:mode="each" />
```

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ABTestView abTestViewEach = (ABTestView) findViewById(R.id.each);
    View inflatedEachView = abTestViewEach.inflate(); // Must
}
```

Attribute
=====

|name|type||
|----|----|----|
|layoutA|reference|layout resource|
|layoutB|reference|layout resource|
|mode|int|`each` or `fix`<br> `each` -> select A or B at random at every time. <br>`fix` -> select A or B at random at first time. save selected item(AorB). refered saved item(AorB) at every times.|
|inflatedId|reference|id after view inflated|


Install
=====

Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```
Add the dependency

```groovy
dependencies {
        compile 'com.github.shts:ABTestView:1.0.0'
}
```

License
=======

    Copyright (C) 2016 Shota Saito

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
