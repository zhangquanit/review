// Person.aidl.aidl
package com.review.aidl.server.bean;
parcelable Person;
/**
* 这个Person.aidl文件很简单，就是定义了一个Parcelable类，告诉系统我们需要序列化和反序列化的类型。
* 每一个实现了Parcelable的类型都需要对应的.aidl文件。AIDL编译器在编译AIDL文件时会自动查找此类文件。
*/
