package clean.code.sample;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("clean.code.sample");

        noClasses()
            .that()
            .resideInAnyPackage("clean.code.sample.service..")
            .or()
            .resideInAnyPackage("clean.code.sample.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..clean.code.sample.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
