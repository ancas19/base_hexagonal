import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import static com.tngtech.archunit.lang.conditions.ArchConditions.beAnnotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = "co.com.ancas",
        importOptions = {ImportOption.DoNotIncludeTests.class, IgnoreNestedAndBuilderClassesHelper.class}
)
public class ArchitectureTest {

    @ArchTest
    public static final ArchRule MODELS_ONLY_IN_MODEL_PACKAGES = classes()
            .that().haveSimpleNameEndingWith("Model")
            .should().resideInAnyPackage("..models..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule CLASSES_IN_MODEL_PACKAGE_MUST_END_WITH_MODEL = classes()
            .that().resideInAnyPackage("..models..")
            .should().haveSimpleNameEndingWith("Model")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule MODELS_ONLY_DEPENDS_OF_MODEL_ENUM_EXCEPTIONS = classes()
            .that().resideInAnyPackage("..domains.models..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage(
                    "java..",
                    "jakarta..",
                    "..domains.models..",
                    "..domains.enums..",
                    "..domains.exceptions.."
            )
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule ADAPTERS_ONLY_IN_ADAPTER_PACKAGES = classes()
            .that().haveSimpleNameEndingWith("Adapter")
            .should().resideInAnyPackage("..adapters..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule ADAPTER_CLASSES_MUST_END_WITH_ADAPTER = classes()
            .that().resideInAnyPackage("..adapters..")
            .should().haveSimpleNameEndingWith("Adapter")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule PORTS_ONLY_IN_PORT_PACKAGES = classes()
            .that().haveSimpleNameEndingWith("Port")
            .should().resideInAnyPackage("..ports..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule PORT_CLASSES_MUST_END_WITH_PORT = classes()
            .that().resideInAnyPackage("..ports..")
            .should().haveSimpleNameEndingWith("Port")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule SERVICES_ONLY_IN_SERVICE_PACKAGES = classes().that()
            .haveSimpleNameEndingWith("Service")
            .should().resideInAnyPackage("..services..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule SERVICE_CLASSES_MUST_END_WITH_SERVICE = classes()
            .that().resideInAPackage("..services..")
            .should().haveSimpleNameEndingWith("Service")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule HANDLERS_ONLY_IN_HANDLER_PACKAGE = classes()
            .that()
            .haveSimpleNameEndingWith("Handler")
            .should().resideInAnyPackage("..handlers..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule HANDLER_CLASSES_IN_HANDLER_PACKAGE_MUST_END_WITH_HANDLER = classes()
            .that().resideInAPackage("..handlers..")
            .should().haveSimpleNameEndingWith("Handler")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule CONTROLLERS_ONLY_IN_CONTROLLER_PACKAGES = classes().that()
            .haveSimpleNameEndingWith("Controller")
            .should().resideInAnyPackage("..controllers..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule CONTOLLER_CLASSES_MUST_END_WITH_CONTROLLER =
            classes()
                    .that().resideInAPackage("..controllers..")
                    .should()
                    .haveSimpleNameEndingWith("GetController")
                    .orShould().haveSimpleNameEndingWith("PostController")
                    .orShould().haveSimpleNameEndingWith("PutController")
                    .orShould().haveSimpleNameEndingWith("PatchController")
                    .orShould().haveSimpleNameEndingWith("DeleteController")
                    .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule CONFIGURATION_CLASSES_ONLY_IN_CONFIGURATION_PACKAGE = classes()
            .that().haveSimpleNameEndingWith("Config")
            .should().resideInAnyPackage("..configurations..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule CONFIGURATIONS_MUST_END_WITH_CONFIG = classes()
            .that().resideInAPackage("..configurations..")
            .should().haveSimpleNameEndingWith("Config")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule RESPONSES_ONLY_IN_RESPONSE_PACKAGES = classes()
            .that()
            .haveSimpleNameEndingWith("Response")
            .should().resideInAnyPackage("..responses..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule RESPONSE_CLASSES_MUST_END_WITH_RESPONSE = classes()
            .that().resideInAPackage("..responses..")
            .should().haveSimpleNameEndingWith("Response")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule REQUEST_CLASSES_ONLY_IN_REQUEST_PACKAGE = classes()
            .that()
            .haveSimpleNameEndingWith("Request")
            .should().resideInAnyPackage("..requests..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule REQUEST_CLASSES_MUST_END_WITH_REQUEST =
            classes()
                    .that().resideInAPackage("..requests..")
                    .should().haveSimpleNameEndingWith("Request")
                    .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule ADAPTERS_ONLY_ACCESSED_BY_CONTROLLERS_OR_ADAPTERS =
            classes().that()
                    .resideInAPackage("..adapters..")
                    .should()
                    .onlyBeAccessed()
                    .byAnyPackage("..controllers..", "..adapters..", "..configurations..", "..services..")
                    .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule DONT_USE_FIELD_INJECTION = ArchRuleDefinition.noFields()
            .should(beAnnotatedWith("org.springframework.beans.factory.annotation.Autowired")).because(
                    "Injection fields is not  advisible, see this https://blog.marcnuri.com/inyeccion-de-campos-desaconsejada-field-injection-not-recommended-spring-ioc/")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule REPOSITORY_ALLOWED_ONLY_IN__ADAPTERS = classes()
            .that()
            .resideInAPackage("..repositories..")
            .should().onlyBeAccessed()
            .byAnyPackage("..use_cases..", "..adapters..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule SERVICES_ONLY_ACCESSED_BY_CONTROLLERS = ArchRuleDefinition.noClasses().that()
            .resideInAPackage("..services..")
            .should().onlyBeAccessed().byAnyPackage("..controllers..", "..services..")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule CONTROLLERS_MUST_HAVE_TAG_ANNOTATION = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().beAnnotatedWith(io.swagger.v3.oas.annotations.tags.Tag.class)
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule MAPPING_METHOD_MUST_HAVE_OPERATION_ANNOTATION = classes()
            .that().areAnnotatedWith(GetMapping.class)
            .or().areAnnotatedWith(PostMapping.class)
            .or().areAnnotatedWith(PutMapping.class)
            .or().areAnnotatedWith(PatchMapping.class)
            .or().areAnnotatedWith(DeleteMapping.class)
            .should().beAnnotatedWith(Operation.class)
            .allowEmptyShould(true);


}
