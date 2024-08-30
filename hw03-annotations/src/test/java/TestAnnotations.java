import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Test {
}

@Retention(RetentionPolicy.RUNTIME)
@interface Before {
}

@Retention(RetentionPolicy.RUNTIME)
@interface After {
}
