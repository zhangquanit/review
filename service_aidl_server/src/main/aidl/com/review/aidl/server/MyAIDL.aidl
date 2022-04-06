// MyAIDL.aidl
package com.review.aidl.server;
import com.review.aidl.server.bean.Person; //引入
interface MyAIDL {
       String getInfor(String s);

       String greet(in Person person);

       List<Person> getPerson();
}
