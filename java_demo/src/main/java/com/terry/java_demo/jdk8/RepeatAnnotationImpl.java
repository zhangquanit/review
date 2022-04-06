package com.terry.java_demo.jdk8;

/**
 * @author zhangquan
 */

@RepeatAnnotation("aa")
@RepeatAnnotation("bbb")
public class RepeatAnnotationImpl {

    public static void main(String[] args) {
        boolean annotationPresent = RepeatAnnotationImpl.class.isAnnotationPresent(RepeatAnnotations.class);
        if (annotationPresent) {
            RepeatAnnotations annotations = RepeatAnnotationImpl.class.getAnnotation(RepeatAnnotations.class);
            RepeatAnnotation[] values = annotations.value();
            for (RepeatAnnotation annotation : values) {
                System.out.println(annotation);
            }

            /**输出
             * @com.terry.java_demo.jdk8.RepeatAnnotation(value=aa)
             * @com.terry.java_demo.jdk8.RepeatAnnotation(value=bbb)
             */
        }
    }
}
