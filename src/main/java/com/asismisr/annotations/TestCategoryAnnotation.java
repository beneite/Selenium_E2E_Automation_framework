package com.asismisr.annotations;

import com.asismisr.enums.TestGroupEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestCategoryAnnotation {

    /**
     * this is a custom annotation interface which can be used to set the test method author and test method categories.
     */

    /**
     * This will ask for author if user doesn't provide any author, default will be "no-author"
     */
    public String testAuthors() default "no-author";

    /**
     * this will hold the test group/categories enums
     */
    public TestGroupEnum[] testGroups();
}
