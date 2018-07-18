Explain
=======


```Java
dependencies {
    implementation 'com.github.cxfish:cxmvp:1.0'
}
```


This project contains rxjava2、Glide、Butterknife and rxlifecycle2.
if you want to develop with cxmvp, you should Let your own project's App inherit CxAppliction：


```Java
public class MyApplication extends CxApplication
......
```

if you want to use ButterKnife in your project, in your own app gradle:

```Java
dependencies {
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'
}
```
