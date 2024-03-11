package sbrt.preppy.lesson_17.lesson8_Spring.cache;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheType cacheType() default CacheType.IN_MEMORY;
    boolean zip() default false;
    Class[] identityBy() default Class.class;
    String keyName() default "";
    int listSize() default Integer.MAX_VALUE;
}